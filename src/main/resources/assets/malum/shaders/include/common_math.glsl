float hash(vec2 p) {
    p = p - (512. * floor(p/512.));
    return fract(sin(dot(p, vec2(12.9898, 4.1414))) * 43758.5453);
}

float noise(vec2 x) {
    vec2 i = floor(x);
    vec2 f = fract(x);
    f = f*f*(3.0-2.0*f);

    float res =
    mix(
    mix(hash(i+vec2(0., 0.)),hash(i+vec2(1., 0.)),f.x),
    mix(hash(i+vec2(0., 1.)),hash(i+vec2(1., 1.)),f.x),f.y);
    return res*res;
}

float layeredNoise(vec2 uv, float speed, float gameTime) {
    float time = speed*gameTime;
    vec2 direction = normalize(vec2(sin(mod(time*0.025, 6.28)), cos(mod(time*0.025, 6.28))));
    vec2 offset = 3.*normalize(vec2(time, time)*direction);

    vec2 positive = uv+offset;
    vec2 negative = uv-offset;
    float n = 0.;
    n += 0.5*noise(positive);
    n += 0.25*noise(negative);
    n += 0.125*noise(positive);
    n += 0.0625*noise(negative);
    n += 0.03215*noise(positive);
    return n/2.;
}

float pattern(vec2 uv, float speed, float gameTime) {
    return layeredNoise(uv+layeredNoise(uv+layeredNoise(uv, speed, gameTime), speed, gameTime), speed, gameTime);
}

vec4 fancySample(sampler2D sampler, vec2 uv, float speed, float distortion, float gameTime) {
    float n = pattern(distortion*uv, speed, gameTime);
    float n2 = pattern(distortion*uv + 0.01, speed, gameTime);
    vec2 distortedUV = uv+vec2(cos(n), cos(n));
    vec2 distortedUV2 = uv-vec2(cos(n2), cos(n2));
    vec4 noise = texture(sampler, distortedUV)+texture(sampler, distortedUV2);
    return vec4(noise.rgb/2., noise.a);
}