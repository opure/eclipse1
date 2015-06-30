package zz;

import java.util.List ;
import java.util.ArrayList ;
public class Student{
	private String name ;
	private int age ;
	private List<Course> allCourses ;
	public Student(){
		this.allCourses = new ArrayList<Course>() ;
	}
	public Student(String name,int age){
		this() ;
		this.name = name ;
		this.age = age ;
	}
	public List<Course> getAllCourses(){
		return this.allCourses ;
	}
	public void setName(String name){
		this.name = name ;
	}
	public void setAge(int age){
		this.age = age ;
	}
	public String getName(){
		return this.name ;
	}
	public int getAge(){
		return this.age ;
	}
	public String toString(){
		return "学生姓名：" + this.name + "；年龄：" + this.age ;
	}
};