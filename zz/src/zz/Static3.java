package zz;

public class Static3 {
	public Static3() {
		System.out.println("Constructor");
	}

	private static int a;
	private int b;

	static {
		Static3.a = 3;
		System.out.println(a);
		Static3 t = new Static3();
		t.f();
		t.b = 1000;
		System.out.println(t.b);
		System.out.println("static 1");
	}

	static {
		Static3.a = 4;
		System.out.println(a);
		System.out.println("static 2");
	}

	public static void main(String[] args) {
		// TODO 自动生成方法存根
		System.out.println("Main");
		Static3 t = new Static3();
		t.f();

	}

	static {
		Static3.a = 5;
		System.out.println(a);
		System.out.println("static 3");
	}

	public void f() {
		System.out.println("hhahhahah");

	}
}