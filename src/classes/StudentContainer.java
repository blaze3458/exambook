package classes;

import java.util.*;

public class StudentContainer {
	
	private static int last_question;
	private static String student_id,student_name,student_surname,exam_lesson;
	private static boolean has_exam;
	
	private ArrayList<String> questions,answer_a,answer_b,answer_c,answer_d;
	
	private Map<Integer,String> student_ans;
	
	
	public int GetLastQuestionNum()
	{
		return last_question;
	}
	
	public void SetLastQuestionNum(int last)
	{
		last_question = last;
	}
	
	public String GetStudentID()
	{
		return student_id;
	}
	
	public void SetStudentID(String ID)
	{
		student_id = ID;
	}
	
	public String GetStudentName()
	{
		return student_name;
	}
	
	public void SetStudentName(String name)
	{
		student_name = name;
	}
	
	public String GetStudentSurname()
	{
		return student_surname;
	}
	
	public void  SetStudentSurname(String surname)
	{
		student_surname = surname;
	}
	
	public String GetExamLesson()
	{
		return exam_lesson;
		
	}
	
	public void SetExamLesson(String exam)
	{
		exam_lesson = exam;
	}
	
	public boolean HasExam()
	{
		return has_exam;
	}
	
	public void SetHasExam(boolean has)
	{
		has_exam = has;
	}
	
	public ArrayList<String> GetQuestions()
	{
		return questions;
	}
	
	public String GetQuestions(int index)
	{
		return questions.get(index);
	}
	
	public int GetQuestSize()
	{
		return questions.size();
	}
	
	public void SetQuestions(ArrayList<String> quest)
	{
		questions = quest;
	}
	
	public ArrayList<String> GetAnswer_A()
	{
		return answer_a;
	}
	
	public String GetAnswer_A(int index)
	{
		return answer_a.get(index);
	}
	
	public void SetAnswer_A(ArrayList<String> answers)
	{
		answer_a = answers;
	}
	
	public ArrayList<String> GetAnswer_B()
	{
		return answer_b;
	}
	
	public String GetAnswer_B(int index)
	{
		return answer_b.get(index);
	}
	
	public void SetAnswer_B(ArrayList<String> answers)
	{
		answer_b = answers;
	}
	
	public ArrayList<String> GetAnswer_C()
	{
		return answer_c;
	}
	
	public String GetAnswer_C(int index)
	{
		return answer_c.get(index);
	}
	
	public void SetAnswer_C(ArrayList<String> answers)
	{
		answer_c = answers;
	}
	
	public ArrayList<String> GetAnswer_D()
	{
		return answer_d;
	}
	
	public String GetAnswer_D(int index)
	{
		return answer_d.get(index);
	}
	
	public void SetAnswer_D(ArrayList<String> answers)
	{
		answer_d = answers;
	}
	
	public Map<Integer,String> GetStudentAnswers()
	{
		return student_ans;
	}
	
	public void SetStudentAnswers(Map<Integer,String> map)
	{
		student_ans = map;
	}
	
	public void ReplaceStudentAnswers(int Index,String Value)
	{
		student_ans.replace(Index, Value);
	}
	
	public void PutStudentAnswers(int Index, String Value)
	{
		student_ans.put(Index, Value);
	}
	
	public String GetStudentAnswers(int Index)
	{
		return student_ans.get(Index);
	}
	
	public boolean IsStudentAnswerContains(int Index)
	{
		return student_ans.containsKey(Index);
	}
	
	public boolean HasAnotherExam()
	{
		return  new Data().CheckExam(student_id);
	}
}
