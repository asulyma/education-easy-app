package com.global.education.IT;

import static java.util.Collections.emptyList;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import javax.servlet.http.HttpServletRequest;

import org.jetbrains.annotations.NotNull;
import org.junit.ClassRule;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.test.context.support.WithSecurityContextFactory;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.PostgreSQLContainer;

import lombok.*;


@ContextConfiguration(initializers = { EducationApplicationIT.Initializer.class })
public class EducationApplicationIT {

	private static final String POSTGRES_IMAGE = "mdillon/postgis:10";
	private static final String KAFKA_IMAGE_TAG = "5.5.2";
	private static final String DB_NAME = "education_app";
	private static final String USERNAME = "education-app";
	private static final String PASSWORD = "education-app";

	@ClassRule
	public static PostgreSQLContainer<?> postgreSQL = new PostgreSQLContainer<>(POSTGRES_IMAGE)
			.withDatabaseName(DB_NAME)
			.withUsername(USERNAME)
			.withPassword(PASSWORD);

	@ClassRule
	public static KafkaContainer kafka = new KafkaContainer(KAFKA_IMAGE_TAG);

	/**
	 * Need to provide and override existing properties inside of the application.yml
	 */
	static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
		@Override
		public void initialize(@NotNull ConfigurableApplicationContext context) {
			String[] properties = new String[6];
			properties[0] = "spring.datasource.url=" + postgreSQL.getJdbcUrl();
			properties[1] = "spring.datasource.username=" + postgreSQL.getUsername();
			properties[2] = "spring.datasource.password=" + postgreSQL.getPassword();
			properties[3] = "spring.jpa.hibernate.ddl-auto=none";
			properties[4] = "spring.kafka.bootstrap-servers=" + kafka.getBootstrapServers();
			properties[5] = "spring.mail.enabled=false";

			TestPropertyValues.of(properties).applyTo(context);
		}
	}

	/**
	 * Need to mock and override existing OAuth2 security authentication.
	 */
	static class WithMockOAuth2SecurityContextFactory implements WithSecurityContextFactory<WithMockOAuth2User> {
		@Override
		public SecurityContext createSecurityContext(WithMockOAuth2User customUser) {

			CustomUserDetails principal = new CustomUserDetails(customUser.name(), customUser.username());
			Authentication baseAuth = new UsernamePasswordAuthenticationToken(principal, "password", emptyList());

			HttpServletRequest request = new MockHttpServletRequest();
			request.setAttribute("OAuth2AuthenticationDetails.ACCESS_TOKEN_VALUE", MOCK_TOKEN);
			request.setAttribute("OAuth2AuthenticationDetails.ACCESS_TOKEN_TYPE", EMPTY);
			OAuth2AuthenticationDetails details = new OAuth2AuthenticationDetails(request);

			OAuth2Authentication oAuth2 = new OAuth2Authentication(null, baseAuth);
			oAuth2.setDetails(details);


			SecurityContext context = SecurityContextHolder.createEmptyContext();
			context.setAuthentication(oAuth2);
			return context;
		}
	}

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	private static class CustomUserDetails {
		private String name;
		private String username;
	}

	private static final String MOCK_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiZm9vIl0sInVzZXJfbmFtZSI6Ik1vY2tfVXNlciIsInNjb3BlIjpbIm1vY2stc2NvcGUiXSwidXNlclV1aWQiOiJhMDAwMGEwMC1hMDBhLTAwYTAtMGEwYS0wMDBhMGEwMDAwMDAiLCJleHAiOjE2MDE5MzA4MTcsImF1dGhvcml0aWVzIjpbIlJPTEVfTU9DS19VU0VSIl0sImp0aSI6ImIwMDAwYjAwLWIwMGItMDBiMC0wYjBiLTAwMGIwYjAwMDAwMCIsImNsaWVudF9pZCI6ImVkdWNhdGlvbi13ZWItbW9jay1jbGllbnQifQ.xsiBGfFd9fOQ9Bb6ZamTFko-OKEuMr_NWZskFHorqcY";

}
