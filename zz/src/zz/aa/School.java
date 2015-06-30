package zz.aa;

import java.util.List ;
import java.util.ArrayList ;
public class School{
	private String name ;
	private List<Student> allStudents ;
	public School(){
		this.allStudents = new ArrayList<Student>() ;
	}
	public School(String name){
		this() ;
		this.setName(name) ;
	}
	public void setName(String name){
		this.name = name ;
	}
	public String getName(){
		return this.name; 
	}
	public List<Student> getAllStudents(){
		return this.allStudents ;
	}
	public String toString(){
		return "Ñ§Ð£Ãû³Æ£º" + this.name ;
	}
};