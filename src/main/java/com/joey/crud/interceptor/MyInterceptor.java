package com.joey.crud.interceptor;

import com.joey.crud.entity.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义拦截器，实现简单的登录拦截
 */
@Component
@Aspect
public class MyInterceptor {

    @Pointcut("within (com.joey.crud.controller..*) && !within(com.joey.crud.controller.admin.LoginController)")
    public void pointCut() {

    }

    @Around("pointCut()")
    public Object trackInfo(ProceedingJoinPoint joinPoint) throws Throwable {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            System.out.println("-----------用户未登录-----------");
            attributes.getResponse().sendRedirect("/login"); //转发到/login映射路径
        } else {
            System.out.println("-----------用户已登录-----------");
        }


        return joinPoint.proceed();
    }


}
