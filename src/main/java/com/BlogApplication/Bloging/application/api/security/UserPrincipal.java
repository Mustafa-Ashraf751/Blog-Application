package com.BlogApplication.Bloging.application.api.security;

import com.BlogApplication.Bloging.application.api.entity.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
public class UserPrincipal implements UserDetails {
  private Long id;
  private String firstName;
  private String lastName;
  private String userName;

  @JsonIgnore
  private String email;
  @JsonIgnore
  private String password;

  private Collection<? extends GrantedAuthority> authorities;

  public UserPrincipal(Long id, String firstName, String lastName, String userName, String email,
                       String password, Collection<? extends GrantedAuthority> authorities) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.userName = userName;
    this.email = email;
    this.password = password;
    if(authorities == null){
      this.authorities = null;
    }else{
      this.authorities = new ArrayList<>(authorities);
    }

  }

  public static UserPrincipal create(User user){
    List<GrantedAuthority> authorityList =
            user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName().name())).
                    collect(Collectors.toList());
    return new UserPrincipal(user.getId(),user.getFirstName(),user.getLastName(),
            user.getUsername(),user.getEmail(), user.getPassword(),authorityList);

  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities == null ? null : new ArrayList<>(authorities);
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return userName;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserPrincipal that = (UserPrincipal) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
