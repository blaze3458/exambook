package classes;

import java.sql.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Data
{
	Connection conn = null;
	String dbURL = "jdbc:sqlserver://localhost";
	String user = "furkan";
	String pass = "123456";
//**************************************************************************************************//
	
	public void InsertData(String data)
	{
		try {
			
			DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
			
			conn = DriverManager.getConnection(dbURL, user, pass);
			
			Statement stmt = conn.createStatement();
			
			/*INSERT INTO Tablo_ismi VALUES (Deðerler) */
			
			String sql = "INSERT INTO NEW_DATABASE.dbo.Tablo_adi VALUES('"+data+"',"+Integer.toString(6)+")";
			
			//ResultSet set = stmt.executeQuery(sql);
			
			// INSERT, UPDATE, DELETE = executeUpdate
			// SELECT = executeQuery
			
			stmt.executeUpdate(sql);
			
			
		} catch (SQLException e) {e.printStackTrace();}
	}
	
	public ResultSet GetData()
	{
		ArrayList<String> result = new ArrayList<>();
		ResultSet rs = null;
		try {
			
			DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
			
			conn = DriverManager.getConnection(dbURL, user, pass);
			
			String sql = "SELECT Text,Value FROM NEW_DATABASE.dbo.Tablo_adi";
			
			rs = conn.createStatement().executeQuery(sql);
			
			
			//connection = DriverManager.getConnection(URL,username,password);Database baðlantý	
			//connection.createStatement().executeQuery(sql); SQL SELECT komutunu çalýþtýrýrken kullanýlýr.
			//connection.createStatement().executeUpdate(sql); SQL INSERT,UPDATE,DELETE komutunu çalýþtýrýrken kullanýlýr.
			//rs.next() veri varsa sýradaki veriye geçer true döndürür.yoksa veya en son veriye gelince false;
			//rs.getString(Integer sütun numarasý); //Sütun numarasýna göre satýrdaki String datayý alýr.
			//rs.getInt(Integer sütun numarasý); //Sütun numarasýna göre satýrdaki Integer datayý alýr.
			
			
		}
		catch(SQLException e) {e.printStackTrace();}
		return rs;
	}

//*******************************************CHECK**********************************************//
	
	public boolean IsAdmin(String name)
	{
		boolean check = false;
		Pattern VALID_EMAIL_ADDRESS_REGEX = 
			    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		Matcher m = VALID_EMAIL_ADDRESS_REGEX.matcher(name);
		if(m.find())
			check = true;
		
		return check;
	}
	
	public boolean CheckUserNamePassword(String name,String password) throws SQLException
	{
		DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
		boolean Check = false;
		conn = DriverManager.getConnection(dbURL, user, pass);
		if (conn != null) 
		{
			Statement stmt = conn.createStatement();
						String sql = null;
			if(IsAdmin(name))
				sql = "SELECT Password FROM NEW_DATABASE.dbo.AdminInfo WHERE Email = '"
					+name+"'";
			else
				sql = "SELECT Password FROM NEW_DATABASE.dbo.StudentInfo WHERE StudentID = '"
						+name+"'";
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next())
				if(password.equals(rs.getString("Password")))
					Check = true;
			
			rs.close();
			stmt.close();
			conn.close();		
		}
		return Check;
	}
	
	public boolean CheckEmail(String email)//for sign up new user
	{
		boolean check = false;
		try
		{
			DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
			conn = DriverManager.getConnection(dbURL, user, pass);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT Email FROM NEW_DATABASE.dbo.AdminInfo WHERE Email = '"+email+"'");
			
			if(!rs.next())
				check = true;
			
			stmt.close();
			conn.close();
		}
		catch(SQLException e) {e.printStackTrace();	}
		
		return check;
	}
	
	public boolean CheckExam(String ID)
	{
		boolean check = false;
		try
		{
			conn = DriverManager.getConnection(dbURL, user, pass);
			Statement stmt = conn.createStatement();
			
			String sql = "SELECT HasExam FROM NEW_DATABASE.dbo.ExamTime WHERE StudentID = '"+ID+"' AND HasExam = 1";
			
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next())
				check = true;
		}
		catch(SQLException e){e.printStackTrace();}
		return check;
	}
	
	public boolean CheckHasExamQuestion(String course)
	{
		boolean check = false;
		try
		{
			conn = DriverManager.getConnection(dbURL, user, pass);
			Statement stmt = conn.createStatement();
			
			String sql = "SELECT CourseName FROM NEW_DATABASE.dbo.Questions WHERE CourseName = '"+course+"'";
			
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next())
				check = true;
		}
		catch(SQLException e){e.printStackTrace();}
		return check;
	}

	
