package com.jms.dboard.common.utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.jms.dboard.manage.vo.UserInfoVO;

public class ApplicationUtils {

    public static UserInfoVO getUser() {
        // Check authentication exists
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }

        // Get user
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserInfoVO) {
            return (UserInfoVO) principal;
        }

        throw new RuntimeException("Unable to get user. Unknown user type found in SecurityContextHolder's principal = " + ReflectionToStringBuilder.toString(principal));
    }

    public static String getUsername() {
    	UserInfoVO user = getUser();
        if (user == null) {
            return null;
        }

        return user.getUserName();
    }

    public static HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return null;
        }

        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        return request;
    }

    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    public static String getSessionId() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return null;
        }

        return requestAttributes.getSessionId();
    }

    public static String getIpAddress() {
        if (getRequest() == null) {
            return null;
        }
        return getRequest().getRemoteAddr();
    }

    public static void login(String username, String password) throws ServletException {
        getRequest().login(username, password);
    }

    public static void logout() throws ServletException {
        getRequest().logout();
    }

}