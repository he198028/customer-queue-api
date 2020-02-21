package ca.bestbuy.spark.customerqueueapi.controller;


import ca.bestbuy.spark.customerqueueapi.domain.Customer;
import java.util.List;

import ca.bestbuy.spark.customerqueueapi.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.POST})
public class CustomerQueueController {
    private final QueueService queueService;

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
        return queueService.AddToQueue(customer);
    }

    @DeleteMapping(value = "/customers")
    public long deleteFromQueue() throws Exception {
        return queueService.UpdateQueue();
    }
}
