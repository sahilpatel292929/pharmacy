package com.ets.SecurePharmacy.util;

import com.ets.SecurePharmacy.exception.UnableToProcessException;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class BaseMethod {

    @Autowired
    private JwtUtil jwtUtil;

    public static String getPrinciple() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }

    public Long getUserId() throws Exception {
        RequestAttributes attribs = RequestContextHolder.getRequestAttributes();
        if (attribs != null) {
            String token = ((ServletRequestAttributes) attribs).getRequest().getHeader("Authorization");
            if (token != null && token.startsWith("Bearer ")) {
                token = token.substring(7);
                try {
                    return (long) jwtUtil.getUserId(token);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (ExpiredJwtException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("JWT token does not start with Bearer");
            }
        }
        throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
    }
}
