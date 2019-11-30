/**
 * 
 */
package org.sudhanshu.demo.organization.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.sudhanshu.demo.organization.dto.OrganizationDTO;
import org.sudhanshu.demo.organization.service.OrganizationService;

/**
 * @author Sudhanshu Sharma
 *
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {

	@Override
	public Optional<OrganizationDTO> findById(Long organizationId) {
		OrganizationDTO organizationDTO = new OrganizationDTO();
		organizationDTO.setName("Suranshu");
		return Optional.of(organizationDTO);
	}

	@Override
	public List<OrganizationDTO> findAll() {
		OrganizationDTO organizationDTO1 = new OrganizationDTO();
		organizationDTO1.setName("Suranshu");
		OrganizationDTO organizationDTO2 = new OrganizationDTO();
		organizationDTO2.setName("Himanshu");

		List<OrganizationDTO> organizations = new ArrayList<>();
		organizations.add(organizationDTO1);
		organizations.add(organizationDTO2);

		return organizations;
	}

	@Override
	public OrganizationDTO save(OrganizationDTO organization) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(OrganizationDTO organization) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Long organizationId) {
		// TODO Auto-generated method stub
		return false;
	}

}
