package com.global.auth.config;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.*;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.global.auth.service.UserService;
import com.google.common.collect.ImmutableMap;


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

		//Setup custom enhancer
		enhancers.add((accessToken, authentication) -> {
			Authentication userAuthentication = authentication.getUserAuthentication();
			DefaultOAuth2AccessToken defaultOAuth2AccessToken = (DefaultOAuth2AccessToken) accessToken;

			Set<String> existingScopes = new HashSet<>(defaultOAuth2AccessToken.getScope());
			if (userAuthentication != null) {
				//User (not client) has logged into system, but if you want to add scope to token too - use enhance method below
				existingScopes.add("user-scope");
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
				Map<String, Object> additionalInfo = ImmutableMap.of("userUuid", userService.loadUserUuid(username));
				((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
			}
			accessToken = super.enhance(accessToken, authentication);
			((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(new HashMap<>());
			return accessToken;
		}
	}

}
