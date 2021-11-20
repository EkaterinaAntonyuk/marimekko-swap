package com.marimekko.swap.demo;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class DemoUserInitializationFilter implements Filter {
    private final DemoUserGenerator demoUserGenerator;

    public DemoUserInitializationFilter(DemoUserGenerator demoUserGenerator) {
        this.demoUserGenerator = demoUserGenerator;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpSession session = request.getSession();
        if(session.getAttribute("user") == null) {
            session.setAttribute("user", demoUserGenerator.generateDemoUser());
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
