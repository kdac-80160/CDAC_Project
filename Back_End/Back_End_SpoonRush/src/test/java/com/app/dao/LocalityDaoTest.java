package com.app.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.app.entities.Locality;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
class LocalityDaoTest {
	@Autowired
	private LocalityDao dao;
	
	@Test
	void test() {
		List<Locality> list = List.of(new Locality("Shivaji Nagar", 410113, "Bhopal", "Madhya Pradesh"),
		        new Locality("TT Nagar", 410114, "Bhopal", "Madhya Pradesh"),
		        new Locality("Kohefiza", 410115, "Bhopal", "Madhya Pradesh"),
		        new Locality("Chhola Road", 410116, "Bhopal", "Madhya Pradesh"),
		        new Locality("Shahpura", 410117, "Bhopal", "Madhya Pradesh")
				);
		
		List<Locality> list2 = dao.saveAll(list);
		assertEquals(list.size(), list2.size());
	}

}
