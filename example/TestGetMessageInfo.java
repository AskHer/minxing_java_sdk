import com.minxing.client.app.AppAccount;
import com.minxing.client.model.ApiErrorException;
import com.minxing.client.ocu.Message;



public class TestGetMessageInfo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AppAccount account = AppAccount.loginByAccessToken(
				"http://127.0.0.1:3000",
				"iPefUDrrardwZMWQXaZnBDBCLyY3iksJTmYtP2rcrJ0EYCJA");
		 Message m;
		try {
			account.setFromUserLoginName("oajcs3@js.chinamobile.com");
			m = account.getMessage(67886L);
			System.out.println("Get message:" + m);
		} catch (ApiErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
	}

}
