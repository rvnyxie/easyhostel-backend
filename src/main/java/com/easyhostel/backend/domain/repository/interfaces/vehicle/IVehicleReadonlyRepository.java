package com.easyhostel.backend.domain.repository.interfaces.vehicle;

import com.easyhostel.backend.domain.entity.Vehicle;
import com.easyhostel.backend.domain.repository.interfaces.base.IBaseReadonlyRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Vehicle related readonly methods
 *
 * @author Nyx
 */
@Repository
public interface IVehicleReadonlyRepository extends IBaseReadonlyRepository<Vehicle, String> {
}
