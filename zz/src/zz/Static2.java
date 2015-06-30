package zz;

public class Static2
{
    public static int b = B.a;
    public static Static2 plus =new Static2("A");
    public static final int finalInt = (int)(Math.random()*100);
    public static B p =  new B("A");

    public static final String finalStr = "finalStr";
    public static final Integer finalInteger = new Integer(10);
    public static int a = 1;
    public static B c = null;

    public Static2(String from)
    {
        System.out.println("----------- begin A::A ----------------");
        System.out.println("A::A, from="+from);
        System.out.println("A::A, A.b="+Static2.b);
        System.out.println("A::A, A.finalInt="+Static2.finalInt);
        System.out.println("A::A, B.a="+B.a);
        System.out.println("A::A, B.plus="+B.plus);
        System.out.println("----------- end A::A ----------------");
    }

    public static void main(String[] arg)
    {
        System.out.println("main, A.b="+Static2.b);
        System.out.println("main, B.t="+B.t);
        System.out.println("main, C.a="+C.a);
    }
}

class B
{
    public static int t = Static2.a;
    
    public static Static2 plus = new Static2("B");
    public static int a = 1;

    public B(String from)
    {
        System.out.println("----------- begin B::B ----------------");
        System.out.println("B::B, from="+from);
        System.out.println("B::B, B.a="+B.a);
        System.out.println("B::B, A.a="+Static2.a);
        System.out.println("B::B, A.p="+Static2.p);
        System.out.println("B::B, A.plus="+Static2.plus);
        System.out.println("B::B, A.finalInt="+Static2.finalInt);
        System.out.println("B::B, A.finalInteger="+Static2.finalInteger);
        System.out.println("B::B, A.finalStr="+Static2.finalStr);
        System.out.println("----------- end B::B ----------------");
    }
}

class C
{
    public static final Static2 a = new Static2("C");
}