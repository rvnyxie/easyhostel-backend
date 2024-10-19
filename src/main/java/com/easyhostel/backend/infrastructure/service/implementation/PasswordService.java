package com.easyhostel.backend.infrastructure.service.implementation;

import com.easyhostel.backend.infrastructure.service.interfaces.IPasswordService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Implementation for Password Service using BCryptPasswordEncoder
 *
 * @author Nyx
 */
@Service
public class PasswordService implements IPasswordService {

    private final BCryptPasswordEncoder passwordEncoder;

    public PasswordService(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }
}
