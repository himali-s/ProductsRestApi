package com.example.CentricSoftware.Data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ProductRepository repository) {
        List<String> tags = new ArrayList<String>();
        tags.add("red");
        tags.add("slim");

        return args -> {
            log.info("Preloading " + repository.save(new Product("White Shirt", "Red hugo boss shirt", "Gucci", tags, "apperal")));
            Thread.sleep(1000);
            log.info("Preloading " + repository.save(new Product("Red Shirt", "White hugo boss shirt", "LV", tags, "jewellary")));
            Thread.sleep(1000);
            log.info("Preloading " + repository.save(new Product("Tshirt", "Tshirt for women", "LV", tags, "apperal")));
            Thread.sleep(1000);
            log.info("Preloading " + repository.save(new Product("Earrings", "Gold earrings", "Zara", tags, "jewellary")));
            Thread.sleep(1000);
            log.info("Preloading " + repository.save(new Product("Pants", "Jeans Pants", "Levis", tags, "apperal")));
            Thread.sleep(1000);
            log.info("Preloading " + repository.save(new Product("necklace", "Gold Necklace", "Macys", tags, "jewellary")));
        };
    }
}
