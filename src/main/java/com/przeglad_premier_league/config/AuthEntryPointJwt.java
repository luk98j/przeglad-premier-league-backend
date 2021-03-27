package com.przeglad_premier_league.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class AuthEntryPointJwt implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
      log.error("Unauthorized error: {}", e.getMessage());

      if(e.getMessage()!=null && e.getMessage().equals("Bad credentials")) {
          httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Podano błędne hasło");
      }
      else {
          httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Brak użytkownika o podanym loginie. Załóż konto.");
      }
    }
}