//*********************************************************************************************//
//*******************************************GETS**********************************************//	
//*********************************************************************************************//
	
	public int GetTeacherID(String email)
	{
		int ID = 0;
		try {
			conn = DriverManager.getConnection(dbURL, user, pass);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT TeacherID FROM NEW_DATABASE.dbo.AdminInfo WHERE Email = '"+email+"'");
			
			if(rs.next())
				ID = rs.getInt("TeacherID");

		}
		catch(SQLException e){e.printStackTrace();}
		
		return ID;
	}
	
	public String GetName(String ID)
	{
		String name = null;
		try {
				conn = DriverManager.getConnection(dbURL, user, pass);
				Statement stmt = conn.createStatement();
				String sql = null;
				if(IsAdmin(ID))
					sql = "SELECT Name FROM NEW_DATABASE.dbo.AdminInfo WHERE Email = '"+ID+"'";
				else
					sql = "SELECT Name FROM NEW_DATABASE.dbo.StudentInfo WHERE StudentID = '"+ID+"'";
			
				ResultSet rs = stmt.executeQuery(sql);
			
				if(rs.next())
					name = rs.getString("Name");
		}
		catch(SQLException e){e.printStackTrace();}
		
		return name;
	}
	
	public String GetSurName(String ID)
	{
		String surname = null;
		try {
			conn = DriverManager.getConnection(dbURL, user, pass);
			Statement stmt = conn.createStatement();
			String sql = null;
			if(IsAdmin(ID))
				sql = "SELECT Surname FROM NEW_DATABASE.dbo.AdminInfo WHERE Email = '"+ID+"'";
			else
				sql = "SELECT Surname FROM NEW_DATABASE.dbo.StudentInfo WHERE StudentID = '"+ID+"'";
			
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next())
				surname = rs.getString("Surname");
		}
		catch(SQLException e){e.printStackTrace();}
		
		return surname;
	}
	
	public String GetExamCourse(String name) 
	{
		String CourseName = null;
		try
		{
			conn = DriverManager.getConnection(dbURL, user, pass);
			Statement stmt = conn.createStatement();
			
			String sql = "SELECT CourseName\r\n" + 
					"FROM NEW_DATABASE.dbo.ExamTime\r\n" + 
					"WHERE StudentID = '"+name+"' AND HasExam = 1 ORDER BY ExamID ASC";
			
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next())
				CourseName = rs.getString("CourseName");
		}
		catch(SQLException e) {e.printStackTrace();}
		
		return CourseName;
	}
	
	public ArrayList<String> GetQuestions(String ID,String examClass)
	{
		ArrayList<String> quest = new ArrayList<String>();
		try
		{
			conn = DriverManager.getConnection(dbURL, user, pass);
			Statement stmt = conn.createStatement();
			
			String sql = "SELECT Q.Quest\r\n" + 
					"FROM NEW_DATABASE.dbo.Questions Q, NEW_DATABASE.dbo.ExamTime E\r\n" + 
					"WHERE E.StudentID = '"+ID+"' AND E.CourseName = Q.CourseName AND E.CourseName = '"+examClass+"' "+
					"ORDER BY QuestID";
			
			
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
				quest.add(rs.getString("Quest"));
		}
		catch(SQLException e){e.printStackTrace();}
		
		return quest;
	}
	
	public ArrayList<String> GetAnswers(String ID,String Ans,String examClass)
	{
		ArrayList<String> quest = new ArrayList<String>();
		try
		{
			conn = DriverManager.getConnection(dbURL, user, pass);
			Statement stmt = conn.createStatement();
			
			String sql = "SELECT Q.Answer_"+Ans+"\r\n" + 
					"FROM NEW_DATABASE.dbo.Questions Q, NEW_DATABASE.dbo.ExamTime E\r\n" + 
					"WHERE E.StudentID = '"+ID+"' AND E.CourseName = Q.CourseName AND E.CourseName = '"+examClass+"' "+
					"ORDER BY QuestID" ;
			
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
				quest.add(rs.getString("Answer_"+Ans));

		}
		catch(SQLException e){e.printStackTrace();}
		
		return quest;
	}
	
	public Map<Integer,String> GetStudentAnswers(String ID,String examClass)
	{
		Map<Integer,String> quest = new HashMap<Integer,String>();
		try
		{	
			conn = DriverManager.getConnection(dbURL, user, pass);
			Statement stmt = conn.createStatement();
			
			String sql = "SELECT Answer,Sequence\r\n" + 
					"FROM NEW_DATABASE.dbo.StudentAnswers\r\n" + 
					"WHERE StudentID = '"+ID+"' AND CourseName = '"+examClass+"'";
				
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				int questId = rs.getInt("Sequence");
				String Ans = rs.getString("Answer");
				quest.put(questId, Ans);
			}
				
		}
		catch(SQLException e){e.printStackTrace();}
		
		return quest;
	}
	
	public List<String> GetExamResult(String ID)
	{
		List<String> list = new ArrayList<>();
		try
		{
			conn = DriverManager.getConnection(dbURL, user, pass);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT CourseName,Result FROM NEW_DATABASE.dbo.StudentResults WHERE StudentID = '"+ID+"'");
			while(rs.next())
			{
				String class_name = rs.getString("CourseName");
				float avg = GetExamAverage(class_name);
				list.add(class_name);
				list.add("Point:"+Float.toString(rs.getFloat("Result"))+" Average:"+Float.toString(avg));
			}
		}
		catch(SQLException e) {e.printStackTrace();}	
		return list;
	}
	
	public float GetExamAverage(String class_name)
	{
		float Avg = 0;
		int size = 0;
		try
		{
			conn = DriverManager.getConnection(dbURL, user, pass);
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery("SELECT Result FROM NEW_DATABASE.dbo.StudentResults WHERE CourseName = '"+class_name+"'");
			rs.last();
			size = rs.getRow();
			rs.beforeFirst();
			while(rs.next())
				Avg = Avg+ rs.getFloat("Result");
				
		}
		catch(SQLException e) {e.printStackTrace();}
		return (Avg/(float)size);
	}
	
	public List<String> GetOldExamCourse(String ID) 
	{
		List<String> list = new ArrayList<>();
		try
		{
			conn = DriverManager.getConnection(dbURL, user, pass);
			Statement stmt = conn.createStatement();
			
			String sql = "SELECT CourseName\r\n" + 
					"FROM NEW_DATABASE.dbo.ExamTime\r\n" + 
					"WHERE StudentID = '"+ID+"' AND HasExam = 0 ORDER BY ExamID ASC";
				
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
				list.add(rs.getString("CourseName"));
		}
		catch(SQLException e) {e.printStackTrace();}
		
		return list;
	}
	
	public List<String> GetOldExamQuestions(String name)
	{
		List<String> list = new ArrayList<>();
		try
		{
			conn = DriverManager.getConnection(dbURL, user, pass);
			Statement stmt = conn.createStatement();
			
			String sql = "SELECT QuestID,Quest,TrueAns\r\n" + 
					"FROM NEW_DATABASE.dbo.Questions\r\n" + 
					"WHERE CourseName = '"+name+"' ORDER BY QuestID";
			
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				int ID = rs.getInt("QuestID");
				String TrueAns = rs.getString("TrueAns");
				list.add("Quest: "+rs.getString("Quest"));
				list.add(" Answer: "+GetOldExamAnswer(TrueAns,ID));
			}
		}
		catch(SQLException e) {e.printStackTrace();}
		return list;
	}
	
	public String GetOldExamAnswer(String TrueAns,int ID)
	{
		String answer = null;
		try
		{
			conn = DriverManager.getConnection(dbURL, user, pass);
			Statement stmt = conn.createStatement();
			
			String sql = "SELECT Answer_" +TrueAns+" FROM NEW_DATABASE.dbo.Questions WHERE QuestID = "+ID;		
			ResultSet rs = stmt.executeQuery(sql);
			
			if(rs.next())
				answer = rs.getString("Answer_"+TrueAns);
		}
		catch(SQLException e) {e.printStackTrace();}
		
		return answer;
	}
	
	public ArrayList<String> GetTeacherCourses(int ID)
	{
		ArrayList<String> list = new ArrayList<>();
		try
		{
			conn = DriverManager.getConnection(dbURL, user, pass);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT CourseName FROM NEW_DATABASE.dbo.Courses WHERE TeacherID = "+ID+"ORDER BY CourseID");
			while(rs.next())
				list.add(rs.getString("CourseName"));
		}
		catch(SQLException e) {e.printStackTrace();	}
		
		return list;
	}
	
	public int GetCourseID(int teacher_id,String course_name)
	{
		int ID = 0;
		try
		{
			conn = DriverManager.getConnection(dbURL, user, pass);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT CourseID FROM NEW_DATABASE.dbo.Courses WHERE TeacherID = "+teacher_id+
					"AND CourseName = '"+course_name+"'");
			
			if(rs.next())
				ID = rs.getInt("CourseID");
				
		}
		catch(SQLException e) {e.printStackTrace();	}
		
		return ID;
	}
	
	public ArrayList<String> GetNoExamStudents(int t_id,String course)
	{
		ArrayList<String> students = new ArrayList<>();
		
		try
		{
			conn = DriverManager.getConnection(dbURL, user, pass);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT StudentID, Name,Surname FROM NEW_DATABASE.dbo.StudentInfo WHERE "
					+ "TeacherID = "+t_id+" AND StudentID NOT IN (SELECT StudentID FROM NEW_DATABASE.dbo.ExamTime WHERE CourseName = '"+
					course+"')");
			
			while(rs.next())
				students.add(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3));
		}
		catch(SQLException e) {e.printStackTrace();	}
		
		return students;
	}
	
	public ArrayList<String> GetTeacherStudents(int id)
	{
		ArrayList<String> students = new ArrayList<>();
		
		//StudentID(integer),Name(String),Surname(String)
		//Tablodan verileri çekin ekrana sayfa yazdýrýn.
		
		try
		{
			conn = DriverManager.getConnection(dbURL, user, pass);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT StudentID,Name,Surname FROM NEW_DATABASE.dbo.StudentInfo WHERE "+"TeacherID = "+id);
			
			while(rs.next())
				students.add(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3));
				
		}
		
		catch(SQLException e) {e.printStackTrace();	}
		
		return students;
	}

