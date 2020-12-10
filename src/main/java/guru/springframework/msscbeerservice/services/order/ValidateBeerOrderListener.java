package guru.springframework.msscbeerservice.services.order;

import guru.sfg.brewery.model.BeerOrderLineDto;
import guru.sfg.brewery.model.event.BeerOrderValidationResult;
import guru.sfg.brewery.model.event.ValidateBeerOrderEvent;
import guru.springframework.msscbeerservice.config.JmsConfig;
import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidateBeerOrderListener {

    private final JmsTemplate jmsTemplate;
    private final BeerOrderValidator beerOrderValidator;


    @JmsListener(destination = JmsConfig.VALIDATE_BEER_ORDER_QUEUE)
    public void listenForValidateBeerOrderEvent(@Payload ValidateBeerOrderEvent event) {

        BeerOrderValidationResult result = BeerOrderValidationResult.builder()
                .orderId(event.getBeerOrderDto().getId())
                .isValid(beerOrderValidator.validateOrder(event.getBeerOrderDto()))
                .build();

        jmsTemplate.convertAndSend(JmsConfig.BEER_ORDER_VALIDATION_RESULT_QUEUE, result);
    }
}
