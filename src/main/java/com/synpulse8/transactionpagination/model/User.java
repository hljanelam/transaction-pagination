package com.synpulse8.transactionpagination.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Data
//@NoArgsConstructor
//@AllArgsConstructor
@Document(collection = "user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "Unique ID of user")
    private String id;

    @ApiModelProperty(value = "Username")
    @NonNull
    private String username;

    @ApiModelProperty(value = "Name")
    @NonNull
    private String name;

    @ApiModelProperty(value = "Password")
    @JsonIgnore
    @NonNull
    private String password;

    @ApiModelProperty(value = "Email")
    @Indexed(unique = true)
    @NonNull
    private String email;

    @ApiModelProperty(value = "Account IBAN")
    @NonNull
    private Set<String> accountIban;

//    @ApiModelProperty(value = "User roles")
//    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.EMPTY_LIST;
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
}
