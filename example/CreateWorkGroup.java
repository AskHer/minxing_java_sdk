import com.minxing.client.app.AppAccount;
import com.minxing.client.model.ApiErrorException;
import com.minxing.client.model.Group;


public class CreateWorkGroup {

	public static void main(String[] args) {
		AppAccount account = AppAccount.loginByAccessToken("http://localhost:3000",
				"iPefUDrrardwZMWQXaZnBDBCLyY3iksJTmYtP2rcrJ0EYCJA");
		
		try {
			
			String groupType = Group.SUPPORT; //专家支持类型 还可以是 公开组Group.PUBLIC 私有组 Group.PRIVATE
			
			Group g = account.createGroup("test_Supp212","desc of supp",groupType);
			System.out.println("Create group:" + g);
			
		} catch (ApiErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
