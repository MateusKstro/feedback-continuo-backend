package com.feedbackcontinuos.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.feedbackcontinuos.enums.Role;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class UsersEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS_SEQ")
    @SequenceGenerator(name = "USERS_SEQ", sequenceName = "seq_users", allocationSize = 1)
    @Column(name = "id_user")
    private Integer idUser;
    @Column(name = "user_name")
    private String userNamer;
    @Column(name = "user_role")
    private Role userRole;
    @Column(name = "email")
    private String email;
    @Column(name = "user_password")
    private String userPassword;
    @Column(name = "avatar")
    private byte[] avatar;

    @Column(name = "active")
    private boolean active;

    @JsonIgnore
    @OneToMany(mappedBy = "feedbackEntityGiven", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FeedBackEntity> feedbacksGiven;

    @JsonIgnore
    @OneToMany(mappedBy = "feedbackEntityReceived", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FeedBackEntity> feedBackEntities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return userPassword;
    }

    @Override
    public String getUsername() {
        return email;
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

    public boolean isActive() {
        return active;
    }
}
