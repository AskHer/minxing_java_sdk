import com.minxing.client.app.AppAccount;
import com.minxing.client.model.ApiErrorException;
import com.minxing.client.model.Group;


public class TestGroupCreate {

	public static void main(String[] args) {
		AppAccount account = AppAccount.loginByAccessToken("http://localhost:3000",
				"iPefUDrrardwZMWQXaZnBDBCLyY3iksJTmYtP2rcrJ0EYCJA");
		
		try {
			
			String groupType = Group.SUPPORT; //专家支持类型 还可以是 公开组Group.PUBLIC 私有组 Group.PRIVATE
			boolean isPublic = true; // 公开的工作圈
			int display_order = 0;
			Group g = account.createGroup("test_Supp212","desc of supp",isPublic,groupType,display_order);
			/*
			boolean hidden = true; //是否隐藏，仅对私有圈有效。
			int limit_size = 0; //工作圈人数限定。
			Group g2 = account.createGroup("test_Supp212","desc of supp",isPublic,groupType,hidden,limit_size,display_order);
			*/
			System.out.println("Create group:" + g);
			
		} catch (ApiErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
