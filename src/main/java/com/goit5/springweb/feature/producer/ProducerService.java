package com.goit5.springweb.feature.producer;


import java.util.List;
import java.util.Map;

public interface ProducerService {
    void saveProducer(Producer producer);

    void deleteProducer(Long id);

    List<Producer> findAll();

    Producer findById(Long id);

    Map<Producer, List<String>> getPrettyListForTemplate();
}
