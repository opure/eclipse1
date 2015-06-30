package zz;

import java.util.Scanner;

public class HanNuo {
	public static void hanoi(int n,char one,char two,char three){
     if(n==1)
    	 move(one,three);
     else{
     hanoi(n-1,one,three,two);
     move(one,three);
     hanoi(n-1,two,one,three);
     }
	}
public static void move(char x, char y) {
		// TODO Auto-generated method stub
	System.out.println("´Ó"+x+"ÒÆ¶¯µ½"+y);
		
	}
public static void main(String[] args) {
	Scanner s=new Scanner(System.in);
	int n=s.nextInt();
	 hanoi(n,'A','B','C');
   
}
}
