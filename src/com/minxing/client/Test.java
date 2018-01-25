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


        AppAccount account = AppAccount.loginByAccessToken("http://oatest.bgctv.com.cn",
                "Y6Y5B9q3WRQu56NXckC0sOUiLYrk4Kf7FN2GLo5csfueYorz");
        System.out.print(account.verifyPassword("liuliucs", "92d7ddd2a010c59511dc2905b7e14f64", true));

    }




}

