package com.csci3130.daloffline.Assignment2Thing;

import com.csci3130.daloffline.Assignment2Thing.Student;
import com.csci3130.daloffline.Assignment2Thing.StudentService;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.ui.Grid;
import com.vaadin.v7.ui.TextField;

/* User Interface written in Java.
 *
 * Define the user interface shown on the Vaadin generated web page by extending the UI class.
 * By default, a new UI instance is automatically created when the page is loaded. To reuse
 * the same instance, add @PreserveOnRefresh.
 */
@Title("Dal Offline")
@Theme("valo")
@Widgetset("com.vaadin.v7.Vaadin7WidgetSet")
public class StudentListUI extends VerticalLayout implements View {
    public StudentListUI() {
        setSizeFull();

        configureComponents();
        buildLayout();
    }

    @Override
    public void enter(ViewChangeEvent event) {
        //Notification.show("");
    }
    
    /*
     * Hundreds of widgets. Vaadin's user interface components are just Java
     * objects that encapsulate and handle cross-browser support and
     * client-server communication. The default Vaadin components are in the
     * com.vaadin.ui package and there are over 500 more in
     * vaadin.com/directory.
     */
    TextField filter = new TextField();
    Grid studentList = new Grid();
    Button newStudent = new Button("New Student");
    Button backButton = new Button("Go Back");

    // ContactForm is an example of a custom component class
    StudentForm studentForm = new StudentForm();

    // ContactService is a in-memory mock DAO that mimics
    // a real-world datasource. Typically implemented for
    // example as EJB or Spring Data based service.
    StudentService service = StudentService.createDemoService();

    /*
     * The "Main method".
     *
     * This is the entry point method executed to initialize and configure the
     * visible user interface. Executed on every browser reload because a new
     * instance is created for each web page loaded.
     */

    private void configureComponents() {
        /*
         * Synchronous event handling.
         *
         * Receive user interaction events on the server-side. This allows you
         * to synchronously handle those events. Vaadin automatically sends only
         * the needed changes to the web page without loading a new page.
         */
        newStudent.addClickListener(e -> studentForm.edit(new Student()));
        backButton.addClickListener(e -> getUI().getNavigator().navigateTo("main"));

        filter.setInputPrompt("Filter students...");
        filter.addTextChangeListener(e -> refreshStudents(e.getText()));

        studentList.setContainerDataSource(new BeanItemContainer<>(Student.class));
        studentList.setColumnOrder("firstName", "lastName", "studentID", "major", "email");
        studentList.removeColumn("id");
        studentList.setSelectionMode(Grid.SelectionMode.SINGLE);
        studentList.addSelectionListener(e -> studentForm.edit((Student) studentList.getSelectedRow()));
        refreshStudents();
    }

    /*
     * Robust layouts.
     *
     * Layouts are components that contain other components. HorizontalLayout
     * contains TextField and Button. It is wrapped with a Grid into
     * VerticalLayout for the left side of the screen. Allow user to resize the
     * components with a SplitPanel.
     *
     * In addition to programmatically building layout in Java, you may also
     * choose to setup layout declaratively with Vaadin Designer, CSS and HTML.
     */
    private void buildLayout() {
        HorizontalLayout actions = new HorizontalLayout(backButton, filter, newStudent);
        actions.setWidth("100%");
        filter.setWidth("100%");
        actions.setExpandRatio(filter, 1);

        VerticalLayout left = new VerticalLayout(actions, studentList);
        left.setSizeFull();
        studentList.setSizeFull();
        left.setExpandRatio(studentList, 1);

        HorizontalLayout mainLayout = new HorizontalLayout(left, studentForm);
        mainLayout.setSizeFull();
        mainLayout.setExpandRatio(left, 1);

        // Split and allow resizing
        addComponent(mainLayout);
    }

    /*
     * Choose the design patterns you like.
     *
     * It is good practice to have separate data access methods that handle the
     * back-end access and/or the user interface updates. You can further split
     * your code into classes to easier maintenance. With Vaadin you can follow
     * MVC, MVP or any other design pattern you choose.
     */
    void refreshStudents() {
    	refreshStudents(filter.getValue());
    }

    private void refreshStudents(String stringFilter) {
    	studentList.setContainerDataSource(new BeanItemContainer<>(Student.class, service.findAll(stringFilter)));
        studentForm.setVisible(false);
    }
}
