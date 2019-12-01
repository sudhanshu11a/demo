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

	/**
	 * Returns the Organization with the specified Id.
	 * 
	 * @param organizationId	ID of the organization to be retrieve 
	 * @return					The required organization if found
	 */
	Optional<OrganizationDTO> findById(String organizationId);

	/**
	 * Returns all the organization in the database
	 * 
	 * @return					All organizations in the database
	 */
	List<OrganizationDTO> findAll();

	/**
	 * Saves the specified organization into the database
	 * 
	 * @param organization		The organization to be save
	 * @return					The saved organization 
	 */
	OrganizationDTO save(OrganizationDTO organization);

	/**
	 * Update the specified organization, identified by its ID.
	 * 
	 * @param organization		The organization to update
	 * @return					True if the update succeeded, else false
	 */
	boolean update(OrganizationDTO organization);

	/**
	 * Deletes the organization with the specified ID.
	 * 
	 * @param organizationId	The organization to be deleted
	 * @return					True if the deletion is succeeded, else false
	 */
	boolean delete(String organizationId);

}
