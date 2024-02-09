package com.app.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.app.entities.Address;
import com.app.entities.Locality;
import com.app.entities.UserEntity;
import com.app.enums.TypeOfAddress;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
class AddressDaoTest {
	@Autowired
	private LocalityDao locDao;
	
	@Autowired
	private AddressDao addDao;
	
	@Autowired
	private UserEntityDao userDao;

	@Test
	void test() {
		// adding directly by finding individual objects
//		Address address = new Address("24B","Bansal Hospital",TypeOfAddress.WORK,"Manisha Lake","Right from Hanuman Temple");
		Address address = new Address("34A","HabibGanj Road",TypeOfAddress.HOME,"Near Over Bridge","Take left from underbridge.");
		
		UserEntity user = userDao.findByFirstName("Khagendra").orElseThrow();
		Locality locality = locDao.findById(5L).orElseThrow();
		
		address.setUserInAddress(user);
		address.setLocality(locality);
		
		Address savedAdd = addDao.save(address);
		
		assertTrue(savedAdd.getId()>0);
		
		//adding by user
		
	}

}
