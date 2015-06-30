package zz.aa;

public class Student{
	private String name ;
	private int age ;
	private School school; // 一个学生属于一个学校
	public Student(String name,int age){
		this.setName(name) ;
		this.setAge(age) ;
	}
	public void setSchool(School school){
		this.school = school ;
	}
	public School getSchool(){
		return this.school ;
	}
	public void setName(String name){
		this.name = name ;
	}
	public void setAge(int age){
		this.age = age ;
	}
	public String getName(){
		return this.name; 
	}
	public int getAge(){
		return this.age ;
	}
	public String toString(){
		return "学生姓名：" + this.name + "；年龄：" + this.age ;
	}
};