package org.pb.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LoginUserDetail implements UserDetails {

    private static final long serialVersionUID = 5555358335233062142L;

    private String userName;
    private String password;
    private List<GrantedAuthority> roles;
    private boolean active;

    public LoginUserDetail(){}

    public LoginUserDetail(UserAuth userAuth) {
        this.userName = userAuth.getUserName();
        this.password = userAuth.getPassword();
        this.active = userAuth.isActive();
        this.roles = Stream.of(userAuth.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
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
        return active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return active;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
