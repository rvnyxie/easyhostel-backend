package com.easyhostel.backend.domain.repository.interfaces;

import com.easyhostel.backend.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IRoleRepository extends JpaRepository<Role, UUID> {
}
