package com.codechallenge.product.uaa.model.entity;

import com.codechallenge.product.sales.model.entity.Provider;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Accessors;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Accessors(chain = true)
@Document("userInfo")
public class UserInfo implements UserDetails {

    @Id
    private ObjectId id;
    private String username;

    @JsonIgnore
    private String hashedPassword;

    private Boolean active;
    private List<Role> roles;

    private String providerCompanyName;

    @Override
    @Transient
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    @Transient
    public String getPassword() {
        return hashedPassword;
    }

    @Override
    @Transient
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    @Transient
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    @Transient
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    @Transient
    public boolean isEnabled() {
        return active;
    }
}
