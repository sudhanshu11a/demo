/**
 * 
 */
package org.sudhanshu.demo.organization.rest;

import org.springframework.test.web.servlet.MockMvc;

/**
 * @author Sudhanshu Sharma
 *
 */
public interface EndpointTest {
	String baseEndpoint();

	MockMvc getMockMvc();
}
