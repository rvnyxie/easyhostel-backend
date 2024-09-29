package com.easyhostel.backend.domain.repository.interfaces;

import com.easyhostel.backend.domain.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IContractRepository extends JpaRepository<Contract, UUID> {
}
