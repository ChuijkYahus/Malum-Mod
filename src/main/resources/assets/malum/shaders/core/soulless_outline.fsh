#version 150

#moj_import <lodestone:common_math.glsl>

uniform sampler2D Sampler0;
uniform sampler2D Skybox;
uniform sampler2D Mask;
uniform float LumiTransparency;

uniform vec2 ScreenSize;
uniform vec4 ColorModulator;
uniform float FogStart;
uniform float FogEnd;
uniform vec4 FogColor;

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
    vec2 skyUv = gl_FragCoord.xy/ScreenSize;
    vec4 skyColor = texture(Skybox, skyUv);
    fragColor = skyColor;




//    vec2 uv = texCoord0;
//    uv.x = floor(uv.x* Width)/ Width;
//    uv.y = floor(uv.y* Height)/ Height;
//    float skyWidth = Width*4.0;
//    float skyHeight = Height*4.0;
//    skyUv.x = floor(skyUv.x* skyWidth)/ skyWidth;
//    skyUv.y = floor(skyUv.y* skyHeight)/ skyHeight;
//    vec4 textureColor = texture(Sampler0, uv);
//    vec4 color = transformColor(textureColor, LumiTransparency, vertexColor, ColorModulator);
//    vec4 mask = texture(Mask, texCoord0);
//    fragColor = skyColor;
//    if (mask.a == 0.0) {
//        discard;
//    }
//    float lumi = (0.21 * skyColor.r + 0.71 * skyColor.g + 0.07 * skyColor.b);
//    float delta = min(0.5 + lumi*2.0, 1.0);
//    float alpha = color.a * delta;
//    color = vec4(mix(color.rgb, skyColor.rgb, delta), alpha);
//    vec4 fog = applyFog(color, FogStart, FogEnd, FogColor, vertexDistance);
//    if (fog.a == 0.0) {
//        discard;
//    }
//    fragColor = fog;
}