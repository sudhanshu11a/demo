/**
 * 
 */
package org.sudhanshu.demo.user.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author sudha
 *
 */
@FeignClient(name="organization-service" )
public interface OrganizationServiceProxy {
	
	@RequestMapping("/hello")
	public String getHello();

	

}
