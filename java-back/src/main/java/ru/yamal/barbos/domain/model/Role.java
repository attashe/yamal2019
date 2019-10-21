package ru.yamal.barbos.domain.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
  ROLE_ADMIN, ROLE_HELPER, ROLE_USER;

  public String getAuthority() {
    return name();
  }

}
