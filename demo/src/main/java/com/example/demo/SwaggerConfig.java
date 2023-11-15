package com.example.demo;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Ecommerce Backend API")
                        .description(" APIs for E-commerce System\n" +
                                "\n" +
                                "This set of APIs enables the interaction with an E-commerce system, providing various functionalities related to buying products. The APIs cover essential operations for managing products, orders, and user accounts in the e-commerce platform.\n" +
                                "\n" +
                                "Key Features:\n" +
                                "- Product Management: Retrieve product information, search for products, and view product details.\n" +
                                "- Shopping Cart: Add products to the shopping cart, update quantities, and remove items.\n" +
                                "- Order Processing: Place orders, view order history, and track order status.\n" +
                                "- User Authentication: Register new users, log in, and manage user accounts.\n" +
                                "\n" +
                                "API Documentation:\n" +
                                "- `/api/products`: Retrieve information about available products.\n" +
                                "- `/api/cart`: Manage the shopping cart, including adding, updating, and removing items.\n" +
                                "- `/api/orders`: Place orders, view order history, and track order status.\n" +
                                "- `/api/users`: User authentication and account management.\n" +
                                "\n" +
                                "Feel free to explore and integrate these APIs to enhance your e-commerce experience.\n.")
                        .version("1.0.0"));
    }

    @Bean
    public GroupedOpenApi customApi() {
        return GroupedOpenApi.builder()
                .group("Ecommerce API")
                .pathsToMatch("/api/**")
                .build();
    }

}



