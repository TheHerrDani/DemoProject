package com.daniel.sipos.demoproject.securityconfig;

import lombok.val;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;

public class AudienceValidator implements OAuth2TokenValidator<Jwt> {

  public AudienceValidator(String audience) {
    this.audience = audience;
  }

  private final String audience;

  @Override
  public OAuth2TokenValidatorResult validate(Jwt jwt) {
    val audiences = jwt.getAudience();
    if (audiences.contains(audience)) {
      return OAuth2TokenValidatorResult.success();
    }
    val err = new OAuth2Error(OAuth2ErrorCodes.INVALID_TOKEN);
    return OAuth2TokenValidatorResult.failure(err);
  }

}
