package com.latamshop.ecommerce;

import java.time.LocalDateTime;
import java.time.ZoneId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class EcommerceApplication {

  private static final Logger log = LoggerFactory.getLogger(EcommerceApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(EcommerceApplication.class, args);
  }

  @EventListener(ApplicationReadyEvent.class)
  public void logSystemInfo() {
    log.info("Zona Horaria de la JVM: {}", ZoneId.systemDefault());
    log.info("Hora Local actual: {}", LocalDateTime.now());
  }
}
