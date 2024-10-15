package com.easyhostel.backend.domain.repository.interfaces.role;

import com.easyhostel.backend.domain.entity.Role;
import com.easyhostel.backend.domain.repository.interfaces.base.IBaseReadonlyRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Role related readonly methods
 *
 * @author Nyx
 */
@Repository
public interface IRoleReadonlyRepository extends IBaseReadonlyRepository<Role, String> {
}
