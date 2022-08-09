package controller;

import data_base.CourseChairDB;
import model_sistema.CourseChair;
import model_sistema.Professor;
import view.main_frame.Main_Window_Tabs;

public class CourseChairController {

	private static CourseChairController instance = null;
	
	public static CourseChairController getInstance() {
		if(instance == null)
			instance = new CourseChairController();
		
		return instance;
	}
	
	private CourseChairController() {}
	
	public void AddCourseChair(int code, String name, Professor prof) {
		CourseChairDB.getInstance().addCourseChair(code, name, prof);
		Main_Window_Tabs.getInstance().updateDepartmentView("Add chair", code);
	}
	
	public void RemoveCourseChair(int row) {
		CourseChair chair = CourseChairDB.getInstance().getRow(row);
		CourseChairDB.getInstance().removeCourseChair(chair.getCode());
		Main_Window_Tabs.getInstance().updateDepartmentView("Remove chair", row);
	}
	
	public void EditCourseChair(int row, int code, String name, Professor prof) {
		CourseChair chair = CourseChairDB.getInstance().getRow(row);
		CourseChairDB.getInstance().editCourseChair(chair.getCode(), code, name, prof);
		Main_Window_Tabs.getInstance().updateDepartmentView("Edit chair", code);
	}	
}
