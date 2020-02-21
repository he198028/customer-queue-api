package ca.bestbuy.spark.customerqueueapi.service;

import ca.bestbuy.spark.customerqueueapi.domain.Customer;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicLong;

import static ca.bestbuy.spark.customerqueueapi.domain.Constants.SENDERS_PHONE;

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

        Customer customer = (Customer)queue.peek();
        Message.creator(new PhoneNumber(customer.getPhone()),new PhoneNumber(SENDERS_PHONE),
                "Heads up " + customer.getName()+ ", You are next in the line.").create();

        if (timeStamp.get()==0) {
            timeStamp.set(Instant.now().toEpochMilli());
            return 0;
        } else {
            return Instant.now().toEpochMilli() - timeStamp.get();
        }
    }
}
