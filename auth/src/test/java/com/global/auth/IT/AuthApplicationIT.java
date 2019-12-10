package com.global.auth.IT;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthApplicationIT {

    @LocalServerPort
    private int port;

    @Test(expected = HttpClientErrorException.class)
    public void authorizationRequestShouldFall() {
        // PREDICATES
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);
        headers.set("Authorization", "Basic: wrongKey");

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        // FUNCTIONALITY & TESTS
        String url = getBaseUrl() + "token?grant_type=client_credentials";
        new RestTemplate().exchange(url, HttpMethod.POST, entity, String.class);
    }

    @Test
    public void authorizationRequestShouldPassAndReturnToken() throws JSONException {
        // PREDICATES
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);
        headers.set("Authorization", "Basic ZWR1Y2F0aW9uLXdlYi1jbGllbnQ6ZWR1Y2F0aW9uLXdlYi1jbGllbnQtc2VjcmV0");

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        // FUNCTIONALITY
        String url = getBaseUrl() + "token?grant_type=client_credentials";
        ResponseEntity<String> response = new RestTemplate().exchange(url, HttpMethod.POST, entity, String.class);

        // TESTS
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(new JSONObject(response.getBody()).get("access_token"));
    }

    private String getBaseUrl() {
        return "http://localhost:" + port + "/auth/oauth/";
    }

}
