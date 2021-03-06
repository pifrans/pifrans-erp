package com.pifrans.modules.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pifrans.modules.ecommerce.models.PaymentSlip;

@Repository
public interface PaymentSlipRepository extends JpaRepository<PaymentSlip, Long> {

}
