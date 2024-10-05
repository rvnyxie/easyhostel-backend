package com.easyhostel.backend.domain.repository.interfaces;

import com.easyhostel.backend.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role, String> {
}
