package com.goit5.springweb.feature.producer;

import com.goit5.springweb.feature.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProducerServiceImpl implements ProducerService {
    private final ProducerRepository producerRepository;
    @Override
    public void saveProducer(Producer producer) {
        if(producer.getId()==null) {
            producerRepository.save(producer);
        } else {
            List<Product> products = producer.getProducts();
            List<Product> copyOfProductsList = List.copyOf(products);
            for(Product p: copyOfProductsList){
                producer.addProduct(p);
            }
            producerRepository.save(producer);
        }
    }

    @Override
    public void deleteProducer(UUID id) {
        Producer producer = findById(id);
        producerRepository.delete(producer);
    }

    @Override
    public List<Producer> findAll() {
        return producerRepository.findAll();
    }

    @Override
    public Producer findById(UUID id) {
        return producerRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Producer not found"));
    }


    public Map<Producer, List<String>> getPrettyListForTemplate(){
        List<Producer> listAll = findAll();
        Map<Producer, List<String>> list = new LinkedHashMap<>();

        for(Producer p : listAll){
            List<String> nameList= p.getProducts().stream().map(Product::getName).collect(Collectors.toList());
            list.put(p, nameList);
        }
        return list;
    }
}
