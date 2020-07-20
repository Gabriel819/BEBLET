package com.example.springwebservice.domain.cabinet;

import com.example.springwebservice.domain.inquiry.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CabinetRepository extends JpaRepository<Cabinet, Long> {
}
