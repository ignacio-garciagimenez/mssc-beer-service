package guru.springframework.msscbeerservice.bootstrap;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.repositories.BeerRepository;
import guru.springframework.msscbeerservice.web.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Created by jt on 2019-05-17.
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class BeerLoader implements CommandLineRunner {

    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234300019";
    public static final String BEER_3_UPC = "0083783375213";
    public static final UUID BEER_1_UUID = UUID.fromString("0a818933-087d-47f2-ad83-2f986ed087eb");
    public static final UUID BEER_2_UUID = UUID.fromString("a712d914-61ea-4623-8bd0-32c0f6545bfd");
    public static final UUID BEER_3_UUID = UUID.fromString("026cc3c8-3a0c-4083-a05b-e908048c1b08");

    private final BeerRepository beerRepository;

    @Override
    public void run(String... args) throws Exception {
        loadBeerObjects();
    }

    public void loadBeerObjects() {
        if (beerRepository.count() == 0) {
            Beer b1 = Beer.builder()
                    .beerName("Mango Bobs")
                    .beerStyle(BeerStyleEnum.IPA.name())
                    .minOnHand(12)
                    .id(BEER_1_UUID)
                    .quantityToBrew(200)
                    .price(new BigDecimal("12.95"))
                    .upc(BEER_1_UPC)
                    .build();

            Beer b2 = Beer.builder()
                    .beerName("Galaxy Cat")
                    .beerStyle(BeerStyleEnum.PALE_ALE.name())
                    .minOnHand(12)
                    .id(BEER_2_UUID)
                    .quantityToBrew(200)
                    .price(new BigDecimal("12.95"))
                    .upc(BEER_2_UPC)
                    .build();

            Beer b3 = Beer.builder()
                    .beerName("Pinball Porter")
                    .beerStyle(BeerStyleEnum.PALE_ALE.name())
                    .minOnHand(12)
                    .id(BEER_3_UUID)
                    .quantityToBrew(200)
                    .price(new BigDecimal("12.95"))
                    .upc(BEER_3_UPC)
                    .build();

            beerRepository.save(b1);
            beerRepository.save(b2);
            beerRepository.save(b3);
        }
    }
}
