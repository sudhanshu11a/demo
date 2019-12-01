/**
 * 
 */
package org.sudhanshu.demo.organization.rest;

import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;

import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.sudhanshu.demo.organization.dto.OrganizationDTO;
import org.sudhanshu.demo.organization.service.OrganizationService;

/**
 * @author Sudhanshu Sharma
 *
 */

@SpringJUnitWebConfig()
@WebMvcTest(controllers = OrganizationController.class)
public class OrganizationControllerTest1 implements GetResourceEndpointTest<OrganizationDTO, String> {
 
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
    public String getExistingResource() {
        return "id";
    }
 
    @Override
    public String getNonExistingResoruce() {
        return "id";
    }
 
    @Override
    public OrganizationDTO foundResource() {
        return new OrganizationDTO("id", new Date(), new Date(), "NewOrg");
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
