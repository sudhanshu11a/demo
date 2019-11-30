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
import org.springframework.web.bind.annotation.RestController;
import org.sudhanshu.demo.organization.dto.OrganizationDTO;
import org.sudhanshu.demo.organization.service.OrganizationService;

/**
 * @author Sudhanshu Sharma
 *
 */
@RestController
public class OrganizationController {

	static final Logger logger = LoggerFactory.getLogger(OrganizationController.class);

	@Autowired
	private OrganizationService organizationService;

	@GetMapping("/name/{orgId}")
	public ResponseEntity<?> getOrganizationName(@PathVariable long orgId) {
		return organizationService.findById(orgId).map(organization -> {
			try {
				return ResponseEntity.ok().location(new URI("/name/" + orgId)).body(organization);
			} catch (URISyntaxException e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		}).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/name")
	public ResponseEntity<?> getAllOrganizationName() {
		List<OrganizationDTO> organizations =  organizationService.findAll();
		
		try {
			return ResponseEntity.ok().location(new URI("/name")).body(organizations);
		} catch (URISyntaxException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PostMapping("/organization")
	public ResponseEntity<OrganizationDTO> saveOrganization(@RequestBody OrganizationDTO organization) {
		logger.info("Creating new Organization with name {} ", organization.getName());

		// Create the new Organization
		OrganizationDTO newOrganization = organizationService.save(organization);

		try {
			return ResponseEntity.created(new URI("/organization/" + newOrganization.getName())).body(newOrganization);
		} catch (URISyntaxException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@PutMapping("/organization/{id}")
	public ResponseEntity<?> updateOrganization(@RequestBody OrganizationDTO newOrganization, @PathVariable Long id) {
		logger.info("Organization ID {} is updating.", id);

		Optional<OrganizationDTO> existingOrganization = organizationService.findById(id);

		return existingOrganization.map(o -> {
			logger.info("Organization ID {} exists", id);
			o.setName(newOrganization.getName());

			if (organizationService.update(o)) {
				//update the organization and return a OK response
				return ResponseEntity.ok().body(o);
			} else {
				return ResponseEntity.notFound().build();
			}
		}).orElse(ResponseEntity.notFound().build());
	}
	
//	@DeleteMapping("/organization/{id}")
//	public ResponseEntity<?> deleteOrganization(@PathVariable Long id){
//		
//		
//		
//		return null;
//	}

}
