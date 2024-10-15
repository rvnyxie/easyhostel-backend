package com.easyhostel.backend.domain.repository.interfaces.permission;

import com.easyhostel.backend.domain.entity.Permission;
import com.easyhostel.backend.domain.repository.interfaces.base.IBaseReadonlyRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Permission related readonly methods
 *
 * @author Nyx
 */
@Repository
public interface IPermissionReadonlyRepository extends IBaseReadonlyRepository<Permission, Integer> {
}
