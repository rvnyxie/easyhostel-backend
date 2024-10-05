package com.easyhostel.backend.domain.repository.interfaces.manager;

import com.easyhostel.backend.domain.entity.Manager;
import com.easyhostel.backend.domain.repository.interfaces.base.IBaseRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Manager related modification methods
 *
 * @author Nyx
 */
@Repository
public interface IManagerRepository extends IBaseRepository<Manager, String> {
}
