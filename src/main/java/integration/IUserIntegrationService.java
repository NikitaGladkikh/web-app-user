package integration;

import domain.User;

import java.util.List;

/**
 * IUserIntegration
 * <p/>
 * Copyright (C) 2017 copyright.com
 * <p/>
 * Date: 02/13/2017
 *
 * @author Mikita Hladkikh
 */
public interface IUserIntegrationService {

    List<User> getUsers();

    User getUser(String id);

    User createUser(User user);

    User updateUser(User user);

    void deleteUser(String id);
}
