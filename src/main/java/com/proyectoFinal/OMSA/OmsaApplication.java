package com.proyectoFinal.OMSA;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@EnableTransactionManagement(proxyTargetClass=true)
@SpringBootApplication
public class OmsaApplication {
	public static void main(String[] args) {
		SpringApplication.run(OmsaApplication.class, args);
	}
}
