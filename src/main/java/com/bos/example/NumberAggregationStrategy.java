package com.bos.example;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

/**
 * Aggregate two numbers
 *
 * @version 
 */
// START SNIPPET: e1
public class NumberAggregationStrategy implements AggregationStrategy {

    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        if (oldExchange == null) {
            return newExchange;
        }

        // check for stop command
        String input = newExchange.getIn().getBody(String.class);
        if ("STOP".equalsIgnoreCase(input)) {
            return oldExchange;
        }

        Integer num1 = oldExchange.getIn().getBody(Integer.class);
        Integer num2 = newExchange.getIn().getBody(Integer.class);

        // just avoid bad inputs by assuming its a 0 value
        Integer num3 = (num1 != null ? num1 : 0) + (num2 != null ? num2 : 0);
        oldExchange.getIn().setBody(num3);

        return oldExchange;
    }

}
// END SNIPPET: e1
