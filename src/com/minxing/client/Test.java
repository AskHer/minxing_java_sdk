package com.minxing.client;

import com.minxing.client.app.AppAccount;
import com.minxing.client.json.JSONObject;
import com.minxing.client.organization.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static com.lambdaworks.codec.Base16.encode;

public class Test {

	/*public static void main(String[] args) throws Exception{
		
		User u = new User();
		AppAccount account = AppAccount.loginByAccessToken("http://192.168.100.245","XrhAoAjWyWktkeLvVqAi8lf9z444mYgRxImwOx8K8gphnBbm");
		u.setLoginName("njhh1");
//		u.setName("name");
//		u.setExt2("ext2");
//		account.addNewUser(u);
		u.setExt2("333");
		account.updateUser(u);
//		List list = account.getAllDepartments();
		System.out.println(1);
	}*/

    static Logger log = Logger.getLogger(Test.class.getSimpleName());

    public static void main(String[] args) throws Exception {

        AppAccount account = AppAccount.loginByAccessToken(
                "http://test.dehuinet.com:8030",
                "45hmk4pjz5h80lk8imNXzhnJWW_haUznfwYDI1cRrKBFUOkG");

        /*String token_ = "IAAAAAtLGKG8kPfnHVTzFNndjUDezh3IvOTsS2fO22jB8gwS";
        User u = account.verifyOcuSSOToken(token_, null);
        log.info("u.getLoginName() " + u.getLoginName());*/

		String token = account.getAccessToken("t53", "111111");
		log.info(token);

    }




}

