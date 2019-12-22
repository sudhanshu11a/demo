package org.sudhanshu.demo.organization.service.impl;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.sudhanshu.demo.organization.repository.OrganizationRepository;
import org.sudhanshu.demo.organization.service.OrganizationService;

class OrganizationServiceImplTest {
	
	@Autowired
	private OrganizationService service;
	
	@MockBean
	private OrganizationRepository repository;

	@Test
	void testFindById() {
		
		
		
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testFindAll() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testSave() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testUpdate() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testDelete() {
		fail("Not yet implemented"); // TODO
	}

}
