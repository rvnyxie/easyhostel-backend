package com.easyhostel.backend.domain.repository.interfaces.manager;

import com.easyhostel.backend.domain.entity.Manager;
import com.easyhostel.backend.domain.repository.interfaces.base.IBaseReadonlyRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Manager related readonly methods
 *
 * @author Nyx
 */
@Repository
public interface IManagerReadonlyRepository extends IBaseReadonlyRepository<Manager, String> {
}
