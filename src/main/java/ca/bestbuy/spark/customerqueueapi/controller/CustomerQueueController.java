package ca.bestbuy.spark.customerqueueapi.controller;


import ca.bestbuy.spark.customerqueueapi.domain.Customer;
import static ca.bestbuy.spark.customerqueueapi.domain.Constants.*;
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
    
    @Autowired
    public CustomerQueueController(QueueService queueService) {
        this.queueService = queueService;

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    @GetMapping(value = "/customers")
    public List<Customer> getQueue() {
        return queueService.getQueue();
    }

    @PutMapping(value = "/customers")
    public boolean addToQueue(@RequestBody Customer customer) {
    	
    	Message.creator(new PhoneNumber(customer.getPhone()),new PhoneNumber(SENDERS_PHONE),
    			"Welcome " + customer.getName()+ " an associate we'll be with you shortly.").create();
    	
        return queueService.AddToQueue(customer);
    }

    @DeleteMapping(value = "/customers")
    public long deleteFromQueue() throws Exception {
        return queueService.UpdateQueue();
    }
}
