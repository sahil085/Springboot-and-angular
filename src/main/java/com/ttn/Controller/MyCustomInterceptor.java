package com.ttn.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyCustomInterceptor implements HandlerInterceptor {

    //unimplemented methods comes here. Define the following method so that it
    //will handle the request before it is passed to the controller.

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //your custom logic here.
        if (request instanceof HttpServletRequest) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication.isAuthenticated()) {
                HttpServletResponse httpServletResponse = (HttpServletResponse) response;
                httpServletResponse.sendRedirect("http://localhost:8080/success");
            } else {
                HttpServletResponse httpServletResponse = (HttpServletResponse) response;
                httpServletResponse.sendRedirect("http://localhost:8080/home");

            }
        }
        return true;
    }
}