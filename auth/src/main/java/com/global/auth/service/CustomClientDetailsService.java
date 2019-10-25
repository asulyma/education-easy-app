package com.global.auth.service;

import com.global.auth.model.ClientDetailsEntity;
import com.global.auth.repository.ClientDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientAlreadyExistsException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.ClientRegistrationService;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomClientDetailsService implements ClientDetailsService, ClientRegistrationService {

    @Autowired
    private ClientDetailsRepository repository;

    @Override
    public ClientDetails loadClientByClientId(String s) throws ClientRegistrationException {
        ClientDetailsEntity clientDetails = repository.findByClientId(s);
        if (clientDetails == null) {
            throw new RuntimeException("Client with id " + s + " can not be found!");
        }
        return clientDetails;
    }

    @Override
    @Transactional
    public void addClientDetails(ClientDetails details) throws ClientAlreadyExistsException {
        if (repository.findByClientId(details.getClientId()) != null) {
            throw new RuntimeException("Client with id " + details.getClientId() + " is already exist!");

        }
        ClientDetailsEntity entity = new ClientDetailsEntity();
        entity.setClientId(details.getClientId());
        entity.setResourceIds(details.getResourceIds());
        entity.setSecretRequired(details.isSecretRequired());
        entity.setClientSecret(NoOpPasswordEncoder.getInstance().encode(details.getClientSecret()));
        entity.setScoped(details.isScoped());
        entity.setScope(details.getScope());
        entity.setAuthorizedGrantTypes(details.getAuthorizedGrantTypes());
        entity.setRegisteredRedirectUri(details.getRegisteredRedirectUri());
        entity.setAuthorities(details.getAuthorities());
        entity.setAccessTokenValiditySeconds(details.getAccessTokenValiditySeconds());
        entity.setRefreshTokenValiditySeconds(details.getRefreshTokenValiditySeconds());
        entity.setAutoApprove(details.isAutoApprove("true"));
        //entity.setAdditionalInformation(details.getAdditionalInformation());

        repository.save(entity);
    }

    @Override
    @Transactional
    public void updateClientDetails(ClientDetails details) throws NoSuchClientException {
        ClientDetailsEntity entity = (ClientDetailsEntity) loadClientByClientId(details.getClientId());
        entity.setResourceIds(details.getResourceIds());
        entity.setScope(details.getScope());
        entity.setAuthorizedGrantTypes(details.getAuthorizedGrantTypes());
        entity.setRegisteredRedirectUri(details.getRegisteredRedirectUri());
        entity.setAuthorities(details.getAuthorities());
        entity.setAccessTokenValiditySeconds(details.getAccessTokenValiditySeconds());
        entity.setRefreshTokenValiditySeconds(details.getRefreshTokenValiditySeconds());
        //entity.setAdditionalInformation(details.getAdditionalInformation());
    }

    @Override
    @Transactional
    public void updateClientSecret(String clientId, String secret) throws NoSuchClientException {
        ClientDetailsEntity entity = (ClientDetailsEntity) loadClientByClientId(clientId);
        entity.setClientSecret(NoOpPasswordEncoder.getInstance().encode(secret));
    }

    @Override
    public void removeClientDetails(String s) throws NoSuchClientException {
        repository.deleteByClientId(s);
    }

    @Override
    public List<ClientDetails> listClientDetails() {
        return new ArrayList<>(repository.findAll());
    }
}
