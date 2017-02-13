package controller;

import domain.User;
import integration.IUserIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import widget.UsersWidget;

import java.util.List;

/**
 * UserWidgetController
 * <p/>
 * Copyright (C) 2017 copyright.com
 * <p/>
 * Date: 02/13/2017
 *
 * @author Mikita Hladkikh
 */
@Controller
public class UserWidgetController implements IUserWidgetController {

    @Autowired
    private IUserIntegrationService integrationService;

    @Override
    public UsersWidget initWidget() {
        return new UsersWidget(this);
    }

    @Override
    public List<User> getUsers() {
        return integrationService.getUsers();
    }
}
