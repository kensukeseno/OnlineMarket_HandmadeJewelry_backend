package com.ken.handmadeJewelry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;


/**
 * @author ken
 *
 */
@SpringBootApplication
@PropertySource("classpath:application.properties")
public class OnlineMarket_HandmadeJewelry {

	public static void main(String[] args) {
		SpringApplication.run(OnlineMarket_HandmadeJewelry.class, args);
	}

}