#version 150

#moj_import <lodestone:common_math.glsl>

uniform sampler2D Sampler0;
uniform sampler2D Mask;
uniform float LumiTransparency;

uniform vec4 ColorModulator;
uniform float FogStart;
uniform float FogEnd;
uniform vec4 FogColor;

in float vertexDistance;
in vec4 vertexColor;
in vec2 texCoord0;
in vec3 normal;
in vec3 tangent;
in vec3 bitangent;

out vec4 fragColor;

void main() {
    vec4 color = transformColor(texture(Sampler0, texCoord0), LumiTransparency, vertexColor, ColorModulator);
    vec4 mask = texture(Mask, texCoord0);
    if (mask.a == 0.0) {
        discard;
    }
    vec4 fog = applyFog(color, FogStart, FogEnd, FogColor, vertexDistance);
    if (fog.a == 0.0) {
        discard;
    }
    fragColor = fog;
}