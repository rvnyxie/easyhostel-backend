package com.easyhostel.backend.domain.repository.interfaces.house;

import com.easyhostel.backend.domain.entity.House;
import com.easyhostel.backend.domain.repository.interfaces.base.IBaseReadonlyRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for House related readonly methods
 *
 * @author Nyx
 */
@Repository
public interface IHouseReadonlyRepository extends IBaseReadonlyRepository<House, String> {
}
