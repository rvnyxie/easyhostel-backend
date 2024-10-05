package com.easyhostel.backend.domain.repository.interfaces.rentpayment;

import com.easyhostel.backend.domain.entity.RentPayment;
import com.easyhostel.backend.domain.repository.interfaces.base.IBaseRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for RentPayment related modification methods
 *
 * @author Nyx
 */
@Repository
public interface IRentPaymentRepository extends IBaseRepository<RentPayment, String> {
}
