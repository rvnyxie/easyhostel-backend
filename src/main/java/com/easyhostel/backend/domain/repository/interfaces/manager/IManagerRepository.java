package com.easyhostel.backend.domain.repository.interfaces.manager;

import com.easyhostel.backend.domain.entity.Manager;
import com.easyhostel.backend.domain.repository.interfaces.base.IBaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for Manager related modification methods
 *
 * @author Nyx
 */
@Repository
public interface IManagerRepository extends IBaseRepository<Manager, String> {

    /**
     * Find Manager by username
     *
     * @param username Manager's username
     * @return Optional Manager
     * @author Nyx
     */
    Optional<Manager> findManagerByUsername(String username);

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
