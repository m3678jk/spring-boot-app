package com.goit5.springweb.feature.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProducerServiceImpl implements ProducerService {
    private final ProducerRepository producerRepository;
    @Override
    public void saveProducer(Producer producer) {
        producerRepository.save(producer);
    }

    @Override
    public void deleteProducer(Long id) {
        Producer producer = findById(id);
        producerRepository.delete(producer);
    }

    @Override
    public List<Producer> findAll() {
        return producerRepository.findAll();
    }

    @Override
    public Producer findById(Long id) {
        return producerRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Producer not found"));
    }
}
