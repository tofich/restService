package aa.trusov.testApp.services;

import aa.trusov.testApp.model.Counter;
import aa.trusov.testApp.repositories.CounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CounterService {

    CounterRepository counterRepository;

    @Autowired
    public CounterService(CounterRepository counterRepository) {
        this.counterRepository = counterRepository;
    }

    public Optional<Counter> findById(String id) {
        //some business logic
        return counterRepository.findById(id);
    }

    public void save(Counter c) {
        //some business logic
        counterRepository.save(c);
    }
}
