package com.example.demo.cunsumer.Model;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Table
@Entity
public class Consumers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long Id;
    private String name;
    private String relationship;


    public Consumers() {
    }

    public Consumers(Long id, String name, String relationship) {
        Id = id;
        this.name = name;
        this.relationship = relationship;
    }

    public Consumers(String name, String relationship) {
        this.name = name;
        this.relationship = relationship;
    }

    public Consumers(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        Id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public Long getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public String getRelationship() {
        return relationship;
    }

    @Override
    public String toString() {
        return "Consumers{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", relationship='" + relationship + '\'' +
                '}';
    }
}
