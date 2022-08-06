package com.goit5.springweb.feature.product;

import com.goit5.springweb.feature.producer.Producer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "product")
public class Product {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    private BigDecimal price;
    private String name;

    @Setter
    @Getter
    @ManyToOne
    private Producer producer;

}
