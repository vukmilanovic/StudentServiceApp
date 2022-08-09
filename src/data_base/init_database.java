package data_base;

public class init_database {

	public static void main(String[] args) {
		MockDB db = new MockDB();
		try {
			db.SaveMockChairs();
			db.SaveMockCourses();
			db.SaveMockGrades();
			db.SaveMockProfs();
			db.SaveMockStudents();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

}
