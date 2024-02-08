package com.app.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import com.app.entities.UserEntity;
import com.app.enums.UserRole;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
class UserEntityDaoTest {
	// dep
	@Autowired
	private UserEntityDao userRepo;

	@Autowired
	private PasswordEncoder enc;

	@Test
	void testAddUsers() {
		List<UserEntity> list = List.of(
				new UserEntity("Mac", "Thakur", "mac@gmail.com", enc.encode("12345"),"8349703527", UserRole.ROLE_MANAGER,LocalDateTime.now()),
				new UserEntity("Khagendra", "Baraskar", "khags@gmail.com", enc.encode("2345"),"6265168982", UserRole.ROLE_CUSTOMER,LocalDateTime.now()),
				new UserEntity("Siddu", "Tiwari", "siddu@gmail.com", enc.encode("1345"),"9826332239", UserRole.ROLE_DELIVERY,LocalDateTime.now()));
		List<UserEntity> list2 = userRepo.saveAll(list);
		assertEquals(3, list2.size());

	}

}
