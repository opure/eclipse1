package zz;
import java.util.ArrayList ;
import java.util.List ;
public class ListTest{
	public static void main(String args[]){
		List<String> allList = null ;
		allList = new ArrayList<String>() ;	// 指定操作的泛型为String
		System.out.println("集合操作前是否为空？" + allList.isEmpty()) ;
		allList.add("Hello") ;	// 此方法由Collection接口而来
		allList.add(0,"World") ;	// 在第一个位置上添加新的内容
		allList.add("MLDN") ;	// 向Collection中加入内容
		allList.add("www.mldn.cn") ;
		System.out.println(allList.contains("Hello")?"Hello字符串存在！" : "\"Hello\"字符串不存在！") ;
		List<String> allSub = allList.subList(2,4) ;	// 字符串截取
		System.out.println("集合截取：") ;
		for(int i=0;i<allSub.size();i++){
			System.out.println(allSub.get(i) + "、") ;
		}
		System.out.println("MLDN字符串的位置：" + allList.indexOf("MLDN")) ;
		System.out.println("集合操作后是否为空？" + allList.isEmpty()) ;
	}
};