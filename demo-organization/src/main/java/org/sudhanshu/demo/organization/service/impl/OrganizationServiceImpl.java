package org.sudhanshu.demo.organization.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationServiceImpl.class);

    @Autowired
    OrganizationRepository organizationRepository;

    @Override
    public Optional<OrganizationDTO> findById(String id) {
        Optional<OrganizationDTO> dto = Optional.empty();
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException ex) {
            LOGGER.error("Exception in findById " + id + " with Exception " + ex.getMessage());
            return dto;
        }
        Optional<Organization> organization = organizationRepository.findById(uuid);

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
    public Optional<OrganizationDTO> save(OrganizationDTO dto) {
        dto.setId(null);
        Optional<Organization> organization = ObjectMapperUtils.map(dto, Organization.class);
        Optional<OrganizationDTO> organizationDTO = Optional.empty();
        if (organization.isPresent()) {
            Organization entity = organization.get();
            entity.setActive(true);
            entity.setCreatedBy(1L);
            entity = organizationRepository.saveAndFlush(entity);
            organizationDTO = ObjectMapperUtils.map(entity, OrganizationDTO.class);
        }
        return organizationDTO;
    }

    @Override
    public boolean update(OrganizationDTO organization) {
        Optional<OrganizationDTO> existingOrganization = findById(organization.getId());
        return existingOrganization.map(o -> {
            LOGGER.info("Organization ID {} exists", o.getId());
            o.setOrgName(organization.getOrgName());
            return true;
        }).orElse(false);
    }

    @Override
    public boolean delete(String organizationId) {
        // TODO Auto-generated method stub
        return false;
    }

}
