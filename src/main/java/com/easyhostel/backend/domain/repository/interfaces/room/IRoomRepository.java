package com.easyhostel.backend.domain.repository.interfaces.room;

import com.easyhostel.backend.domain.entity.Room;
import com.easyhostel.backend.domain.repository.interfaces.base.IBaseRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Room related modification methods
 *
 * @author Nyx
 */
@Repository
public interface IRoomRepository extends IBaseRepository<Room, String> {
}
