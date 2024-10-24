package com.easyhostel.backend.domain.repository.interfaces.interior;

import com.easyhostel.backend.domain.entity.Interior;
import com.easyhostel.backend.domain.repository.interfaces.base.IBaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Interior related modification methods
 *
 * @author Nyx
 */
@Repository
public interface IInteriorRepository extends IBaseRepository<Interior, String> {

    /**
     * Check if Interior's name existed
     *
     * @param interiorName Interior's name
     * @return true if existed, false if not
     * @author Nyx
     */
    boolean existsByInteriorName(String interiorName);

}
