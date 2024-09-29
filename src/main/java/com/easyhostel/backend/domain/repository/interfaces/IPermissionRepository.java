package com.easyhostel.backend.domain.repository.interfaces;

import com.easyhostel.backend.domain.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IPermissionRepository extends JpaRepository<Permission, UUID> {
}
