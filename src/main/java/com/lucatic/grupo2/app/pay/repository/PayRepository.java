package com.lucatic.grupo2.app.pay.repository;

import com.lucatic.grupo2.app.pay.models.Pay;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Clase repository UserRepository para las conexiones a BBDD. Extende de
 * JpaRepository.
 * 
 * @author BlueDevTeams
 * @version v1.0.0
 * @since 19-03-2024
 */
public interface PayRepository extends JpaRepository<Pay, Long> {
}
