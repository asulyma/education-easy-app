package com.global.auth.config;

import com.global.auth.model.UserEntity;
import com.global.auth.service.UserService;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Configuration
public class JwtTokenConfig {

    @Autowired
    private JwtAccessTokenConverter accessTokenConverter;

    @Autowired
    private UserService userService;

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setTokenStore(jwtTokenStore());

        List<TokenEnhancer> enhancers = new ArrayList<>();
        enhancers.add(accessTokenConverter);

        //Some custom enhancer
        enhancers.add((accessToken, authentication) -> {
            final Authentication userAuthentication = authentication.getUserAuthentication();

            final DefaultOAuth2AccessToken defaultOAuth2AccessToken = (DefaultOAuth2AccessToken) accessToken;
            Set<String> existingScopes = new HashSet<>(defaultOAuth2AccessToken.getScope());
            if (userAuthentication != null) {
                //User has logged into system
                existingScopes.add("read-foo");
            } else {
                //service is trying to access system
                existingScopes.add("another-scope");
            }

            defaultOAuth2AccessToken.setScope(existingScopes);
            return defaultOAuth2AccessToken;
        });

        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        enhancerChain.setTokenEnhancers(enhancers);
        tokenServices.setTokenEnhancer(enhancerChain);

        return tokenServices;
    }

    @Bean
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        EducationJwtAccessTokenConverter converter = new EducationJwtAccessTokenConverter();
        converter.setSigningKey("123");
        return converter;
    }

    private class EducationJwtAccessTokenConverter extends JwtAccessTokenConverter {

        @Override
        public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
            if (authentication.getOAuth2Request().getGrantType().equalsIgnoreCase("password")) {
                String username = String.valueOf(((User) authentication.getPrincipal()).getUsername());
                UserEntity user = userService.loadUserInformation(username);
                Map<String, Object> additionalInfo = ImmutableMap.of("userUuid", user.getUuid());
                ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
            }
            accessToken = super.enhance(accessToken, authentication);
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(new HashMap<>());
            return accessToken;
        }
    }

}
