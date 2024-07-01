package com.example.storypath_backend.utils;

import com.example.storypath_backend.exception.ApiException;
import com.example.storypath_backend.service.impl.UserDetailsServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenHelper jwtTokenHelper;
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String requestHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        if (requestHeader != null && requestHeader.startsWith("Bearer")) {
            if (requestHeader.startsWith("Bearer ")) {
                token = requestHeader.substring(7);
                try {
                    username = jwtTokenHelper.getUsernameFromToken(token);
                } catch (IllegalArgumentException e) {
                    throw new ApiException(HttpStatus.BAD_REQUEST, "Unable to get JWT token");
                } catch (ExpiredJwtException e) {
                    throw new ApiException(HttpStatus.UNAUTHORIZED, "JWT token is expired");
                } catch (MalformedJwtException | SignatureException e) {
                    throw new ApiException(HttpStatus.BAD_REQUEST, "Invalid JWT token");
                }
            } else {
                throw new ApiException(HttpStatus.BAD_REQUEST, "JWT token not start with Bearer string");
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);
            if (jwtTokenHelper.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        username,
                        null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            } else {
                throw new ApiException(HttpStatus.BAD_REQUEST, "username or token is not valid");
            }
        } else {
            log.warn("username is null or context is not null");
        }

        filterChain.doFilter(request, response);
    }

}