class Bowl{  
    public Bowl(int maker) {  
        System.out.println("Bowl("+maker+")");  
    }  
    void f1(int maker){  
        System.out.println("f1("+maker+")");  
    }  
}  
  
class Table{  
    static Bowl bowl1 = new Bowl(1);  
    static Bowl bowl2 = new Bowl(2);  
      
    public Table() {  
        System.out.println("Table()");  
        bowl2.f1(1);  
    }  
    void f2(int maker){  
        System.out.println("f2("+maker+")");  
    }  
}  
  
class Cupboard{  
    Bowl bowl3 = new Bowl(3);  
    static Bowl bowl4 = new Bowl(4);  
    static Bowl bowl5 = new Bowl(5);  
      
    public Cupboard() {  
        System.out.println("cupboard()");  
        bowl4.f1(2);  
    }  
    void f3(int maker){  
        System.out.println("f3("+maker+")");  
    }  
}  
  
public class Static5 {  
      
    static Table table = new Table();  
    static Cupboard cupboard = new Cupboard();  
      
    public static void main(String[] args) {  
        System.out.println("creating new cupboard() in main");  
        new Cupboard();  
        System.out.println("creating new cupboard() in main");  
        new Cupboard();  
        table.f2(1);  
        cupboard.f3(1);  
          
    }  
}  