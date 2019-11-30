/**
 * 
 */
package org.sudhanshu.demo.order.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sudha
 *
 */
@RestController
public class OrderController {
	
	@Autowired
    private JmsTemplate jmsTemplate;
	
	private static int orderNumber;
	
	@GetMapping()
	public String selectOrder() {
		jmsTemplate.convertAndSend("Order", "Order"+orderNumber++);
		return "order Done" + orderNumber;
	}

}