//*********************************************************************************************//
//*******************************************SETS**********************************************//	
//*********************************************************************************************//	
	
	public void SetStudentAnswers(Map <Integer,String> map,String ID,String examClass)
	{
		try
		{
			conn = DriverManager.getConnection(dbURL, user, pass);
			Statement stmt = conn.createStatement();
			
			Map<Integer,String> old_answers = new HashMap<Integer,String>();
			old_answers = GetStudentAnswers(ID,examClass);
			
			
			for(int i = 0; i<= map.size(); i++ )
			{
				if(map.containsKey(i) && !old_answers.containsKey(i))
				{
					stmt.executeUpdate("INSERT INTO NEW_DATABASE.dbo.StudentAnswers (CourseName,Sequence,StudentID,Answer)"+ 
									"VALUES('"+examClass+"',"+i+",'"+ID+"','"+map.get(i)+"')");
					System.out.println(examClass+":"+i+":"+ID+":"+map.get(i));
				}
				else if(map.containsKey(i) && old_answers.containsKey(i))
					if(!map.get(i).equals(old_answers.get(i)))
						stmt.executeUpdate("UPDATE NEW_DATABASE.dbo.StudentAnswers SET Answer = '"+map.get(i)+"' WHERE StudentID = '"+
								ID+"' AND Sequence = "+i+" AND CourseName = '"+examClass+"'");
				else
					continue;
			}
		}
		catch(SQLException e){e.printStackTrace();}
	}

	public void SetExamFinish(String ID,String class_name)
	{
		try
		{
			conn = DriverManager.getConnection(dbURL, user, pass);
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("UPDATE NEW_DATABASE.dbo.ExamTime SET HasExam = 0 WHERE StudentID = '"+ID+"' AND CourseName = '"+class_name+"'");
		}
		catch(SQLException e) {e.printStackTrace();}
	}
	
	public float CalculateResult(Map<Integer,String> answers,String class_name)/*Sorun*/
	{
		int i=1,size = 0;
		float result = 0,point_per_ans = 0;
		try
		{
			conn = DriverManager.getConnection(dbURL, user, pass);
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery("SELECT TrueAns FROM NEW_DATABASE.dbo.Questions WHERE CourseName = '"+class_name+"' ORDER BY QuestID");
			
			rs.last();
			size = rs.getRow();
			rs.beforeFirst();
			point_per_ans = (float) (100.0/(float)size);
			while(rs.next())
			{
				if(rs.getString("TrueAns").equals(answers.get(i)))
					result = result + point_per_ans;
				i++;
			}
			
		}
		catch(SQLException e) {e.printStackTrace();	}
		
		return result;
	}
	
	public void SetExamResult(String ID, String class_name, float result)/*Sorun*/
	{
		try
		{
			conn = DriverManager.getConnection(dbURL, user, pass);
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO NEW_DATABASE.dbo.StudentResults VALUES('"+ID+"',"+result+",'"+class_name+"')");
		}
		catch(SQLException e) {e.printStackTrace();	}
	}
	
	public void UpdatePassword(String ID, String new_pass)
	{
		try
		{
			conn = DriverManager.getConnection(dbURL, user, pass);
			Statement stmt = conn.createStatement();
			if(IsAdmin(ID))
				stmt.executeUpdate("UPDATE NEW_DATABASE.dbo.AdminInfo SET Password = '"+new_pass+"' WHERE Email = '"+ID+"'");
			else
				stmt.executeUpdate("UPDATE NEW_DATABASE.dbo.StudentInfo SET Password = '"+new_pass+"' WHERE StudentID = '"+ID+"'");
		}
		catch(SQLException e) {e.printStackTrace();	}
	}
	
	
	
	public void SetNewUser(String email,String password,String name,String surname)
	{
		try
		{
			conn = DriverManager.getConnection(dbURL, user, pass);
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO NEW_DATABASE.dbo.AdminInfo (Email,Password,Name,Surname)  VALUES('"+email+"','"+password+"','"+name+"','"+surname+"')");
		}
		catch(SQLException e) {e.printStackTrace();	}
	}
	
	public boolean SetNewStudent(String ID,String name,String surname, String password,int TeacherID)
	{
		boolean check = false;
		try
		{
			conn = DriverManager.getConnection(dbURL, user, pass);
			Statement stmt = conn.createStatement();
			int i = stmt.executeUpdate("INSERT INTO NEW_DATABASE.dbo.StudentInfo (StudentID,Name,Surname,Password,TeacherID)  VALUES('"+ID+"','"+name+"','"+surname+"','"+password+"',"+TeacherID+")");
			
			if(i == 1)
				check = true;
		}
		catch(SQLException e) {e.printStackTrace();	}
		
		return check;
	}
	
	public boolean SetNewCourse(String class_name, int AdminID)
	{
		boolean check = false;
		try
		{
			conn = DriverManager.getConnection(dbURL, user, pass);
			Statement stmt = conn.createStatement();
			int i = stmt.executeUpdate("INSERT INTO NEW_DATABASE.dbo.Courses (CourseName,TeacherID)  VALUES('"+class_name+"',"+AdminID+")");
			if(i == 1)
				check = true;
		}
		catch(SQLException e) {e.printStackTrace();	}
		
		return check;
	}
	
	public boolean SetNewQuestion(int ID,String course_name,ArrayList<String> quest)
	{
		boolean check = false;
		int course_id = GetCourseID(ID,course_name);
		
		if(course_id != 0)
		{
			try
			{
				conn = DriverManager.getConnection(dbURL, user, pass);
				Statement stmt = conn.createStatement();
				stmt.executeUpdate("INSERT INTO NEW_DATABASE.dbo.Questions (CourseID,CourseName,Quest,Answer_A,Answer_B,Answer_C,Answer_D,TrueAns)"+ 
				" VALUES("+course_id+",'"+course_name+"','"+
				quest.get(0)+"','"+quest.get(1)+"','"+quest.get(2)+"','"+quest.get(3)+"','"+quest.get(4)+"','"+quest.get(5)+"')");
				
				check = true;
			}
			catch(SQLException e) {e.printStackTrace();	}
		}	
		return check;
	}
	
	public void SetNoExamStudents(String course, ArrayList<String> stu_id)
	{
		try
		{
			conn = DriverManager.getConnection(dbURL, user, pass);
			Statement stmt = conn.createStatement();
			
			String sql = "INSERT INTO NEW_DATABASE.dbo.ExamTime (StudentID,HasExam,CourseName) VALUES ";
			
			for(int i = 0; i<stu_id.size(); i++)
			{
				sql = sql + "('"+stu_id.get(i)+"','1','"+course+"')";
				if(i != stu_id.size()-1)
					sql = sql + ",";
			}
			stmt.executeUpdate(sql);
		}
		catch(SQLException e) {e.printStackTrace();	}
	}
}
