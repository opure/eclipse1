package zz;

public class StaticTest1
{
    public static Test1 t = new Test1();
    public static int a = 0;
    public static int b;

    public static void main(String[] arg)
    {
        System.out.println(StaticTest1.a);
        System.out.println(StaticTest1.b);
    }
}

class Test1
{
    public Test1()
    {
    	System.out.println("aa");
       // StaticTest1.a++;
        System.out.println(StaticTest1.a++);
       // System.out.println(StaticTest1.a++);
        System.out.println("b");
        System.out.println(StaticTest1.b++);
        
    }
}