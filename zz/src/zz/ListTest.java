package zz;
import java.util.ArrayList ;
import java.util.List ;
public class ListTest{
	public static void main(String args[]){
		List<String> allList = null ;
		allList = new ArrayList<String>() ;	// ָ�������ķ���ΪString
		System.out.println("���ϲ���ǰ�Ƿ�Ϊ�գ�" + allList.isEmpty()) ;
		allList.add("Hello") ;	// �˷�����Collection�ӿڶ���
		allList.add(0,"World") ;	// �ڵ�һ��λ��������µ�����
		allList.add("MLDN") ;	// ��Collection�м�������
		allList.add("www.mldn.cn") ;
		System.out.println(allList.contains("Hello")?"Hello�ַ������ڣ�" : "\"Hello\"�ַ��������ڣ�") ;
		List<String> allSub = allList.subList(2,4) ;	// �ַ�����ȡ
		System.out.println("���Ͻ�ȡ��") ;
		for(int i=0;i<allSub.size();i++){
			System.out.println(allSub.get(i) + "��") ;
		}
		System.out.println("MLDN�ַ�����λ�ã�" + allList.indexOf("MLDN")) ;
		System.out.println("���ϲ������Ƿ�Ϊ�գ�" + allList.isEmpty()) ;
	}
};