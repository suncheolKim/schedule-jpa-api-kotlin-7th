package net.sckim.schedule.api.security;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.util.PatternMatchUtils;

import javax.security.sasl.AuthenticationException;
import java.io.IOException;

public class LoginFilter implements Filter {
    private static final String[] WHITE_LIST = {"/", "*/signup", "*/login", "*/logout", "*/swagger-ui/**", "*/v3/api-docs/**", "*/v3/api-docs"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        if (!isWhiteList(requestURI)) {
            HttpSession session = httpRequest.getSession(false);

            if (session == null || session.getAttribute(SessionType.USER) == null) {
                throw new AuthenticationException("로그인 해 주세요.");
            }
        }

        chain.doFilter(request, response);
    }

    private boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}
