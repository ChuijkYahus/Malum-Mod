#version 150

#moj_import <lodestone:common_math.glsl>

uniform sampler2D Sampler0;
uniform sampler2D Sampler1;

uniform float LumiTransparency;
uniform float GameTime;
uniform float TimeOffset;
uniform float Speed;
uniform float Intensity;
uniform float XFrequency;
uniform float YFrequency;
uniform vec4 UVCoordinates;

uniform vec4 ColorModulator;

uniform float Dissolvement;
uniform float Width;
uniform float Height;

in vec4 vertexColor;
in vec2 texCoord0;

out vec4 fragColor;

void main() {
    float time = GameTime * Speed + TimeOffset;
    vec2 uv = texCoord0;
    vec2 uCap = vec2(UVCoordinates.x, UVCoordinates.y);
    vec2 vCap = vec2(UVCoordinates.z, UVCoordinates.w);

    uv.x = floor(uv.x* Width)/ Width;
    uv.y = floor(uv.y* Height)/ Height;

    uv.x += cos(uv.y*XFrequency+time)/Intensity;
    uv.y += sin(uv.x*YFrequency+time)/Intensity;

    uv.x = clamp(uv.x, uCap.x, uCap.y);
    uv.y = clamp(uv.y, vCap.x, vCap.y);
    vec4 textureColor = texture(Sampler0, uv);
    if (textureColor.a == 0) {
        discard;
    }
    vec4 dissolved = texture(Sampler1, uv);
    float threshold = Dissolvement;
    threshold -= abs(0.1f * cos(uv.y*24.0+time*3.0) * (1.0-threshold));
    threshold *= 1 - abs(0.2f * sin(uv.x*24.0+time*1.5) * (1.0-threshold));
    if (dissolved.r > threshold) {
        discard;
    }
    vec4 color = transformColor(textureColor, LumiTransparency, vertexColor, ColorModulator);
    fragColor = color;
}
