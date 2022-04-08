package com.ftn.fishingbooker.security.auth;

import com.ftn.fishingbooker.security.util.TokenUtils;
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

    public TokenAuthenticationFilter(TokenUtils tokenUtils, UserDetailsService userService) {
        this.tokenUtils = tokenUtils;
        this.userDetailsService = userService;
    }

    /**
     * TokenAuthenticationFilter je ispred BasicAuthFilter
     * OncePerRequestFilter - tigeruje se na svaki request
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String email;
        // 1. Preuzimanje JWT tokena iz zahteva
        String authToken = tokenUtils.getToken(request);
        try {
            if (authToken != null) {
                // 2. Citanje korisnickog imena iz tokena tj u nasem slucaju metoda ce vratiti email
                email = tokenUtils.getUsernameFromToken(authToken);
                if (email != null) {
                    // 3. Preuzimanje korisnika na osnovu email-a
                    UserDetails userDetails = userDetailsService.loadUserByUsername(email);

                    // 4. Provera da li je prosledjeni token validan
                    if (tokenUtils.validateToken(authToken, userDetails)) {

                        // 5. Kreiraj autentifikaciju
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
