package com.security.securitytestdemo.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MyService {
    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
