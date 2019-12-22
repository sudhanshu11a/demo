/**
 * 
 */
package org.sudhanshu.demo.organization.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sudhanshu.demo.organization.dto.OrganizationDTO;
import org.sudhanshu.demo.organization.entity.Organization;
import org.sudhanshu.demo.organization.repository.OrganizationRepository;
import org.sudhanshu.demo.organization.service.OrganizationService;
import org.sudhanshu.demo.organization.utils.ObjectMapperUtils;

/**
 * @author Sudhanshu Sharma
 *
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {

	@Autowired
	OrganizationRepository organizationRepository;

	@Override
	public Optional<OrganizationDTO> findById(String id) {
		Optional<OrganizationDTO> dto= Optional.empty();
		
		Optional<Organization> organization = organizationRepository.findById(UUID.fromString(id));
		
		if (organization.isPresent()) {
			return ObjectMapperUtils.map(organization.get(), OrganizationDTO.class);
		} 
		
		return dto;
	}

	@Override
	public List<OrganizationDTO> findAll() {
		List<Organization> organizations = organizationRepository.findAll();
		return ObjectMapperUtils.mapAll(organizations, OrganizationDTO.class);
	}

	@Override
	public OrganizationDTO save(OrganizationDTO dto) {
		dto.setId(null);
		Optional<Organization> organization = ObjectMapperUtils.map(dto, Organization.class);
		Optional<OrganizationDTO> organizationDTO = Optional.empty();
		if(organization.isPresent()) {
			Organization entity = organization.get();
			entity.setActive(true);
			entity.setCreatedBy(1l);
			entity = organizationRepository.saveAndFlush(entity);
			organizationDTO = ObjectMapperUtils.map(entity, OrganizationDTO.class);
		}
		return organizationDTO.isPresent()?organizationDTO.get():null;
	}

	@Override
	public boolean update(OrganizationDTO organization) {
		//OrganizationDTO organizationDTO1 = new OrganizationDTO();
		//organizationDTO1.setName("Suranshu");
		return true;
	}

	@Override
	public boolean delete(String organizationId) {
		// TODO Auto-generated method stub
		return false;
	}

}
