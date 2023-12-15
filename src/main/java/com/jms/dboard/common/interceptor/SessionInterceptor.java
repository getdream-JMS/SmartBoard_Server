package com.jms.dboard.common.interceptor;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.mvc.WebContentInterceptor;

import com.jms.dboard.manage.vo.RoleInfoVO;
import com.jms.dboard.manage.vo.UserInfoVO;

public class SessionInterceptor extends WebContentInterceptor {

    /**
     * 세션에 계정정보(SessionVO)가 있는지 여부로 인증 여부를 체크한다. 계정정보(SessionVO)가 없다면, 로그인 페이지로 이동한다.
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException {
        UserInfoVO sessionVo = null;
        try {
            sessionVo = (UserInfoVO) request.getSession().getAttribute("sessionUserInfo");
            if (sessionVo != null && sessionVo.getUserId() != null) { // 세션이 있을 경우만 체크
                return true;
            } else {
//                modelAndView.addObject("msgCode", "세션이 만료되어 로그아웃 되었습니다. 다시 로그인 해주세요.");
//                modelAndView.addObject("returnUrl", "/login");
                response.sendRedirect("/login");
                return false;
//                throw new ModelAndViewDefiningException(modelAndView);
            }
        } catch (Exception e) {
//            modelAndView.addObject("msgCode", "세션이 만료되어 로그아웃 되었습니다. 다시 로그인 해주세요.");
            try {
				response.sendRedirect("/login");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            return false;
//            throw new ModelAndViewDefiningException(modelAndView);
        }
    }

    /**
     * 세션에 메뉴권한(SessionVO)이 있는지 여부로 메뉴권한 여부를 체크한다. 계정정보(SessionVO)가 없다면, 로그인 페이지로 이동한다.
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    	UserInfoVO sessionVo = null;
        String requestURI = request.getRequestURI();

        try {
        } catch (Exception e) { // 그 외 예외사항은 index로 이동
        	e.printStackTrace();
        }
    }
}