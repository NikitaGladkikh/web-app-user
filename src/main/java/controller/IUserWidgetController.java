package controller;

import domain.User;
import widget.UsersWidget;
import java.util.List;

/**
 * IUserWidgetController
 * <p/>
 * Copyright (C) 2017 copyright.com
 * <p/>
 * Date: 02/13/2017
 *
 * @author Mikita Hladkikh
 */
public interface IUserWidgetController {

    UsersWidget initWidget();

    List<User> getUsers();

    void createOrUpdateUser(User user);

    void deleteUser(User user);
}
