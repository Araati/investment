package com.vazgen.investment.model.entity;

import com.vazgen.investment.model.IdReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.With;

import javax.persistence.*;

@With
@Entity
@Table(name = "user_details")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsEntity implements IdReference {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "user_id", nullable = false)
    private long userId;

    @Column(name = "person_id")
    private String personId;

    @OneToOne(fetch = FetchType.LAZY)
    private UserEntity userEntity;

    public UserDetailsEntity(final long userId, final String personId) {
        this.userId = userId;
        this.personId = personId;
    }

    public UserDetailsEntity(final long userId) {
        this.userId = userId;
    }
}
