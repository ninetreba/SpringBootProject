package com.accenture.russiaatc.irentservice10.SNAPSHOT.configuration;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Configuration
@ConfigurationProperties("price")
public class TripProperties {
    @Value("${price.startingPriceBicycle}")
    private  BigDecimal startingPriceBicycle;

    @Value("${price.startingPriceElectricScooter}")
    private  BigDecimal startingPriceElectricScooter;

    @Value("${price.pricePerMinBicycle}")
    private  BigDecimal pricePerMinBicycle;

    @Value("${price.pricePerMinElectricScooter}")
    private  BigDecimal pricePerMinElectricScooter;

    @Value("${maxNumber.trips}")
    private int maxNumberTrips;

}
