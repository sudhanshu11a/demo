/**
 * 
 */
package org.sudhanshu.demo.organization.service;

import java.util.List;
import java.util.Optional;

import org.sudhanshu.demo.organization.dto.OrganizationDTO;

/**
 * @author Sudhanshu Sharma
 *
 */
public interface OrganizationService {

	Optional<OrganizationDTO> findById(Long organizationId);

	List<OrganizationDTO> findAll();

	OrganizationDTO save(OrganizationDTO organization);

	boolean update(OrganizationDTO organization);

	boolean delete(Long organizationId);

}
