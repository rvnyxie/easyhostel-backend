package com.easyhostel.backend.domain.repository.interfaces;

import com.easyhostel.backend.domain.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IManagerRepository extends JpaRepository<Manager, UUID> {
}
