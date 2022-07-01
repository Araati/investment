package com.vazgen.investment.model.entity;

import com.vazgen.investment.security.permission.Authority;
import com.vazgen.investment.model.IdReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@With
@Entity
@Table(name = "\"user\"")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
public class UserEntity implements IdReference {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "email_confirmed", nullable = false)
    private boolean emailConfirmed;

    @Column(name = "username")
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_authorities", joinColumns = @JoinColumn(name = "user_id"))
    private List<UserAuthorityEntity> authorities;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public UserEntity(
            final String username,
            final String email,
            final String password,
            final List<Authority> authorities
    ) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities.stream().map(UserAuthorityEntity::new).collect(Collectors.toList());
        this.enabled = true;
        this.emailConfirmed = true;//todo: OTP password for confirmation
    }
}
