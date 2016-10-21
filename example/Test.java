

import java.util.List;

import com.minxing.client.app.AppAccount;
import com.minxing.client.organization.User;

public class Test {
	
	public static void main(String[] args) throws Exception{
		
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
	}
	
}

