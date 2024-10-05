package com.easyhostel.backend.domain.repository.interfaces.room;

import com.easyhostel.backend.domain.entity.Room;
import com.easyhostel.backend.domain.repository.interfaces.base.IBaseReadonlyRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Room related readonly methods
 *
 * @author Nyx
 */
@Repository
public interface IRoomReadonlyRepository extends IBaseReadonlyRepository<Room, String> {
}
