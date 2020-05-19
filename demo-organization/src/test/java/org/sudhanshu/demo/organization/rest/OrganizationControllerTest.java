/**
 * 
 */
package org.sudhanshu.demo.organization.rest;

import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.sudhanshu.demo.organization.dto.OrganizationDTO;
import org.sudhanshu.demo.organization.service.OrganizationService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.HttpHeaders;

/**
 * @author Sudhanshu Sharma
 *
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class OrganizationControllerTest {

	@MockBean
	private OrganizationService service;

	@Autowired
	private MockMvc mockMvc;
	
	private String mock_id = "8c6415f6-e39f-4d4d-9812-46fac05e14f7"; 

	/**
	 * Test method for
	 * {@link org.sudhanshu.demo.organization.rest.OrganizationController#getOrganizationName(long)}.
	 * 
	 * @throws Exception
	 */
	@Test
	@DisplayName("GET /orgs/id - FOUND")
	void testGetOrganizationByNameFound() throws Exception {

		// Setup our mocked service
		OrganizationDTO mockOrganization = new OrganizationDTO(mock_id, new Date(), new Date(), "Suranshu", "S");
		doReturn(Optional.of(mockOrganization)).when(service).findById(mock_id);

		// Execute the GET Request
		mockMvc.perform(get("/orgs/{id}", mock_id))
				// validate the response code and content type
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				// Validate the headers
				.andExpect(header().string(HttpHeaders.LOCATION, "/orgs/"+mock_id))
				// validate the returned fields
				.andExpect(jsonPath("$.orgName", is("Suranshu")));

	}

	@Test
	@DisplayName("GET /orgs/id - Not Found")
	void testGetOranizationByNameNotFound() throws Exception {
		// Setup our mocked service
		doReturn(Optional.empty()).when(service).findById("id");
		// Execute the GET request
		mockMvc.perform(get("/orgs/{id}", "id"))
				// Validate that we get a 404 Not Found response
				.andExpect(status().isNotFound());

	}

	@Test
	@DisplayName("POST /orgs - Success")
	void testCreateOrganizationSuccess() throws Exception {
		// Setup mocked service
		OrganizationDTO postOrganization = new OrganizationDTO("id", new Date(), new Date(), "TestPost", "S");
		OrganizationDTO mockOrganization = new OrganizationDTO("id", new Date(), new Date(), "TestPost", "S");
		doReturn(mockOrganization).when(service).save(any());

		// Execute the POST request
		mockMvc.perform(
				post("/orgs").contentType(MediaType.APPLICATION_JSON).content(asJsonString(postOrganization)))
				// Validate the response code and content type
				.andExpect(status().isCreated()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				// Validate the returned field
				.andExpect(jsonPath("$.orgName", is("TestPost")));

	}

	@Test
	@DisplayName("PUT /orgs/1 - Success")
	void testUpdateOrganizationSuccess() throws Exception {
		// Setup mocked service
		OrganizationDTO updateOrganization = new OrganizationDTO("id", new Date(), new Date(), "TestPut", "S");
		OrganizationDTO mockOrganization = new OrganizationDTO("id", new Date(), new Date(), "TestPut", "S");
		doReturn(Optional.of(mockOrganization)).when(service).findById("id");
		doReturn(true).when(service).update(any());

		// Execute the POST request
		mockMvc.perform(put("/orgs/{id}", "id").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(updateOrganization)))
				// Validate the response code and content type
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				// Validate the returned field
				.andExpect(jsonPath("$.orgName", is("TestPut")));
	}

	
	@Test
	@DisplayName("DELETE /orgs/1 - Success")
	void testDeleteOrganizationSuccess() throws Exception {
		OrganizationDTO mockDTO = new OrganizationDTO("id", new Date(), new Date(), "Sudhanshu", "S");

		doReturn(Optional.of(mockDTO)).when(service).findById("id");
		doReturn(true).when(service).delete("id");

		mockMvc.perform(delete("/orgs/{id}", "id")).andExpect(status().isOk());

	}

	@Test
	@DisplayName("DELETE /orgs/1 - Not Found")
	void testDeleteOrganizationNotFound() throws Exception {
		doReturn(Optional.empty()).when(service).findById("id");
		mockMvc.perform(delete("/orgs/{id}", "id")).andExpect(status().isNotFound());
	}

	private static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
