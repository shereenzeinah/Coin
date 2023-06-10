package com.example.Coin.kafka;

import com.example.Coin.dto.CoinConsumedEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(
            topics = {
                    "${spring.kafka.topic.payment.processed}",
                    "${spring.kafka.topic.order.created}",
                    "${spring.kafka.topic.post.created}"
            },
            groupId = "myGroup"
    )
    public void consume(String CoinConsumedEvent, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {

        try {

            ObjectMapper mapper = new ObjectMapper();
            CoinConsumedEvent event = mapper.readValue(CoinConsumedEvent, CoinConsumedEvent.class);

            this.handleEvent(event, topic);


        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void handleEvent(CoinConsumedEvent CoinConsumedEvent, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        switch (topic) {
            case "payment-processed": {
                // TODO Handle payment processed event
                break;
            }
            case "order-created": {
                // TODO Handle order created event
                break;
            }
            case "post-created": {
                // TODO Handle post created event
                break;
            }
        }
    }

}
