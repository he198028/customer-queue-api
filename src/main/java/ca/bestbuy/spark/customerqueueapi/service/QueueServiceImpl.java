package ca.bestbuy.spark.customerqueueapi.service;

import ca.bestbuy.spark.customerqueueapi.domain.Customer;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class QueueServiceImpl implements QueueService {
    private final BlockingDeque queue;
    private AtomicLong timeStamp = new AtomicLong(0);

    public QueueServiceImpl() {
        queue = new LinkedBlockingDeque();
    }

    @Override
    public boolean AddToQueue(Customer customer) {
        try {
            queue.put(customer);
            return true;
        } catch(InterruptedException e) {
            return false;
        }
    }

    @Override
    public List<Customer> getQueue() {
        return new ArrayList<>(queue);
    }

    @Override
    public long UpdateQueue() throws InterruptedException {
        queue.take();
        if (timeStamp.get()==0) {
            timeStamp.set(Instant.now().toEpochMilli());
            return 0;
        } else {
            return Instant.now().toEpochMilli() - timeStamp.get();
        }
    }
}
