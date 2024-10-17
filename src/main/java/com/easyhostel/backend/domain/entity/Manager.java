package com.easyhostel.backend.domain.entity;

import com.easyhostel.backend.domain.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

/**
 * Manager entity
 *
 * @author Nyx
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
@Table(name = "manager")
public class Manager extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false)
    private String managerId;

//    @Column(updatable = false, unique = true)
    private String username;

    private String email;

    private String password;

    private String avatar;

    private String token;

    @ManyToOne(cascade = { CascadeType.MERGE })
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "manager", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private Set<ManagerHouse> managerHouses = new HashSet<>();

}
