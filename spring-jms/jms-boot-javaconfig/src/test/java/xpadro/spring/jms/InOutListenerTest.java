package xpadro.spring.jms;


import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import xpadro.spring.jms.model.Order;
import xpadro.spring.jms.service.ClientService;
import xpadro.spring.jms.service.RegisterService;
import xpadro.spring.jms.service.StoreService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = JmsJavaconfigApplication.class)
public class InOutListenerTest {
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private StoreService storeService;
	
	@Autowired
	private RegisterService registerService;
	
	@Test
	public void registerOrder() throws InterruptedException {
		clientService.registerOrder(new Order("order1","Hello Hello"));
		clientService.registerOrder(new Order("order2"));
		Thread.sleep(1000);
		
		Optional<Order> storedOrder1 = storeService.getReceivedOrder("order1");
		Assert.assertTrue(storedOrder1.isPresent());
		Order order = storedOrder1.get();
		Assert.assertEquals("order1", order.getId());
		Assert.assertEquals("Hello Hello", order.getValue());
		
		Optional<Order> storedOrder2 = storeService.getReceivedOrder("order2");
		Assert.assertTrue(storedOrder2.isPresent());
		order = storedOrder2.get();
		Assert.assertEquals("order2", order.getId());
		Assert.assertNotEquals("Hello Hello", order.getValue());
	}
}
