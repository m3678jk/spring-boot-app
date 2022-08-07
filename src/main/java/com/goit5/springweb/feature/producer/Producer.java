package com.goit5.springweb.feature.producer;

import com.goit5.springweb.feature.product.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "producer")
public class Producer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @Getter
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "producer", cascade = {CascadeType.MERGE, CascadeType.PERSIST} , orphanRemoval = true)
    private List<Product> products = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producer producer = (Producer) o;
        return id == producer.id && Objects.equals(name, producer.name);
    }

    public void addProduct(Product product) {
        products.add(product);
        product.setProducer(this);
    }

    public void removeProduct(Product product) {
        products.remove(product);
        product.setProducer(null);
    }

    @Override
    public String toString() {
        return "Producer{" +
                "id=" + id +
                ", name='" + name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
