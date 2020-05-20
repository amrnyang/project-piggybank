package org.pb.config;

import lombok.Data;
import org.pb.service.LoginUserDetailService;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Data
//@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtHelper jwtHelper;

    private final LoginUserDetailService loginUserDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String authoriztionHeader = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);

        String username = null;
        String jwt = null;

        if(Objects.nonNull(authoriztionHeader) && authoriztionHeader.startsWith("Bearer ")) {
            jwt = authoriztionHeader.substring(7);
            username = jwtHelper.extractUsername(jwt);
        }

        if(Objects.nonNull(username) && SecurityContextHolder.getContext().getAuthentication() == null) {
            final UserDetails userDetails = loginUserDetailService.loadUserByUsername(username);
            if(jwtHelper.validateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken upat =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(upat);
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
