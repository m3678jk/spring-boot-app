package com.goit5.springweb.feature.producer;


import java.util.List;

public interface ProducerService {
    void saveProducer(Producer producer);

    void deleteProducer(Long id);

    List<Producer> findAll();

    Producer findById(Long id);
}
