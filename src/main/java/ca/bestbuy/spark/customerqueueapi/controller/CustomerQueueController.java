package ca.bestbuy.spark.customerqueueapi.controller;


import ca.bestbuy.spark.customerqueueapi.domain.Customer;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerQueueController {

    @GetMapping(value = "/customers")
    public List<Customer> getQueue() {

        return null;
    }

    @PutMapping(value = "/customers")
    public Customer addToQueue(Customer customer) {
    //TODO invoke service class to add to queue
        return customer;
    }


    @DeleteMapping(value = "/customers")
    public void deleteFromQueue(){
    //TODO invoke service class to delete  queue
    }
}
