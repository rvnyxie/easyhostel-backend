package com.easyhostel.backend.domain.repository.interfaces.rolepermission;

import com.easyhostel.backend.domain.entity.RolePermission;
import com.easyhostel.backend.domain.entity.embedded.RolePermissionId;
import com.easyhostel.backend.domain.repository.interfaces.base.IBaseReadonlyRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for RolePermission related readonly methods
 *
 * @author Nyx
 */
@Repository
public interface IRolePermissionReadonlyRepository extends IBaseReadonlyRepository<RolePermission, RolePermissionId> {
}
