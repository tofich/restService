package aa.trusov.testApp.restClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

//Для простоты вся логика содержится в контроллере
@Controller
@RequestMapping("/client")
public class ClientController {

    private RestTemplate restTemplate;

    @Autowired
    public ClientController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/getCount")
    public String getCount(String counterId, Model model){
        String url = UriComponentsBuilder
                .fromUriString(String.format("http://localhost:8080/getCount/%s", counterId))
                .build().toString();
        String count;
        try {
            count = restTemplate.getForObject(url, String.class);
        }
        catch(HttpClientErrorException e){
            count = "Не найден указанный счетчик";
        }
        model.addAttribute("count", count);
        return "getCount";
    }

    @GetMapping("/incrementCount")
    public String incrementCount(String counterId, String incrementCount, Model model){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        Map<String, Object> map = new HashMap<>();
        map.put("counterId", counterId);
        map.put("incrementCount", incrementCount);

        String url = "http://localhost:8080/incrementCount/";
        String count;
        try {
            count = restTemplate.postForObject(url, map, String.class);
        }
        catch(HttpClientErrorException e){
            count = "Введено неверное значение или не найден указанный счетчик";
        }
        model.addAttribute("count", count);
        return "incrementCount";
    }

}
