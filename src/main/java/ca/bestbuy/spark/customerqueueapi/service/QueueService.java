package ca.bestbuy.spark.customerqueueapi.service;

import ca.bestbuy.spark.customerqueueapi.domain.Customer;

import java.util.List;

public interface QueueService {
    boolean AddToQueue(Customer customer);
    List<Customer> getQueue();
    long UpdateQueue() throws InterruptedException;
}
