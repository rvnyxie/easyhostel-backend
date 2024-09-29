package com.easyhostel.backend.domain.repository.interfaces;

import com.easyhostel.backend.domain.entity.Interior;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IInteriorRepository extends JpaRepository<Interior, UUID> {
}
