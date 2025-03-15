$input a_color0, a_position
$output v_color0

#include <bgfx_shader.sh>

uniform vec4 StarsColor;
uniform float u_time;

void main() {
#ifndef INSTANCING
  vec3 pos = a_position;
  vec3 worldPos = mul(u_model[0], vec4(pos, 1.0)).xyz;

  vec4 color = a_color0;
  float star = fract(sin(dot(pos.xy * 5.0, vec2(12.9898, 78.233))) * 43758.5453);
  star = step(0.89, star);

  float twinkle = 0.5 + 0.5 * sin(u_time + pos.x * 10.0);
  color.rgb = vec3(star, star, star) * 1.5 * twinkle;
 
  v_color0 = color;
  gl_Position = mul(u_viewProj, vec4(worldPos, 1.0));
#else
  gl_Position = vec4(0.0,0.0,0.0,0.0);
#endif
}
