package com.global.auth.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "client_details")
@NoArgsConstructor
@AllArgsConstructor
public class ClientDetailsEntity implements ClientDetails {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_id")
    private String clientId;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "client_resource", joinColumns = @JoinColumn(name = "client_details_id"))
    @Column(name = "resource")
    private Set<String> resourceIds = new HashSet<>();

    @Column(name = "secret_required")
    private boolean secretRequired;

    @Column(name = "client_secret")
    private String clientSecret;

    @Column(name = "scoped")
    private boolean scoped;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "client_scope", joinColumns = @JoinColumn(name = "client_details_id"))
    @Column(name = "scope")
    private Set<String> scope;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "client_grant_type", joinColumns = @JoinColumn(name = "client_details_id"))
    @Column(name = "grant_type")
    private Set<String> authorizedGrantTypes = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "client_registered_redirect_uri", joinColumns = @JoinColumn(name = "client_details_id"))
    @Column(name = "registered_redirect_uri")
    private Set<String> registeredRedirectUri = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "client_authorities", joinColumns = @JoinColumn(name = "client_details_id"))
    @Column(name = "authority")
    private Collection<GrantedAuthority> authorities = new ArrayList<>();

    @Column(name = "access_token_validity_seconds")
    private Integer accessTokenValiditySeconds;

    @Column(name = "refresh_token_validity_seconds")
    private Integer refreshTokenValiditySeconds;

    @Column(name = "auto_approve")
    private boolean autoApprove;

    //    @ElementCollection(fetch = FetchType.EAGER)
    //    @CollectionTable(name = "additional_info")
    //    @MapKeyColumn(name = "info_key")
    //    @Column(name = "additional_info_id")
    //private Map<String, Object> additionalInformation = new HashMap<>();

    @Override
    public boolean isAutoApprove(String s) {
        return autoApprove;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        //Empty according to the comments above
        return Collections.emptyMap();
    }
}
