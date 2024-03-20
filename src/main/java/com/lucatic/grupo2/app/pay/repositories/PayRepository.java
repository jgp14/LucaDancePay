package com.lucatic.grupo2.app.pay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucatic.grupo2.app.pay.models.Pay;

/**
 * Genera una interfaz que hereda metodos de accion en la base de datos,
 * gestiona objetos tipo Pay
 * 
 * @author BlueDevTeam
 * @version 1.0.0
 * @since 19-03-2024
 */
public interface PayRepository extends JpaRepository<Pay, Long> {
}
