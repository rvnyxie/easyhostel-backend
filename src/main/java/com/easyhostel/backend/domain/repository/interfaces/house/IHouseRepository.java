package com.easyhostel.backend.domain.repository.interfaces.house;

import com.easyhostel.backend.domain.entity.House;
import com.easyhostel.backend.domain.repository.interfaces.base.IBaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository interface for House related modification methods
 *
 * @author Nyx
 */
@Repository
public interface IHouseRepository extends IBaseRepository<House, String> {
}
