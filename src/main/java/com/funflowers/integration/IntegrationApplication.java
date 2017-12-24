package com.funflowers.integration;

import com.flexionmobile.codingchallenge.integration.IntegrationTestRunner;
import com.funflowers.integration.billing.BillingService;
import com.funflowers.integration.utils.IntegrationResponseErrorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;

@SpringBootApplication
@ConfigurationProperties(prefix = "application.funFlowers")
public class IntegrationApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(IntegrationApplication.class);

    private final ApplicationContext context;

    private List<String> developers = new ArrayList<>();

    @Autowired
    public IntegrationApplication(ApplicationContext context) {
        this.context = context;
    }

    public static void main(String[] args) {
        SpringApplication.run(IntegrationApplication.class, args);
    }

    public List<String> getDevelopers() {
        return developers;
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        RestTemplate restTemplate = builder.build();
        restTemplate.setErrorHandler(context.getBean(IntegrationResponseErrorHandler.class));
        return restTemplate;
    }

    @Override
    public void run(String... args) {

        developers.parallelStream().forEach(developer -> {
            logger.info("Testing {}", developer);
            BillingService service = context.getBean(BillingService.class);
            service.setDeveloperId(developer);
            IntegrationTestRunner testRunner = new IntegrationTestRunner();
            testRunner.runTests(service);
        });

        exit(0);
    }
}
