package com.example.Coin.kafka;

import com.example.Coin.dto.CoinConsumedEvent;
import com.example.Coin.services.coin.CoinService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @Autowired
    CoinService coinService;

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

    public void handleEvent(CoinConsumedEvent coinConsumedEvent, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        switch (topic) {
            case "payment-processed": {
                // Add balance to wallet
                coinService.addCoins(coinConsumedEvent.getUserId(), coinConsumedEvent.getAmount());
                break;
            }
            case "order-created": {
                // Deduct balance from wallet
                coinService.deductCoins(coinConsumedEvent.getUserId(), coinConsumedEvent.getAmount());
                break;
            }
            case "post-created": {
                // Deduct balance from wallet
                coinService.deductCoins(coinConsumedEvent.getUserId(), coinConsumedEvent.getAmount());
                break;
            }
        }
    }

}
