package com.easyhostel.backend.domain.repository.interfaces;

import com.easyhostel.backend.domain.entity.RentPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRentPaymentRepository extends JpaRepository<RentPayment, String> {
}
