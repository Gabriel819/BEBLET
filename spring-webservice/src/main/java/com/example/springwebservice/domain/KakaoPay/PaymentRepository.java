package com.example.springwebservice.domain.KakaoPay;

import com.example.springwebservice.domain.inquiry.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
