package com.example.demo.cunsumer.Controller;

import com.example.demo.cunsumer.Service.ConsumerService;
import com.example.demo.cunsumer.Model.Consumers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/consumers")
public class ConsumerController {
    private  final ConsumerService consumerService;

    public ConsumerController(ConsumerService consumerService) {
        this.consumerService = consumerService;
    }
    @GetMapping
    public ResponseEntity<?> getAllConsumers() {
        List<Consumers> consumers = consumerService.getConsumers();

        if (consumers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Customers not found");
        }

        return ResponseEntity.ok(consumers);
    }

    @PostMapping
    public ResponseEntity<?> createConsumer(@RequestBody Consumers consumer) {
        Optional<Consumers> createdConsumer = Optional.ofNullable(consumerService.createConsumer(consumer));

        if (createdConsumer.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(createdConsumer.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Failed to create consumer");
        }
    }


    @DeleteMapping
public void deleteAll(){
        consumerService.deleteAll();
}
    @GetMapping("/{id}")
    public ResponseEntity<?> getConsumerById(@PathVariable String id) {
        Optional<Consumers> consumer = consumerService.getConsumerById(Long.valueOf(id));

        if (consumer.isPresent()) {
            return ResponseEntity.ok(Optional.of(consumer.get()));

        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The customer with ID" +" " + id+" "+  "was not found");

    }

@DeleteMapping("/{id}")
public ResponseEntity<?> deleteConsumer(@PathVariable Long id) {
    Optional<Consumers> consumer = consumerService.getConsumerById(Long.valueOf(id));
    if (consumer.isPresent()) {
        consumerService.deleteConsumer(id);
    }

    else{
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The product with ID " + " "  + id +  "  " + "could not be deleted");

    }

    return null;
}
}
