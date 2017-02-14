package integration;

import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

/**
 * UserIntegrationService
 * <p>
 * Copyright (C) 2017 copyright.com
 * <p>
 * Date: 02/13/2017
 *
 * @author Mikita Hladkikh
 */
@Service
@Configuration
@PropertySource("classpath:config.properties")
public class UserIntegrationService implements IUserIntegrationService {

    @Value("${rest-user.url}")
    private String restUserUrl;
    private String restUserIdUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<User> getUsers() {
        User[] body = restTemplate.getForEntity(restUserUrl, User[].class).getBody();
        return Arrays.asList(body);
    }

    @Override
    public User getUser(String id) {
        return restTemplate.getForEntity(restUserUrl + id, User.class).getBody();
    }

    @Override
    public User createUser(User user) {
        return restTemplate.postForEntity(restUserUrl, user, User.class).getBody();
    }

    @Override
    public User updateUser(User user) {
        return restTemplate.exchange(restUserIdUrl, HttpMethod.PUT, new HttpEntity<>(user), User.class, user.getId())
                .getBody();
    }

    @Override
    public void deleteUser(String id) {
        restTemplate.delete(restUserIdUrl, id);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @PostConstruct
    private void buildUserIdUrl() {
        restUserIdUrl = UriComponentsBuilder.fromHttpUrl(restUserUrl).path("/{id}").build().toUriString();
    }
}
