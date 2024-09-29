package com.easyhostel.backend.domain.repository.interfaces;

import com.easyhostel.backend.domain.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IVehicleRepository extends JpaRepository<Vehicle, UUID> {
}
