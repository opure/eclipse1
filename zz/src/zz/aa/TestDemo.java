package zz.aa;

import java.util.Iterator ;
public class TestDemo{
	public static void main(String args[]){
		School sch = new School("�廪��ѧ") ;	// ����ѧУ
		Student s1 = new Student("����",21) ;
		Student s2 = new Student("����",22) ;
		Student s3 = new Student("����",23) ;
		sch.getAllStudents().add(s1) ;
		sch.getAllStudents().add(s2) ;
		sch.getAllStudents().add(s3) ;
		s1.setSchool(sch) ;
		s2.setSchool(sch) ;
		s3.setSchool(sch) ;
		System.out.println(sch) ;
		Iterator<Student> iter = sch.getAllStudents().iterator() ;
		while(iter.hasNext()){
			System.out.println("\t|- " + iter.next()) ;
		}
	}
};