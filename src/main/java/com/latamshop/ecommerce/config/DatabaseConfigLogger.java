package com.latamshop.ecommerce.config;

import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!prod")
public class DatabaseConfigLogger {
  private static final Logger log = LoggerFactory.getLogger(DatabaseConfigLogger.class);

  @Bean
  public CommandLineRunner logDatabaseInfo(DataSource dataSource) {
    return args -> {
      if (dataSource instanceof HikariDataSource hikari) {
        try (Connection connection = dataSource.getConnection()) {
          DatabaseMetaData metaData = connection.getMetaData();

          StringBuilder sb = new StringBuilder();
          sb.append("HHH10001005: Database info:\n");
          sb.append("\tDatabase JDBC URL [").append(hikari.getJdbcUrl()).append("]\n");
          sb.append("\tDatabase driver: ").append(hikari.getDriverClassName()).append("\n");
          sb.append("\tDatabase dialect: ")
              .append(metaData.getDatabaseProductName())
              .append("Dialect\n");
          sb.append("\tDatabase version: ")
              .append(metaData.getDatabaseProductVersion())
              .append("\n");
          sb.append("\tDefault catalog/schema: ")
              .append(connection.getCatalog())
              .append("/")
              .append(connection.getSchema())
              .append("\n");
          sb.append("\tAutocommit mode: ").append(hikari.isAutoCommit()).append("\n");
          sb.append("\tIsolation level: ")
              .append(connection.getTransactionIsolation())
              .append("\n");
          sb.append("\tJDBC fetch size: none\n");
          sb.append("\tPool: ").append(hikari.getPoolName()).append("\n");
          sb.append("\tMinimum pool size: ").append(hikari.getMinimumIdle()).append("\n");
          sb.append("\tMaximum pool size: ").append(hikari.getMaximumPoolSize());

          log.info("{}", sb.toString());
        } catch (Exception e) {
          log.error("Error al obtener metadata de la base de datos", e);
        }
      }
    };
  }
}
