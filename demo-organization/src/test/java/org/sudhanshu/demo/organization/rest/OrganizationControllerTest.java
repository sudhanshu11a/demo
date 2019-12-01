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

	/**
	 * Test method for
	 * {@link org.sudhanshu.demo.organization.rest.OrganizationController#getOrganizationName(long)}.
	 * 
	 * @throws Exception
	 */
	@Test
	@DisplayName("GET /name/1 - FOUND")
	void testGetOrganizationByNameFound() throws Exception {

		// Setup our mocked service
		OrganizationDTO mockOrganization = new OrganizationDTO(new Date(), new Date(), "Suranshu");
		doReturn(Optional.of(mockOrganization)).when(service).findById(1l);

		// Execute the GET Request
		mockMvc.perform(get("/name/{id}", 1))
				// validate the response code and content type
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				// Validate the headers
				.andExpect(header().string(HttpHeaders.LOCATION, "/name/1"))
				// validate the returned fields
				.andExpect(jsonPath("$.name", is("Suranshu")));

	}

	@Test
	@DisplayName("GET /name/1 - Not Found")
	void testGetOranizationByNameNotFound() throws Exception {
		// Setup our mocked service
		doReturn(Optional.empty()).when(service).findById(1l);
		// Execute the GET request
		mockMvc.perform(get("/name/{id}", 1))
				// Validate that we get a 404 Not Found response
				.andExpect(status().isNotFound());

	}

	@Test
	@DisplayName("POST /organization - Success")
	void testCreateOrganizationSuccess() throws Exception {
		// Setup mocked service
		OrganizationDTO postOrganization = new OrganizationDTO(new Date(), new Date(), "TestPost");
		OrganizationDTO mockOrganization = new OrganizationDTO(new Date(), new Date(), "TestPost");
		doReturn(mockOrganization).when(service).save(any());

		// Execute the POST request
		mockMvc.perform(
				post("/organization").contentType(MediaType.APPLICATION_JSON).content(asJsonString(postOrganization)))
				// Validate the response code and content type
				.andExpect(status().isCreated()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				// Validate the returned field
				.andExpect(jsonPath("$.name", is("TestPost")));

	}

	@Test
	@DisplayName("PUT /organization/1 - Success")
	void testUpdateOrganizationSuccess() throws Exception {
		// Setup mocked service
		OrganizationDTO updateOrganization = new OrganizationDTO(new Date(), new Date(), "TestPut");
		OrganizationDTO mockOrganization = new OrganizationDTO(new Date(), new Date(), "TestPut");
		doReturn(Optional.of(mockOrganization)).when(service).findById(1l);
		doReturn(true).when(service).update(any());

		// Execute the POST request
		mockMvc.perform(put("/organization/{id}", 1l).contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(updateOrganization)))
				// Validate the response code and content type
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				// Validate the returned field
				.andExpect(jsonPath("$.name", is("TestPut")));
	}

	
	@Test
	@DisplayName("DELETE /organization/1 - Success")
	void testDeleteOrganizationSuccess() throws Exception {
		OrganizationDTO mockDTO = new OrganizationDTO(new Date(), new Date(), "Sudhanshu");

		doReturn(Optional.of(mockDTO)).when(service).findById(1l);
		doReturn(true).when(service).delete(1l);

		mockMvc.perform(delete("/organization/{id}", 1l)).andExpect(status().isOk());

	}

	@Test
	@DisplayName("DELETE /organization/1 - Not Found")
	void testDeleteOrganizationNotFound() throws Exception {
		doReturn(Optional.empty()).when(service).findById(1l);
		mockMvc.perform(delete("/organization/{id}", 1l)).andExpect(status().isNotFound());
	}

	private static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
