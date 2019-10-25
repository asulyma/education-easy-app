package com.global.auth.service;

import com.global.auth.model.ClientDetailsEntity;
import com.global.auth.repository.ClientDetailsRepository;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Need to run single time to create default client for application
 */
@Service
public class ClientCreationInitService {

    @Autowired
    private ClientDetailsRepository repository;

    @PostConstruct
    public void init() {
        ClientDetailsEntity client = repository.findByClientId("web-client");
        if (client != null) {
            return;
        }

        ClientDetailsEntity clientDetails = new ClientDetailsEntity();
        clientDetails.setClientId("web-client");
        clientDetails.setClientSecret("{noop}web-client-secret");
        clientDetails.setSecretRequired(true);
        clientDetails.setResourceIds(Sets.newHashSet("foo"));
        clientDetails.setScope(Sets.newHashSet("read-foo"));
        clientDetails.setAuthorizedGrantTypes(Sets.newHashSet("authorization_code", "refresh_token",
                "password", "client_credentials"));
        clientDetails.setRegisteredRedirectUri(Sets.newHashSet("http://localhost:8082/resource-service"));
        clientDetails.setAuthorities(AuthorityUtils.createAuthorityList("ROLE_USER"));
        clientDetails.setAccessTokenValiditySeconds(60);
        clientDetails.setRefreshTokenValiditySeconds(14400);
        clientDetails.setAutoApprove(false);
        repository.save(clientDetails);
    }

}
