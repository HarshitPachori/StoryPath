package com.example.storypath.utils;

import java.io.IOException;
import java.util.HashMap;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
      throws IOException, ServletException {
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

    HashMap<Object, Object> mapBody = new HashMap<>();
    mapBody.put("status", HttpServletResponse.SC_UNAUTHORIZED);
    mapBody.put("message", "You need to be logged in to perform this action");

    ObjectMapper mapper = new ObjectMapper();
    mapper.writeValue(response.getOutputStream(), mapBody);
  }

}
