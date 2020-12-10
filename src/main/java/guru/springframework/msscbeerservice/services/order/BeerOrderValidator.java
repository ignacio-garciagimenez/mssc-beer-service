package guru.springframework.msscbeerservice.services.order;

import guru.sfg.brewery.model.BeerOrderDto;
import guru.sfg.brewery.model.BeerOrderLineDto;
import guru.springframework.msscbeerservice.config.JmsConfig;
import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BeerOrderValidator {

    private final BeerRepository beerRepository;

    public boolean validateOrder(BeerOrderDto dto) {

        for (BeerOrderLineDto orderLine : dto.getBeerOrderLines()) {
            Beer beer = beerRepository.findByUpc(orderLine.getUpc());
            if (beer == null) {
                return false;
            }
        }

        return true;
    }
}
