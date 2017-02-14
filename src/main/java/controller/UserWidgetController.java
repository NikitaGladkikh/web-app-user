package controller;

import com.vaadin.ui.UI;
import domain.User;
import integration.IUserIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
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
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserWidgetController implements IUserWidgetController {

    @Autowired
    private IUserIntegrationService integrationService;
    private UsersWidget widget;

    @Override
    public UsersWidget initWidget() {
        widget = new UsersWidget(this);
        return widget;
    }

    @Override
    public List<User> getUsers() {
        return integrationService.getUsers();
    }

    @Override
    public void createOrUpdateUser(User user) {
        if (null != user.getId()) {
            integrationService.updateUser(user);
        } else {
            integrationService.createUser(user);
        }
    }

    @Override
    public void deleteUser(String userId) {
        integrationService.deleteUser(userId);
    }

    @Override
    public void refreshWidget() {
        UI.getCurrent().access(() -> widget.refresh());
    }
}
