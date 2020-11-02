package com.global.auth.IT;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.jetbrains.annotations.NotNull;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.containers.PostgreSQLContainer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = { AuthApplicationIT.Initializer.class })
public class AuthApplicationIT {

	private static final String IMAGE = "mdillon/postgis:10";
	private static final String DB_NAME = "education_auth";
	private static final String USERNAME = "education-auth";
	private static final String PASSWORD = "education-auth";

	@ClassRule
	public static PostgreSQLContainer<?> postgreSQL = new PostgreSQLContainer<>(IMAGE).withDatabaseName(DB_NAME)
			.withUsername(USERNAME)
			.withPassword(PASSWORD);

	@LocalServerPort
	private int port;

	@Test(expected = HttpClientErrorException.class)
	public void authorizationRequestShouldFall() {
		// PREDICATES
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Basic: wrongKey");

		HttpEntity<String> entity = new HttpEntity<>(null, headers);

		// FUNCTIONALITY & TESTS
		String url = getBaseUrl() + "oauth/token?grant_type=client_credentials";
		new RestTemplate().exchange(url, HttpMethod.POST, entity, String.class);
	}

	@Test
	public void shouldAuthorizeViaClient() throws JsonProcessingException {
		// PREDICATES
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Basic ZWR1Y2F0aW9uLXdlYi1jbGllbnQ6ZWR1Y2F0aW9uLXdlYi1jbGllbnQtc2VjcmV0");

		HttpEntity<String> entity = new HttpEntity<>(null, headers);

		// FUNCTIONALITY
		String url = getBaseUrl() + "oauth/token?grant_type=client_credentials";
		ResponseEntity<String> response = new RestTemplate().exchange(url, HttpMethod.POST, entity, String.class);

		// TESTS
		assertEquals(200, response.getStatusCodeValue());
		ResponseObject data = new ObjectMapper().readValue(response.getBody(), ResponseObject.class);

		assertEquals("standard-scope", data.getScope());
		assertEquals("bearer", data.getToken_type());
		assertNotNull(data.getAccess_token());
	}

	@Test
	public void shouldAuthorizeViaUser() throws JsonProcessingException {
		// PREDICATES
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Basic ZWR1Y2F0aW9uLXdlYi1jbGllbnQ6ZWR1Y2F0aW9uLXdlYi1jbGllbnQtc2VjcmV0");

		HttpEntity<String> entity = new HttpEntity<>(null, headers);

		// FUNCTIONALITY
		String url = getBaseUrl() + "oauth/token?grant_type=password&username=john&password=john";
		ResponseEntity<String> response = new RestTemplate().exchange(url, HttpMethod.POST, entity, String.class);

		// TESTS
		assertEquals(200, response.getStatusCodeValue());
		ResponseObject data = new ObjectMapper().readValue(response.getBody(), ResponseObject.class);

		assertEquals("standard-scope user-scope", data.getScope());
		assertEquals("bearer", data.getToken_type());
		assertNotNull(data.getAccess_token());
	}

	private String getBaseUrl() {
		return "http://localhost:" + port + "/auth/";
	}

	static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
		@Override
		public void initialize(@NotNull ConfigurableApplicationContext context) {
			String[] properties = new String[4];
			properties[0] = "spring.datasource.url=" + postgreSQL.getJdbcUrl();
			properties[1] = "spring.datasource.username=" + postgreSQL.getUsername();
			properties[2] = "spring.datasource.password=" + postgreSQL.getPassword();
			properties[3] = "spring.jpa.hibernate.ddl-auto=none";

			TestPropertyValues.of(properties).applyTo(context);
		}
	}

	@Getter
	@Setter
	@NoArgsConstructor
	private static final class ResponseObject {
		private String access_token;
		private String refresh_token;
		private String token_type;
		private String expires_in;
		private String scope;
	}

}
