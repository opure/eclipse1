package zz;

public class xuanze {
	public static void main(String[] args) {
		int k,t, i,j;
	int a[]={3,6,1,9,4};
  for(i=0;i<4;i++){
	  k=i;
  for(j=i+1;j<5;j++)
	  if(a[j]<a[k])
	  k=j;
      t=a[k];
      a[k]=a[i];
      a[i]=t;
  }
	
	for(int x:a){
      System.out.println(x);
	}
	
	}
}
