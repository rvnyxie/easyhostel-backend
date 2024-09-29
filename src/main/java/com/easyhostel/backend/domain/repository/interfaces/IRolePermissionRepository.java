package com.easyhostel.backend.domain.repository.interfaces;

import com.easyhostel.backend.domain.entity.RolePermission;
import com.easyhostel.backend.domain.entity.embedded.RolePermissionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRolePermissionRepository extends JpaRepository<RolePermission, RolePermissionId> {
}
