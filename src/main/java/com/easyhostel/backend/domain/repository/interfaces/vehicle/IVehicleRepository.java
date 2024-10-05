package com.easyhostel.backend.domain.repository.interfaces.vehicle;

import com.easyhostel.backend.domain.entity.Vehicle;
import com.easyhostel.backend.domain.repository.interfaces.base.IBaseRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Vehicle related modification methods
 *
 * @author Nyx
 */
@Repository
public interface IVehicleRepository extends IBaseRepository<Vehicle, String> {
}
