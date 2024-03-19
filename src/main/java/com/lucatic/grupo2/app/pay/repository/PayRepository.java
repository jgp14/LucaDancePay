package com.lucatic.grupo2.app.pay.repository;

import com.lucatic.grupo2.app.pay.models.Pay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayRepository extends JpaRepository<Pay, Long> {
}
