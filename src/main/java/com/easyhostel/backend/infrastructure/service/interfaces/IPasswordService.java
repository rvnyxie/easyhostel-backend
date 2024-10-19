package com.easyhostel.backend.infrastructure.service.interfaces;

/**
 * Interface for Password Service
 *
 * @author Nyx
 */
public interface IPasswordService {

    /**
     * Encode raw password
     *
     * @param rawPassword Raw password
     * @return Encoded password
     * @author Nyx
     */
    String encodePassword(String rawPassword);

}
