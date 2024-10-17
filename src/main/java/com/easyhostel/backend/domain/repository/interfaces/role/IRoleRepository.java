package com.easyhostel.backend.domain.repository.interfaces.role;

import com.easyhostel.backend.domain.entity.Role;
import com.easyhostel.backend.domain.repository.interfaces.base.IBaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for Role related modification methods
 *
 * @author Nyx
 */
@Repository
public interface IRoleRepository extends IBaseRepository<Role, Integer> {

    /**
     * Check if Role's name already existed
     *
     * @param roleName Role's name
     * @return true if existed, false if not
     * @author Nyx
     */
    boolean existsByRoleName(String roleName);

}
