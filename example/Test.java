

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.minxing.client.app.AppAccount;
import com.minxing.client.organization.User;

public class Test {
	
	public static void main(String[] args) throws Exception{
//		String url = "http://192.168.100.245";
//		String token = "XrhAoAjWyWktkeLvVqAi8lf9z444mYgRxImwOx8K8gphnBbm";
		String url = "http://test.dehuinet.com:8030";
		String token = "lf-gJ4ATa8mkAwTTMvq8mV_5aD826q-Qr5AjhSkuoRZhp-k1";
		AppAccount account = AppAccount.loginByAccessToken(url,token);

		System.out.println(JSON.toJSONString(account.findUserByLoginname("liuwen")));
//		List list = account.getAllDepartments();
		System.out.println(1);
	}
	
}

