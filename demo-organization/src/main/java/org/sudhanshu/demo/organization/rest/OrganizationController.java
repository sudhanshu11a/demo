/**
 * 
 */
package org.sudhanshu.demo.organization.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sudhanshu.demo.organization.dto.OrganizationDTO;
import org.sudhanshu.demo.organization.service.OrganizationService;

/**
 * @author Sudhanshu Sharma
 *
 */
@RestController
@RequestMapping(path = "/orgs")
public class OrganizationController {

	static final Logger logger = LoggerFactory.getLogger(OrganizationController.class);

	@Autowired
	private OrganizationService organizationService;

	@GetMapping("/{orgId}")
	public ResponseEntity<?> getOrganizationName(@PathVariable String orgId) {
		return organizationService.findById(orgId).map(organization -> {
			try {
				return ResponseEntity.ok().location(new URI("/orgs/" + orgId)).body(organization);
			} catch (URISyntaxException e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		}).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping()
	public ResponseEntity<?> getAllOrganizationName() {
		List<OrganizationDTO> organizations =  organizationService.findAll();
		
		try {
			return ResponseEntity.ok().location(new URI("/orgs")).body(organizations);
		} catch (URISyntaxException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PostMapping()
	public ResponseEntity<OrganizationDTO> saveOrganization(@RequestBody OrganizationDTO organization) {
		logger.info("Creating new Organization with name {} ", organization.getOrgName());

		// Create the new Organization
		OrganizationDTO newOrganization = organizationService.save(organization);

		try {
			return ResponseEntity.created(new URI("/organization/" + newOrganization.getOrgName())).body(newOrganization);
		} catch (URISyntaxException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	/**
	 * Update the organization with the specified ID
	 * 
	 * @param newOrganization		Organization details  
	 * @param id					organization ID
	 * @return
	 */
	@PutMapping("{id}")
	public ResponseEntity<?> updateOrganization(@RequestBody OrganizationDTO newOrganization, @PathVariable String id) {
		logger.info("Organization ID {} is updating.", id);

		Optional<OrganizationDTO> existingOrganization = organizationService.findById(id);

		return existingOrganization.map(o -> {
			logger.info("Organization ID {} exists", id);
			o.setOrgName(newOrganization.getOrgName());

			if (organizationService.update(o)) {
				//update the organization and return a OK response
				return ResponseEntity.ok().body(o);
			} else {
				return ResponseEntity.notFound().build();
			}
		}).orElse(ResponseEntity.notFound().build());
	}
	
	/**
	 * Delete the organization with the specified ID.
	 * 
	 * @param id		The Id of the organization to delete
	 * @return			A responseEntity with one of the following status code:
	 * 					200 OK if the delete was successful 
	 * 					404 Not Found if the the organization with the specified Id not found
	 * 					500 Internal Server Error if the error occurs during deletion
	 */
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteOrganization(@PathVariable String id){
		logger.info("Deleting the organization with ID : {} ", id);
		
		//Get the existing Organization 
		Optional<OrganizationDTO> existingOrganization = organizationService.findById(id);
		
		return existingOrganization.map(org -> {
			if(organizationService.delete(org.getUuid())) {
				return ResponseEntity.ok().build();
			}else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		}).orElse(ResponseEntity.notFound().build());
	}

}
