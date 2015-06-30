package zz;
import java.util.Set ;
import java.util.HashSet ;
class Person{
	private String name ;
	private int age ;
	public Person(String name,int age){
		this.name = name ;
		this.age = age ;
	}
	public boolean equals(Object obj){	// ��дequals����ɶ���Ƚ�
		if(this==obj){
			return true ;
		}
		if(!(obj instanceof Person)){
			return false ;
		}
		Person p = (Person)obj ;	// ����ת��
		if(this.name.equals(p.name)&&this.age==p.age){
			return true ;
		}else{
			return false ;
		}
	}
	public int hashCode(){
		return this.name.hashCode() * this.age	; // ����һ����ʽ
	}
	public String toString(){
		return "������" + this.name + "�����䣺" + this.age ;
	}
};
public class RepeatDemo02{
	public static void main(String args[]){
		Set<Person> allSet = new HashSet<Person>() ;
		allSet.add(new Person("����",30)) ;
		allSet.add(new Person("����",31)) ;
		allSet.add(new Person("����",32)) ;
		allSet.add(new Person("����",32)) ;
		allSet.add(new Person("����",33)) ;
		allSet.add(new Person("����",33)) ;
		allSet.add(new Person("����",33)) ;
		System.out.println(allSet) ;
	}
};