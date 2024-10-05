package com.easyhostel.backend.domain.repository.interfaces.interior;

import com.easyhostel.backend.domain.entity.Interior;
import com.easyhostel.backend.domain.repository.interfaces.base.IBaseReadonlyRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Interior related readonly methods
 *
 * @author Nyx
 */
@Repository
public interface IInteriorReadonlyRepository extends IBaseReadonlyRepository<Interior, String> {
}
