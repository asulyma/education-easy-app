package com.global.auth.service;

import com.global.auth.model.ClientDetailsEntity;
import com.global.auth.repository.ClientDetailsRepository;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Need to run single time to create default client for application
 */
@Slf4j
@Service
public class ClientCreationInitService {

    private static final String DEFAULT_CLIENT = "education-web-client";

    @Autowired
    private ClientDetailsRepository repository;

    @PostConstruct
    public void init() {
        ClientDetailsEntity client = repository.findByClientId(DEFAULT_CLIENT);
        if (client != null) {
            return;
        }

        log.info("OAUTH2 SERVER HAS BEEN STARTED FIRST TIME...");
        log.info("GOING TO CREATE DEFAULT OAUTH2 CLIENT: {}", DEFAULT_CLIENT);

        ClientDetailsEntity clientDetails = new ClientDetailsEntity();
        clientDetails.setClientId(DEFAULT_CLIENT);
        clientDetails.setClientSecret("{noop}education-web-client-secret");
        clientDetails.setSecretRequired(true);
        clientDetails.setResourceIds(Sets.newHashSet("foo"));
        clientDetails.setScope(Sets.newHashSet("standard-scope"));
        clientDetails.setAuthorizedGrantTypes(Sets.newHashSet("authorization_code", "refresh_token",
                "password", "client_credentials"));
        clientDetails.setRegisteredRedirectUri(Sets.newHashSet("http://localhost:8082/resource-service"));
        clientDetails.setAuthorities(AuthorityUtils.createAuthorityList("ROLE_CLIENT"));
        clientDetails.setAccessTokenValiditySeconds(60);
        clientDetails.setRefreshTokenValiditySeconds(14400);
        clientDetails.setAutoApprove(false);
        repository.save(clientDetails);

        log.info("DEFAULT OAUTH2 CLIENT HAS BEEN CREATED");
    }

}
