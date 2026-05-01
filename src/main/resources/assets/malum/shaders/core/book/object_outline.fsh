#version 150

#moj_import <lodestone:common_math.glsl>

uniform sampler2D Sampler0;

uniform int OutlineWidth;
uniform vec2 SourceTextureSize;
uniform vec2 OutputTextureSize;

in vec2 texCoord0;

out vec4 fragColor;

vec4 textureSampler(vec2 uv){
    if (any(greaterThan(abs(uv-.5), vec2(.5)))) {
        return vec4(0.0);
    }
    return texture(Sampler0, uv); // casting to float because glsl is stupid, please give me slang back
}
// kernel that defines the shape and thickness of the border, tweakable
float kernel(vec2 pos){
    float dist = length(pos)/float(OutlineWidth);
    dist = dist * dist;
    float posterize = 4.;
    dist = floor(dist*posterize)/posterize;
    return max(0.,1.-dist); // casting to float because glsl is stupid, please give me slang back
}

vec4 borderFilter(vec2 uv){
    vec4 col = vec4(0.);

    uv *= OutputTextureSize;

    vec4 closest_sample = vec4(0.);
    vec2 closest_pos = vec2(OutlineWidth+1);
    float closest_d = float(OutlineWidth+1);

    // iterate over a kernel the size of the border to cover all the pixels you need
    for (int y = -OutlineWidth; y<=OutlineWidth; y++){
        for (int x = -OutlineWidth; x<=OutlineWidth; x++){
            vec4 tex_sample = textureSampler((uv-vec2(x,y) +SourceTextureSize/2. -OutputTextureSize/2.)/SourceTextureSize);
            // we assume a pixel with an alpha of over half is solid and needs a border, tweak as you wish
            if (tex_sample.a >= 0.5) {
                if (closest_d>length(vec2(x, y))){
                    closest_sample = tex_sample;
                    closest_pos = vec2(x, y);
                    closest_d = length(vec2(x, y));
                }
            }
        }
    }


    float kernel_sample = kernel(closest_pos);

    col = vec4(vec3(kernel_sample), 1.0);

    return col;
}

void main() {
    vec4 border = borderFilter(texCoord0);
    fragColor = border;
}
