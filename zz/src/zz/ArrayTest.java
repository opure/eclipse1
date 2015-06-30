package zz;

public class ArrayTest {
	private long[] a;
	private int ele;
	public ArrayTest(int max){
		a=new long[max];
		ele=0;
	}
	public boolean find(long searchKey){
		int j=0;
		for(j=0;j<ele;j++)
			if(a[j]==searchKey)
			break;
		if(j==ele)
			return false;
			else
				return true;
	}
	public void insert(int value){
		a[ele]=value;
		ele++;
	}
	public void insert1(int value){
		int j;
		for(j=0;j<ele;j++)
			if(a[j]>value)
				break;
		for(int k=ele;k>j;k--)
	    a[k]=a[k-1];
		a[j]=value;
		ele++;
	}
	public boolean delete(int vlaue){
		int i;
		for(i=0;i<ele;i++)
			if(a[i]==vlaue)
				break;
		if(i==ele)
			return false;
		else{
			for(int k=i;k<ele;k++)
		     a[k]=a[k+1];
			 ele--;
		return true;
	}
}
	public void display(){
		for(int j=0;j<ele;j++)
			System.out.println(a[j]);
		
	}

}
