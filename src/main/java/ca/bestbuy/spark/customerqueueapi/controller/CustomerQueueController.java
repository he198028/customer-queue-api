package ca.bestbuy.spark.customerqueueapi.controller;


import ca.bestbuy.spark.customerqueueapi.domain.Customer;
import java.util.List;

import ca.bestbuy.spark.customerqueueapi.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.POST})
public class CustomerQueueController {
    private final QueueService queueService;
    
    public static final String ACCOUNT_SID = "ACb8e48e469f2a1623b567331935a37639";
	public static final String AUTH_TOKEN = "0e67b5bef1a6e89f67153283834b6d4e";
	public static final String SENDERS_PHONE= "+12055126627";

    @Autowired
    public CustomerQueueController(QueueService queueService) {
        this.queueService = queueService;
    }

    @GetMapping(value = "/customers")
    public List<Customer> getQueue() {
        return queueService.getQueue();
    }

    @PutMapping(value = "/customers")
    public boolean addToQueue(@RequestBody Customer customer) {
    	
    	Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    	
    	Message.creator(new PhoneNumber(customer.getPhone()),new PhoneNumber(SENDERS_PHONE),
    			"Welcome " + customer.getName()+ " an associate we'll be with you shortly.").create();
    	
        return queueService.AddToQueue(customer);
    }

    @DeleteMapping(value = "/customers")
    public long deleteFromQueue() throws Exception {
        return queueService.UpdateQueue();
    }
}
