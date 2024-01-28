package com.fiap.techfood.infrastructure.configuration;

import com.fiap.techfood.application.interfaces.usecases.*;
import com.fiap.techfood.application.usecases.*;
import com.fiap.techfood.infrastructure.repository.CategoryBdRepository;
import com.fiap.techfood.infrastructure.repository.CustomerBdRepository;
import com.fiap.techfood.infrastructure.repository.OrderBdRepository;
import com.fiap.techfood.infrastructure.repository.ProductBdRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Configuration
public class BeanConfiguration {

    @Bean
    ProductUseCases productUseCases(ProductBdRepository repository, CategoryBdRepository categoryRepository) {
        return new ProductUseCasesImpl(repository, categoryRepository);
    }

    @Bean
    CategoryUseCases categoryUseCases(CategoryBdRepository repository) {
        return new CategoryUseCasesImpl(repository);
    }

    @Bean
    OrderUseCases orderUseCases(OrderBdRepository orderBdRepository,
                                ProductBdRepository productRepository,
                                CustomerBdRepository customerBdRepository,
                                NotificationUseCases notificationUseCases) {
        return new OrderUseCasesImpl(orderBdRepository, productRepository, customerBdRepository, notificationUseCases);
    }

    @Bean
    CustomerUseCases customerUseCases(CustomerBdRepository repository) {
        return new CustomerUseCasesImpl(repository);
    }

    @Bean
    NotificationUseCases notificationUseCases(RestTemplate restTemplate) {
        return new NotificationUseCasesImpl(restTemplate);
    }

    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(5000);
        factory.setReadTimeout(5000);
        RestTemplate restTemplate = new RestTemplate(factory);

        restTemplate.setInterceptors(Collections.singletonList((request, body, execution) -> {
            request.getHeaders().setCacheControl(CacheControl.noCache());
            return execution.execute(request, body);
        }));

        return restTemplate;
    }
}
