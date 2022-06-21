package com.daniel.sipos.demoproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.daniel.sipos.demoproject")
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
