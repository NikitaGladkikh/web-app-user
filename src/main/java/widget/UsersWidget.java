package widget;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanContainer;
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
    private Grid grid;
    private BeanContainer<String, User> beanContainer;

    public UsersWidget(IUserWidgetController controller) {
        this.controller = controller;
        initContent();
    }

    private void initContent() {
        initGrid();
        VerticalLayout verticalLayout = new VerticalLayout(grid, initButtonsLayout());
        verticalLayout.setSpacing(true);
        addComponent(verticalLayout);
        refresh();
    }

    private HorizontalLayout initButtonsLayout() {
        Button createButton = new Button("Create");
        createButton.addClickListener(event -> {
            UI.getCurrent().addWindow(new UserWindow(new User()));
            refresh();
        });
        Button deleteButton = new Button("Delete");
        deleteButton.addClickListener(event -> deleteUser());
        HorizontalLayout buttonsLayout = new HorizontalLayout(createButton, deleteButton);
        buttonsLayout.setSpacing(true);
        return buttonsLayout;
    }

    private void initGrid() {
        beanContainer = new BeanContainer<>(User.class);
        beanContainer.setBeanIdResolver(User::getId);
        grid = new Grid("Users");
        grid.setContainerDataSource(beanContainer);
        grid.setColumns("name", "surName");
        grid.addItemClickListener(event -> {
            if (event.isDoubleClick()) {
                UI.getCurrent().addWindow(new UserWindow(beanContainer.getItem(event.getItemId()).getBean()));
            }
        });
    }

    public void refresh() {
        String userId = (String) grid.getSelectedRow();
        beanContainer.removeAllItems();
        beanContainer.addAll(controller.getUsers());
        if (null != userId) {
            grid.select(userId);
        }
    }

    private void deleteUser() {
        String userId = (String) grid.getSelectedRow();
        if (null != userId) {
            controller.deleteUser(userId);
            refresh();
        } else {
            Notification.show("Please select user");
        }
    }

    private class UserWindow extends Window {

        private BeanFieldGroup<User> binder;

        private UserWindow(User user) {
            setContent(initContent(user));
        }

        private VerticalLayout initContent(User user) {
            Button button = new Button("Ok");
            button.addClickListener(event -> commitUser(user));
            VerticalLayout verticalLayout = new VerticalLayout(initForm(user), button);
            verticalLayout.setMargin(true);
            verticalLayout.setSpacing(true);
            verticalLayout.setComponentAlignment(button, Alignment.BOTTOM_RIGHT);
            return verticalLayout;
        }

        private void commitUser(User user) {
            try {
                binder.commit();
            } catch (FieldGroup.CommitException e) {
                e.printStackTrace();
            }
            controller.createOrUpdateUser(user);
            UsersWidget.this.refresh();
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
