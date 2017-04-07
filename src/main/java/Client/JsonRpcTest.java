package Client;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;

public class JsonRpcTest {
	static JsonRpcHttpClient client;

	public static void doSomething() throws Throwable {
		client.invoke("doSomething", null);
	}

	public static int getInt(int code) throws Throwable {
		Object[] codes = new Object[] { code };
		return client.invoke("getInt", codes, Integer.class); //invoke(方法名，参数列表，返回类型）
	}

	public static int getString1(String msg,int a) throws Throwable {
		Object[] msgs = new Object[]{msg, a};
		return client.invoke("getStr1", msgs,Integer.class);
	}

	public static String getString2(String msg,int a) throws Throwable {
		Object[] msgs = new Object[]{a, msg};
		return client.invoke("getStr2", msgs,String.class);
	}

	public static void main(String[] args) throws Throwable {
		try {
			client = new JsonRpcHttpClient(new URL("http://127.0.0.1:4000/"));
			// 请求头中添加的信息
//			Map<String, String> headers = new HashMap<String, String>();
//			headers.put("Rpc-Type", "shop");
//			client.setHeaders(headers);

			doSomething();
			System.out.println("===========================Integer");
			System.out.println(getInt(32));
			System.out.println("===========================String");
			System.out.println(getString1("JsonRPC test1:",7));
			System.out.println(getString2("JsonRPC test2:",18));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
