//package com.goit5.springweb.entity;
//
//import lombok.Getter;
//
//import javax.persistence.*;
//import java.util.HashSet;
//import java.util.Objects;
//import java.util.Set;
//
//@Getter
//@Entity
//@Table(name = "producer")
//public class Producer {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//    private String name;
//
//    @Getter
//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "producer", cascade = {CascadeType.MERGE, CascadeType.PERSIST} , orphanRemoval = true)
//    private Set<Product> products = new HashSet<>();
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Producer producer = (Producer) o;
//        return id == producer.id && Objects.equals(name, producer.name);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, name);
//    }
//}
