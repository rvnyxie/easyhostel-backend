package com.easyhostel.backend.domain.repository.interfaces.role;

import com.easyhostel.backend.domain.entity.Role;
import com.easyhostel.backend.domain.repository.interfaces.base.IBaseRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Role related modification methods
 *
 * @author Nyx
 */
@Repository
public interface IRoleRepository extends IBaseRepository<Role, String> {
}
