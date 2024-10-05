package com.easyhostel.backend.domain.repository.interfaces.roomamenity;

import com.easyhostel.backend.domain.entity.RoomAmenity;
import com.easyhostel.backend.domain.repository.interfaces.base.IBaseRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for RoomAmenity related modification methods
 *
 * @author Nyx
 */
@Repository
public interface IRoomAmenityRepository extends IBaseRepository<RoomAmenity, String> {
}
