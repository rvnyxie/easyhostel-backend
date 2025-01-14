package com.easyhostel.backend.domain.repository.interfaces.rentpayment;

import com.easyhostel.backend.domain.entity.RentPayment;
import com.easyhostel.backend.domain.repository.interfaces.base.IBaseReadonlyRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for RentPayment related readonly methods
 *
 * @author Nyx
 */
@Repository
public interface IRentPaymentReadonlyRepository extends IBaseReadonlyRepository<RentPayment, String> {
}
