package classes;

import java.util.ArrayList;

public class TeacherContainer {
	String email,name,surname;
	int ID;
	
	ArrayList<String> courses;
	
	public int GetAdminID()
	{
		return ID;
	}
	
	public void SetAdminID(int newID)
	{
		ID = newID;
	}
	
	public String GetAdminEmail()
	{
		return email;
	}
	
	public void SetAdminEmail(String newEmail)
	{
		email = newEmail;
	}
	
	public String GetAdminName()
	{
		return name;
	}
	
	public void SetAdminName(String newName)
	{
		name = newName;
	}
	
	public String GetAdminSurname()
	{
		return surname;
	}
	
	public void SetAdminSurname(String newSurname)
	{
		surname = newSurname;
	}
	
	public ArrayList<String> GetCourses()
	{
		return courses;
	}
	
	public void SetCourses(ArrayList<String> new_courses)
	{
		courses = new_courses;
	}
	
	public void SetNewCourse(String course)
	{
		courses.add(course);
	}
}
