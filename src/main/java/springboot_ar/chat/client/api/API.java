package springboot_ar.chat.client.api;

import org.springframework.web.client.RestTemplate;

public class API {
    private String server_url = "http://localhost:8080/";
    private RestTemplate restTemplate = new RestTemplate();

    public API(){
    }
}
