package com.easyhostel.backend.domain.repository.interfaces.rolepermission;

import com.easyhostel.backend.domain.entity.RolePermission;
import com.easyhostel.backend.domain.entity.embedded.RolePermissionId;
import com.easyhostel.backend.domain.repository.interfaces.base.IBaseRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for RolePermission related modification methods
 *
 * @author Nyx
 */
@Repository
public interface IRolePermissionRepository extends IBaseRepository<RolePermission, RolePermissionId> {
}
