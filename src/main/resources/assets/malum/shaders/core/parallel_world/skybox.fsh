#version 150

uniform samplerCube SkyboxSampler;

in vec3 texCoord;

out vec4 fragColor;

void main() {
    vec4 skyboxColor = texture(SkyboxSampler, texCoord);
    fragColor = skyboxColor;
}