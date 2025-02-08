package com.knowit.gymintellect.gym_member.gym_member.interceptor;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.knowit.gymintellect.gym_member.gym_member.service.MembershipPlanJoinService;

@Component
public class MembershipAccessInterceptor implements HandlerInterceptor {

    @Autowired
    private MembershipPlanJoinService membershipService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        Long memberId = (Long) request.getSession().getAttribute("memberId");

        if (memberId == null || !membershipService.isMemberAllowed(memberId)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied. Membership Expired.");
            return false;
        }
        return true;
    }
}
