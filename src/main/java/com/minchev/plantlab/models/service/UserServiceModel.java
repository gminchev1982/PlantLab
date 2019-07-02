package com.minchev.plantlab.models.service;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Data
@Getter
@Setter
@NoArgsConstructor
@Component
public class UserServiceModel extends BaseServiceModel {

    private String name;

    private String username;
    private String password;
    private String role;
    private String image;
    private MultipartFile images;
    private Set<RoleServiceModel> authorities;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<RoleServiceModel> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<RoleServiceModel> authorities) {
        this.authorities = authorities;
    }

}
