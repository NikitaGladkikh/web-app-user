package integration;

import domain.User;

import java.util.ArrayList;
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

    default List<User> getUsers() {
        List<User> users = new ArrayList<>(9);
        users.add(new User("1", "John", "Whiley"));
        users.add(new User("2", "Benedict", "Cumbercratch"));
        users.add(new User("3", "Sara", "Conor"));
        users.add(new User("4", "Mace", "Windu"));
        users.add(new User("5", "Jack", "Sparrow"));
        users.add(new User("6", "John", "Snow"));
        users.add(new User("7", "Chip", "Dale"));
        users.add(new User("8", "Glint", "Vine"));
        users.add(new User("9", "Jeepers", "Creepers"));
        return users;
    }


    default User getUser(String id) {
        return getUsers().stream().filter(user -> user.getId().equals(id)).findFirst().get();
    }

    default User updateUser(User user) {
        return user;
    }

    default User deleteUser(String id) {
        return this.getUser(id);
    }
}
