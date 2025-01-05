package todoapp.todoapp_develop.auth.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import todoapp.todoapp_develop.global.util.SessionUtil;
import todoapp.todoapp_develop.user.repository.UserRepository;

import java.io.IOException;
import java.util.Arrays;

@RequiredArgsConstructor
public class AuthFilter implements Filter {
    private final UserRepository userRepository;
    private static final String[] WHITELIST = {"/api/users/signup", "/api/auth/login"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestURI = httpRequest.getRequestURI();

        // 화이트리스트 경로는 인증 체크 제외
        if (Arrays.asList(WHITELIST).contains(requestURI)) {
            chain.doFilter(request, response);
            return;
        }

        // 세션에서 로그인 정보 확인
        HttpSession session = httpRequest.getSession(false);
        if (session == null || SessionUtil.getLoginUserId(session) == null) {
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "로그인이 필요합니다.");
            return;
        }

        chain.doFilter(request, response);
    }
}