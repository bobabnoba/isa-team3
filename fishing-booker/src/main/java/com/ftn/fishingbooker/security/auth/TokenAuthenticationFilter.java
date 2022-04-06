package com.ftn.fishingbooker.security.auth;

import com.ftn.fishingbooker.security.util.TokenUtils;
import com.ftn.fishingbooker.service.Impl.UserServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthenticationFilter extends OncePerRequestFilter {


    private TokenUtils tokenUtils;

    private UserDetailsService userDetailsService;

    protected final Log logger = LogFactory.getLog(getClass());

    public TokenAuthenticationFilter(TokenUtils tokenUtils, UserServiceImpl userService) {
        this.tokenUtils = tokenUtils;
        this.userDetailsService = userDetailsService;
    }

    /**
     * TokenAuthenticationFilter je ispred BasicAuthFilter
     * OncePerRequestFilter - tigeruje se na svaki request
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String email;
        String authToken = tokenUtils.getToken(request);
        try {
            if (authToken != null) {
                email = tokenUtils.getUsernameFromToken(authToken); //email == username
                if (email != null) {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(email);

                    if (tokenUtils.validateToken(authToken, userDetails)) {
                        TokenBasedAuthentication authentication = new TokenBasedAuthentication(userDetails);
                        authentication.setToken(authToken);
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            }
        } catch (ExpiredJwtException ex) {
            logger.debug("Token expired!");
        }

        // prosledi request dalje u sledeci filter
        filterChain.doFilter(request, response);
    }
}
