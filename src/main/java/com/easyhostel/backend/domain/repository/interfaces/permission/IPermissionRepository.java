package com.easyhostel.backend.domain.repository.interfaces.permission;

import com.easyhostel.backend.domain.entity.Permission;
import com.easyhostel.backend.domain.repository.interfaces.base.IBaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for Permission related modification methods
 *
 * @author Nyx
 */
@Repository
public interface IPermissionRepository extends IBaseRepository<Permission, Integer> {

    /**
     * Find Permission by Permission's name
     *
     * @param permissionName Permission's name
     * @return Optional Permission object
     * @author Nyx
     */
    Optional<Permission> findByPermissionName(String permissionName);

    /**
     * Check if Permission's name existed or not
     *
     * @param permissionName Permission's name
     * @return true if existed, false if not
     * @author Nyx
     */
    boolean existsByPermissionName(String permissionName);

}
