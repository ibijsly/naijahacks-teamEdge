package com.securedEdgePay;

import com.securedEdgePay.seeder.StatusSeeder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

@SpringBootApplication
@EnableTransactionManagement
public class SecuredEdgePayApplication {

	@Autowired
	StatusSeeder statusSeeder;

	public static void main(String[] args) {
		SpringApplication.run(SecuredEdgePayApplication.class, args);
	}

	@EventListener
	public void seedDB(ContextRefreshedEvent refreshedEvent){

		try {
			statusSeeder.seed();
		}
		catch (Exception ex){
			ex.printStackTrace();
		}
	}
}
