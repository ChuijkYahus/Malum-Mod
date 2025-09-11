#version 150

uniform sampler2D Sampler0;

in vec2 texCoord;

out vec4 fragColor;

void main() {
    vec4 skyboxColor = texture(Sampler0, texCoord);
    fragColor = skyboxColor;
}