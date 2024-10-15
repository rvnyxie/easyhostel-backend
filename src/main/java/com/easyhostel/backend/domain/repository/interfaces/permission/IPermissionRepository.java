package com.easyhostel.backend.domain.repository.interfaces.permission;

import com.easyhostel.backend.domain.entity.Permission;
import com.easyhostel.backend.domain.repository.interfaces.base.IBaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Permission related modification methods
 *
 * @author Nyx
 */
@Repository
public interface IPermissionRepository extends IBaseRepository<Permission, Integer> {
}
