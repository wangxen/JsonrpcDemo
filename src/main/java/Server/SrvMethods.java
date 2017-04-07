package Server;

public class SrvMethods {

	public Integer getInt(int code) {
		System.out.println("getInt:"+String.valueOf(code));
		return code+1;
	}

	public int getStr1(String msg,int a) {
		System.out.println("getStr1:"+msg);
		return a*2;
	}

	public String getStr2(int a,String msg) {
		System.out.println("getStr2:"+msg);
		return "getStr2:"+msg+String.valueOf(a);
	}

	public void doSomething() {
		System.out.println("do something!");
	}

}
