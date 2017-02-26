package com.csci3130.daloffline;

import com.csci3130.daloffline.views.CourseListView;
import com.csci3130.daloffline.Assignment2Thing.StudentListUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;

public class CourseInfo extends FormLayout {
	Button closeButton = new Button("Close");
	
	public CourseInfo(){
		closeButton.addClickListener(e -> this.getView().courseInfo.setVisible(false));
		setVisible(false);
        setSizeUndefined();
        setMargin(true);

        HorizontalLayout actions = new HorizontalLayout(closeButton);
        actions.setSpacing(true);

        addComponents(actions);
	}
	
    public CourseListView getView() {
        return (CourseListView) getUI().getNavigator().getCurrentView();
    }
}