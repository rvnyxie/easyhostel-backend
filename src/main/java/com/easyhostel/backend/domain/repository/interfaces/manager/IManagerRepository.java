package com.easyhostel.backend.domain.repository.interfaces.manager;

import com.easyhostel.backend.domain.entity.Manager;
import com.easyhostel.backend.domain.repository.interfaces.base.IBaseRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Manager related modification methods
 *
 * @author Nyx
 */
@Repository
public interface IManagerRepository extends IBaseRepository<Manager, String> {

    /**
     * Check if Manager's username existed or not
     *
     * @param username Manager's username
     * @return true if existed, false if not
     * @author Nyx
     */
    boolean existsByUsername(String username);

    /**
     * Check if Manager's email existed or not
     *
     * @param email Manager's email
     * @return true if existed, false if not
     * @author Nyx
     */
    boolean existsByEmail(String email);

}
