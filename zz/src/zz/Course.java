package zz;

import java.util.List ;
import java.util.ArrayList ;
public class Course{
	private String name ;
	private int credit ;
	private List<Student> allStudents ;
	public Course(){
		this.allStudents = new ArrayList<Student>() ;
	}
	public Course(String name,int credit){
		this() ;
		this.name = name ;
		this.credit = credit ;
	}
	public List<Student> getAllStudents(){
		return this.allStudents ;
	}
	public void setName(String name){
		this.name = name  ;
	}
	public void setCredit(int credit){
		this.credit = credit ;
	}
	public String getName(){
		return this.name ;
	}
	public int getCredit(){
		return this.credit ;
	}
	public String toString(){
		return "�γ����ƣ�" + this.name + "���γ�ѧ�֣�" + this.credit ;
	}
};