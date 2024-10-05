package com.easyhostel.backend.domain.repository.interfaces;

import com.easyhostel.backend.domain.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPermissionRepository extends JpaRepository<Permission, String> {
}
