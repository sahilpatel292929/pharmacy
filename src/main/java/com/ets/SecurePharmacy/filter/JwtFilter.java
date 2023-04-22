package com.ets.SecurePharmacy.filter;

import org.springframework.stereotype.Component;

@Component
public class JwtFilter  {

	/*
	 * @Autowired private JwtUtil jwtUtil;
	 * 
	 * @Autowired private CustomUserDetailsService service;
	 * 
	 * 
	 * @Override protected void doFilterInternal(HttpServletRequest
	 * httpServletRequest, HttpServletResponse httpServletResponse, FilterChain
	 * filterChain) throws ServletException, IOException {
	 * 
	 * String authorizationHeader = httpServletRequest.getHeader("Authorization");
	 * 
	 * String token = null; String userName = null;
	 * 
	 * if (authorizationHeader != null && authorizationHeader.startsWith("Bearer "))
	 * { token = authorizationHeader.substring(7); userName =
	 * jwtUtil.extractUsername(token); }
	 * 
	 * if (userName != null &&
	 * SecurityContextHolder.getContext().getAuthentication() == null) {
	 * 
	 * UserDetails userDetails = service.loadUserByUsername(userName);
	 * 
	 * if (jwtUtil.validateToken(token, userDetails)) {
	 * 
	 * UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new
	 * UsernamePasswordAuthenticationToken(userDetails, null,
	 * userDetails.getAuthorities()); usernamePasswordAuthenticationToken
	 * .setDetails(new
	 * WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
	 * SecurityContextHolder.getContext().setAuthentication(
	 * usernamePasswordAuthenticationToken); } }
	 * filterChain.doFilter(httpServletRequest, httpServletResponse); }
	 */
}
