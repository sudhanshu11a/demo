/**
 * 
 */
package org.sudhanshu.demo.organization.rest;

import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.sudhanshu.demo.organization.dto.OrganizationDTO;
import org.sudhanshu.demo.organization.service.OrganizationService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.HttpHeaders;

/**
 * @author Sudhanshu Sharma
 *
 */

@SpringJUnitWebConfig()
@WebMvcTest(controllers = OrganizationController.class)
public class OrganizationControllerTest1 implements GetResourceEndpointTest<OrganizationDTO, Long> {
 
    @Autowired
    private MockMvc mockMvc;
 
    @MockBean
    private OrganizationService service;
 
    @Override
    public String baseEndpoint() {
        return "/Organization";
    }
 
    @Override
    public MockMvc getMockMvc() {
        return mockMvc;
    }
 
    @Override
    public Long getExistingResource() {
        return 1L;
    }
 
    @Override
    public Long getNonExistingResoruce() {
        return 0L;
    }
 
    @Override
    public OrganizationDTO foundResource() {
        return new OrganizationDTO(new Date(), new Date(), "NewOrg");
    }
 
    @Override
    public String getFoundResourceJsonContent() {
        return "{\"firstName\": \"Joe\", \"lastName\" : \"Blow\", \"middleName\" : \"Kokomo\", \"suffix\" : \"Jr.\",\"dateOfLastStay\" : \"1970-01-01T00:00:00.000+0000\" }";
    }
 
    @Override
    public OngoingStubbing<OrganizationDTO> mockExistingBehavior() {
        return when(service.findById(getExistingResource()).get());
    }
 
    @Override
    public OngoingStubbing<OrganizationDTO> mockNonExistingBehavior() {
        return when(service.findById(getNonExistingResoruce()).get());
    }
 
    @Override
    public OngoingStubbing<List<OrganizationDTO>> mockFindAllResourcesBehavior() {
        return when(service.findAll());
    }
 
}
