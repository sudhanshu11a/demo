/**
 * 
 */
package org.sudhanshu.demo.organization.listener;

import org.apache.activemq.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author sudha
 *
 */
@Component
public class JMSListener {
	
	public static final Logger logger =  LoggerFactory.getLogger(JMSListener.class);
	
	
	@JmsListener(destination = "Order")
	public String recievedMassage(final Message jsonMessage) {
		logger.info("Received message : " + jsonMessage);
		return "recievedMassage";
		 
	}

}
