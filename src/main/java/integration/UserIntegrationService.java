package integration;

import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
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
public class UserIntegrationService implements IUserIntegrationService {

    @Value("${rest-user.uri}")
    private String restUserUri;
    private String restUserIdUri;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<User> getUsers() {
        User[] body = restTemplate.getForEntity(restUserUri, User[].class).getBody();
        return Arrays.asList(body);
    }

    @Override
    public User getUser(String id) {
        return restTemplate.getForEntity(restUserUri, User.class, id).getBody();
    }

    @Override
    public User createUser(User user) {
        return restTemplate.postForEntity(restUserUri, user, User.class).getBody();
    }

    @Override
    public User updateUser(User user) {
        return restTemplate.exchange(restUserIdUri, HttpMethod.PUT, new HttpEntity<>(user), User.class, user.getId())
                .getBody();
    }

    @Override
    public void deleteUser(String id) {
        restTemplate.delete(restUserIdUri, id);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @PostConstruct
    private void buildUserIdUrl() {
        restUserIdUri = UriComponentsBuilder.fromHttpUrl(restUserUri).path("/{id}").build().toUriString();
    }
}
