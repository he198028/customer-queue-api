package ca.bestbuy.spark.customerqueueapi.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class Customer {

    private String name;

    private String phone;

    private String dept;
}
