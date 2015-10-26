import com.minxing.client.app.AppAccount;
import com.minxing.client.model.ApiErrorException;

public class TestGroupUpdateApi {

	public static void main(String[] args) {
		AppAccount account = AppAccount.loginByAccessToken(
				"http://127.0.0.1:3000",
				"iPefUDrrardwZMWQXaZnBDBCLyY3iksJTmYtP2rcrJ0EYCJA");
		

		try {
			account.setFromUserLoginName("oajcs3@js.chinamobile.com");
			
			//删除组需要耗时很长时间，有可能超时
			account.updateGroupInfo(126L,"新名字","新描述信息");

		} catch (ApiErrorException e) {
			e.printStackTrace();
		}

	}

}
