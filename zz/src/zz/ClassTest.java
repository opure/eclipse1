package zz;
class Price{
	final static Price INSTANCE=new Price(2.8);
	static double initprice=20;
	double currentprice;
	public Price(double  discount){
		currentprice=initprice-discount;
		}
}

public class ClassTest {
	public static void main(String[] args) {
		System.out.println(Price.INSTANCE.currentprice);
		Price p=new Price(3);
		System.out.println(p.currentprice);
	}

}
