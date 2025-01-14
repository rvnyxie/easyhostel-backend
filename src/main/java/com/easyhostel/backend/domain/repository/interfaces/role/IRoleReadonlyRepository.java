package com.easyhostel.backend.domain.repository.interfaces.role;

import com.easyhostel.backend.domain.entity.Role;
import com.easyhostel.backend.domain.repository.interfaces.base.IBaseReadonlyRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for Role related readonly methods
 *
 * @author Nyx
 */
@Repository
public interface IRoleReadonlyRepository extends IBaseReadonlyRepository<Role, Integer> {

    /**
     * Find Role by Role's name
     *
     * @param roleName Role's name
     * @return Optional Role object
     * @author Nyx
     */
    Optional<Role> findRoleByRoleName(String roleName);

}
