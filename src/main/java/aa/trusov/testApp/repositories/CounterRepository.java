package aa.trusov.testApp.repositories;

import aa.trusov.testApp.model.Counter;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CounterRepository extends PagingAndSortingRepository<Counter, String> {

}
