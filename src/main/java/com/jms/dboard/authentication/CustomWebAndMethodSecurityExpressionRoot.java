package com.jms.dboard.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;

import com.jms.dboard.common.utils.ApplicationEnvironmentUtils;

public class CustomWebAndMethodSecurityExpressionRoot extends WebAndMethodSecurityExpressionRoot {

    public CustomWebAndMethodSecurityExpressionRoot(Authentication a) {
        super(a);
    }

    public CustomWebAndMethodSecurityExpressionRoot(Authentication a, FilterInvocation fi) {
        super(a, fi);
    }

    public boolean isLocalhostEnvironment() {
        return ApplicationEnvironmentUtils.isLocalhostEnvironment();
    }

    public boolean isDevelopmentEnvironment() {
        return ApplicationEnvironmentUtils.isDevelopmentEnvironment();
    }

    public boolean isTestEnvironment() {
        return ApplicationEnvironmentUtils.isTestEnvironment();
    }

    public boolean isProductionEnvironment() {
        return ApplicationEnvironmentUtils.isProductionEnvironment();
    }
}