package main;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import controller.IUserWidgetController;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * WebAppUserUi
 * <p/>
 * Copyright (C) 2017 copyright.com
 * <p/>
 * Date: 02/13/2017
 *
 * @author Mikita Hladkikh
 */
@SpringUI(path = "web-app-user")
public class WebAppUserUi extends UI {

    @Autowired
    private IUserWidgetController userWidgetController;

    @Override
    protected void init(VaadinRequest request) {
        setContent(userWidgetController.initWidget());
        setPollInterval(10000);
        addPollListener(event -> userWidgetController.refreshWidget());
    }
}
