package com.daniel.sipos.demoproject.securityconfig;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
class SecurityConfig {
  @Value("${googleJwkProvider}")
  private String googleJwkProvider;

  @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
  private String issuerUri;

  @Value("${audience}")
  private String audience;

  @Autowired
  CorsConfig corsConfig;

  @Bean
  public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/demo-project-app-websocket/**", "/h2-console/**", "/favicon.ico").permitAll()
        .anyRequest().authenticated()
        .and().cors().configurationSource(corsConfig.corsConfigurationSource())
        .and().headers().frameOptions().disable()
        .and().csrf().disable()
        .oauth2ResourceServer()
        .jwt()
        .decoder(jwtDecoder());
    return http.build();
  }

  @Bean
  public JwtDecoder jwtDecoder() {
    OAuth2TokenValidator<Jwt> audienceValidator = new AudienceValidator(audience);
    val withIssuer = JwtValidators.createDefaultWithIssuer(issuerUri);
    OAuth2TokenValidator<Jwt> withAudience = new DelegatingOAuth2TokenValidator<>(withIssuer,
        audienceValidator);
    val jwtDecoder = NimbusJwtDecoder.withJwkSetUri(googleJwkProvider).build();
    jwtDecoder.setJwtValidator(withAudience);
    return jwtDecoder;
  }
}