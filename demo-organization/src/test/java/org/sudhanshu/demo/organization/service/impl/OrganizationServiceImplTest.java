package org.sudhanshu.demo.organization.service.impl;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.doReturn;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.internal.matchers.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.sudhanshu.demo.organization.dto.OrganizationDTO;
import org.sudhanshu.demo.organization.entity.Organization;
import org.sudhanshu.demo.organization.repository.OrganizationRepository;
import org.sudhanshu.demo.organization.service.OrganizationService;
import org.sudhanshu.demo.organization.utils.ObjectMapperUtils;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class OrganizationServiceImplTest {
	
	@Autowired
	private OrganizationService service;
	
	@MockBean
	private OrganizationRepository repository;

	@Test
	void testFindById() {
		
		//Setup of mock
		String id = "8c6415f6-e39f-4d4d-9812-46fac05e14f7";
		Organization mockOrg = new Organization(id, "TestOrganization", "Test", 1l, new Date(), 1l, new Date(), true);
		doReturn(Optional.of(mockOrg)).when(repository).findByUuid(UUID.fromString(id));
		
		//MockDTO conversion
		Optional<OrganizationDTO> mockDTO = ObjectMapperUtils.map(mockOrg, OrganizationDTO.class);
		doReturn(mockDTO).when(ObjectMapperUtils.class);
		ObjectMapperUtils.map(mockOrg, OrganizationDTO.class);
		
		//Execute the service call
		Optional<OrganizationDTO> result = service.findById(id);
		
		//Assert the response
		Assertions.assertTrue(result.isPresent(), "Result was not found");
		Assertions.assertSame(result.get(), mockDTO.get(), "Result not match");
	}

	@Test
	void testFindAll() {
		
		//Setup of mock
		
				//Execute the service call
				
				//Assert the response
		
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testSave() {
		//Setup of mock
		
		//Execute the service call
				
		//Assert the response
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testUpdate() {
		//Setup of mock
		
		//Execute the service call
						
		//Assert the response
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testDelete() {
		//Setup of mock
		
		//Execute the service call
						
		//Assert the response
		fail("Not yet implemented"); // TODO
	}

}
