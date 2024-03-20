package com.lucatic.grupo2.app.pay;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lucatic.grupo2.app.pay.clientfeign.CheckUserEventFeignClient;

/**
 * Clase de test
 *
 * @author BlueDevTeams
 * @version v1.0.0
 * @since 20-03-2024
 */
@SpringBootTest
class LucaDancePayApplicationTests {

	/** Inyectar checkUserEventFeignClient */
	@Autowired
	private CheckUserEventFeignClient checkUserEventFeignClient;

	/** Funciona test */
	@Test
	void contextLoads() {
		Assertions.assertThat(true).isTrue();
	}

	/** Test obtener el nombre de un usuario por id correctamente */
	@Test
	void getUserNameByIdIfExists() {
		assertEquals("Juan", checkUserEventFeignClient.getNameUser(2L).getUserExistText());
	}

	/** Test lanzar excecion de un usuario por id si el usuario no existe */
	@Test
	void getUserNameByIdIfNotExists() {
		assertThrows(Exception.class, () -> checkUserEventFeignClient.getNameUser(100L).getUserExistText());
	}

	/** Test comprobar si un usuario-evento existe por identificadores */
	@Test
	void getCheckUserEventIfExistsByIds() {
		assertEquals(true, checkUserEventFeignClient.checkUserEvent(2L, 23L).isRespBool());
	}

	/** Test comprobar si un usuario-evento no existe por ids de usuario y evento */
	@Test
	void getCheckUserEventIfNotExistsByIds() {
		assertEquals(false, checkUserEventFeignClient.checkUserEvent(15L, 100L).isRespBool());
	}

}
