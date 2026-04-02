#version 150

#moj_import <lodestone:common_math.glsl>

uniform sampler2D Sampler0;
uniform sampler2D Sampler1;
uniform sampler2D Sampler2;

uniform float LumiTransparency;
uniform float GameTime;

uniform vec4 ColorModulator;

in vec4 vertexColor;
in vec2 texCoord0;

out vec4 fragColor;

void main() {
    vec2 uv = texCoord0;

    vec4 textureColor = texture(Sampler0, uv);
    if (textureColor.a == 0) {
        discard;
    }
    vec4 cutoutColor = texture(Sampler1, uv);
    vec4 targetColor = texture(Sampler2, uv);
    float lumi = 0.21 * cutoutColor.r + 0.71 * cutoutColor.g + 0.07 * cutoutColor.b;
    vec4 combinedColor = textureColor;
    combinedColor += targetColor * lumi;
    vec4 color = transformColor(combinedColor, LumiTransparency, vertexColor, ColorModulator);
    fragColor = combinedColor;
}
