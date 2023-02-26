package com.accenture.russiaatc.irentservice10.SNAPSHOT.controller;

import com.accenture.russiaatc.irentservice10.SNAPSHOT.configuration.SecurityContext;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.exception.BusinessRuntimeException;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.exception.ErrorCodeEnum;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.CallContext;
import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.user.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class RestControllerSecurityInterceptor implements AsyncHandlerInterceptor {
    @Value("${jwt.secret}")
    private String secret;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (HttpMethod.OPTIONS.matches(request.getMethod())) {
            return true;
        }

        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token = authHeader.split(" ")[1];

        try {
            Claims claims = Jwts.parser().setSigningKey(secret)
                    .parseClaimsJws(token).getBody();

            CallContext callContext = CallContext.builder()
                    .id(Long.valueOf(claims.getSubject()))
                    .login(claims.get("login", String.class))
                    .role(Role.valueOf(claims.get("role", String.class)))
                    .build();
            log.info("Пользователь [{}], роль[{}]", callContext.getLogin(), callContext.getRole());

            SecurityContext.set(callContext);
        } catch (Exception e){
            throw new BusinessRuntimeException(ErrorCodeEnum.BAD_TOKEN, e);
        }
        return AsyncHandlerInterceptor.super.preHandle(request, response, handler);
    }

}
