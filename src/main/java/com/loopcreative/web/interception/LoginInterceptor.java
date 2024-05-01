package com.loopcreative.web.interception;

import com.loopcreative.web.error.RestApiException;
import com.loopcreative.web.error.UserErrorCode;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setHeader("Access-Control-Allow-Private-Network", "true");

        String requestURI = request.getRequestURI();
        log.info("[LoginInterceptor] URI : " + requestURI);
        HttpSession session = request.getSession(false);
        Cookie[] cookies = request.getCookies();

        if(session == null || session.getAttribute("admin") == null){
            log.info("[LoginInterceptor] : 미인증 사용자 ");
            throw new RestApiException(UserErrorCode.NOT_USER);
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
