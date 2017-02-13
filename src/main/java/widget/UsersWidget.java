package widget;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.*;
import controller.IUserWidgetController;
import domain.User;

/**
 * UsersWidget
 * <p/>
 * Copyright (C) 2017 copyright.com
 * <p/>
 * Date: 02/13/2017
 *
 * @author Mikita Hladkikh
 */
public class UsersWidget extends VerticalLayout {

    private IUserWidgetController controller;

    public UsersWidget(IUserWidgetController controller) {
        this.controller = controller;
        initContent();
    }

    private void initContent() {
        addComponent(initGrid());
    }

    private Grid initGrid() {
        Grid grid = new Grid("Users");
        grid.setContainerDataSource(initBeanContainer());
        grid.setColumns("name", "surName");
        grid.addItemClickListener(event -> {
            if (event.isDoubleClick()) {
                UI.getCurrent().addWindow(new UserWindow((User) ((BeanItem) event.getItem()).getBean()));
            }
        });
        return grid;
    }

    private BeanItemContainer<User> initBeanContainer() {
        BeanItemContainer<User> userBeanItemContainer = new BeanItemContainer<>(User.class);
        userBeanItemContainer.addAll(controller.getUsers());
        return userBeanItemContainer;
    }

    private class UserWindow extends Window {

        private BeanFieldGroup<User> binder;

        private UserWindow(User user) {
            setContent(initContent(user));
        }

        private VerticalLayout initContent(User user) {
            Button button = new Button("Update");
            button.addClickListener(event -> Notification.show(user.toString()));
            VerticalLayout verticalLayout = new VerticalLayout(initForm(user), button);
            verticalLayout.setMargin(true);
            verticalLayout.setSpacing(true);
            verticalLayout.setComponentAlignment(button, Alignment.BOTTOM_RIGHT);
            return verticalLayout;
        }

        private FormLayout initForm(User user) {
            FormLayout form = new FormLayout();
            form.setSpacing(true);
            binder = new BeanFieldGroup<>(User.class);
            binder.setItemDataSource(user);
            form.addComponent(binder.buildAndBind("Name", "name"));
            form.addComponent(binder.buildAndBind("SurName", "surName"));
            return form;
        }
    }
}
