package aa.trusov.testApp.controllers;

import aa.trusov.testApp.model.Counter;
import aa.trusov.testApp.model.IncrementCountRequest;
import aa.trusov.testApp.repositories.CounterRepository;
import aa.trusov.testApp.services.CounterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Api("Главный контроллер для работы со счетчиками")
public class MainController {

    CounterRepository counterRepository;

    CounterService counterService;

    @Autowired
    public MainController(CounterRepository counterRepository, CounterService counterService) {
        this.counterRepository = counterRepository;
        this.counterService = counterService;
    }

    @GetMapping("/getCount/{id}")
    @ApiOperation("Получение значения счетчика по id")
    public long getCount(@Valid @PathVariable(required = true) @Size(max = 10) String id){
        Counter c = counterService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return c.getCount();
    }

    @PostMapping(path = "/incrementCount/", consumes = APPLICATION_JSON_VALUE)
    @ApiOperation("Увеличение значения счетчика по id")
    public ResponseEntity<?> incrementCount(@Valid @NotBlank @RequestBody IncrementCountRequest request){
        Counter c = counterService.findById(request.getCounterId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        c.setCount(c.getCount() + request.getIncrementCount());
        counterService.save(c);
        return new ResponseEntity<Object>(c.getCount(), HttpStatus.OK);
    }

}
