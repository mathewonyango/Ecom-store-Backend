package com.example.demo.cunsumer.Repository;

import com.example.demo.cunsumer.Model.Consumers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsumerRepository extends JpaRepository<Consumers, Long> {

    List<Consumers> findAllByName(String name);
}
