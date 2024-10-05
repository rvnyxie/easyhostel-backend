package com.easyhostel.backend.domain.repository.interfaces.roomamenity;

import com.easyhostel.backend.domain.entity.RoomAmenity;
import com.easyhostel.backend.domain.repository.interfaces.base.IBaseReadonlyRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for RoomAmenity related readonly methods
 *
 * @author Nyx
 */
@Repository
public interface IRoomAmenityReadonlyRepository extends IBaseReadonlyRepository<RoomAmenity, String> {
}
