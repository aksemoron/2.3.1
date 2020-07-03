package ru.project.config.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import ru.project.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
        User user = (User) authentication.getPrincipal();

        if (user.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("ADMIN"))) {
            httpServletResponse.sendRedirect("/admin/all");
        } else {
            httpServletResponse.sendRedirect("/user/me");
        }
    }


}