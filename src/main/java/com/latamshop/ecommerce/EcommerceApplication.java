package com.latamshop.ecommerce;

import java.time.LocalDateTime;
import java.time.ZoneId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

/** Main class that starts the Spring Boot Ecommerce application. */
@SpringBootApplication
public class EcommerceApplication {

  /** Logger used to output system information during application startup. */
  private static final Logger log = LoggerFactory.getLogger(EcommerceApplication.class);

  /**
   * Main method that starts the Spring Boot application.
   *
   * @param args command-line arguments passed during application startup
   */
  public static void main(String[] args) {
    SpringApplication.run(EcommerceApplication.class, args);
  }

  /**
   * Listener executed when the application is fully initialized and ready.
   *
   * <p>This method logs runtime environment details such as the JVM default time zone and the
   * current local date and time.
   */
  @EventListener(ApplicationReadyEvent.class)
  public void logSystemInfo() {
    log.info("Zona Horaria de la JVM: {}", ZoneId.systemDefault());
    log.info("Hora Local actual: {}", LocalDateTime.now());
    log.info("Test");
  }
}
