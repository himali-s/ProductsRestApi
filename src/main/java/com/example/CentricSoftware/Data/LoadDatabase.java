package com.example.CentricSoftware.Data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;


@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ProductRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Product("White Shirt", "White Gucci shirt", "Gucci", Arrays.asList("white, shirt,men"), "apperal")));
            Thread.sleep(1000);
            log.info("Preloading " + repository.save(new Product("Red Shirt", "Red hugo boss shirt", "Hugo Boss", Arrays.asList("red","shirt","slim fit"), "apperal")));
            Thread.sleep(1000);
            log.info("Preloading " + repository.save(new Product("Black Pant", "Pants for women", "LV", Arrays.asList("black,","pants","women"), "apperal")));
            Thread.sleep(1000);
            log.info("Preloading " + repository.save(new Product("Earrings", "Gold earrings", "Zara", Arrays.asList("earrings","gold"), "jewellary")));
            Thread.sleep(1000);
            log.info("Preloading " + repository.save(new Product("Diamond Ring", "Diamond ring", "Gucci", Arrays.asList("diamond","fingerRing"), "jewellary")));
            Thread.sleep(1000);
            log.info("Preloading " + repository.save(new Product("Necklace", "Gold Necklace", "Macys", Arrays.asList("gold","necklace"), "jewellary")));
        };
    }
}
