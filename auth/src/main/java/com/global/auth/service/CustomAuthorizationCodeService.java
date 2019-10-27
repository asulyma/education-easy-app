package com.global.auth.service;

import com.global.auth.repository.AuthorizationCodeRepository;
import com.global.auth.model.AuthorizationCodeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.RandomValueAuthorizationCodeServices;
import org.springframework.stereotype.Service;

@Service
public class CustomAuthorizationCodeService extends RandomValueAuthorizationCodeServices {

    @Autowired
    private AuthorizationCodeRepository repository;

    @Override
    protected void store(String s, OAuth2Authentication oAuth2Authentication) {
        AuthorizationCodeEntity entity = new AuthorizationCodeEntity();
        entity.setCode(s);
        entity.setAuthentication(oAuth2Authentication);
        repository.save(entity);
    }

    @Override
    protected OAuth2Authentication remove(String s) {
        OAuth2Authentication authentication = null;
        AuthorizationCodeEntity authorizationCode = repository.findByCode(s);
        if (authorizationCode != null) {
            authentication = authorizationCode.getAuthentication();
        }
        return authentication;
    }
}
