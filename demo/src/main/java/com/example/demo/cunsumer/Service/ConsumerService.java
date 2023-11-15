package com.example.demo.cunsumer.Service;

import com.example.demo.cunsumer.Model.Consumers;
import com.example.demo.cunsumer.Repository.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class ConsumerService {
    @Autowired
    private final ConsumerRepository consumerRepository;

    public ConsumerService(ConsumerRepository consumerRepository) {
        this.consumerRepository = consumerRepository;
    }
    public List<Consumers> getConsumers(){
        return consumerRepository.findAll();
    }
    public Consumers createConsumer(Consumers consumer){
        return consumerRepository.save(consumer);

    }

public void deleteConsumer(Long id){
        consumerRepository.deleteById(id);
}
public Optional<Consumers> getConsumerById(Long id){
        return consumerRepository.findById(id);
}
public void deleteAll(){
        consumerRepository.deleteAll();
}

    public List<Consumers> findAllConsumersByName(String name) {
        return consumerRepository.findAllByName(name);
    }

}
