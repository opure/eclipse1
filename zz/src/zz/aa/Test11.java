package zz.aa;

 class Base1 {
    public void info(){
    	System.out.println("s");
    }
    
 }
 class Child1 extends Base1{
 @Override
public void info() {
	// TODO Auto-generated method stub
	
	System.out.println("a");
	
}
	 
 }
 public class Test11{
	 public static void main(String[] args) {
		 Child1 c=new Child1();
		 c.info();
		 Base1 b=(Base1)c;
		 b.info();
	}
 }
