#version 150

#moj_import <lodestone:common_math.glsl>
#moj_import <malum:common_math.glsl>

uniform sampler2D Sampler0;
uniform sampler2D Skybox;
uniform sampler2D Mask;
uniform float LumiTransparency;

uniform vec2 ScreenSize;
uniform vec4 ColorModulator;
uniform float GameTime;
uniform float FogStart;
uniform float FogEnd;
uniform vec4 FogColor;

uniform float Speed;
uniform float Distortion;
uniform float Width;
uniform float Height;

in float vertexDistance;
in vec4 vertexColor;
in vec2 texCoord0;
in vec3 normal;
in vec3 tangent;
in vec3 bitangent;

out vec4 fragColor;

void main() {
    vec4 mask = texture(Mask, texCoord0);
    if (mask.a == 0.0) {
        discard;
    }
    vec2 uv = texCoord0;
    uv.x = floor(uv.x* Width)/ Width;
    uv.y = floor(uv.y* Height)/ Height;
    vec2 skyUv = gl_FragCoord.xy/ScreenSize;
    float skyWidth = Width*4;
    float skyHeight = Height*4;
    skyUv.x = floor(skyUv.x* skyWidth)/ skyWidth;
    skyUv.y = floor(skyUv.y* skyHeight)/ skyHeight;
    vec4 skyColor = texture(Skybox, skyUv);
    vec4 noise = fancySample(Sampler0, uv, Speed, Distortion, GameTime);
    vec4 color = transformColor(noise, LumiTransparency, vertexColor, ColorModulator);
    float maskLumi = (0.21 * mask.r + 0.71 * mask.g + 0.07 * mask.b);
    float alpha = min(color.a * 0.05 + (maskLumi * 0.1), 1.0);
    color = vec4((color.rgb + skyColor.rgb) * 0.5, alpha);
    vec4 fog = applyFog(color, FogStart, FogEnd, FogColor, vertexDistance);
    if (fog.a == 0.0) {
        discard;
    }
    fragColor = fog;
}