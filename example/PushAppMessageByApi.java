import com.minxing.client.app.AppAccount;
import com.minxing.client.model.ApiErrorException;
import com.minxing.client.ocu.AppMessage;

public class PushAppMessageByApi {

	public static void main(String[] args) throws InterruptedException {



		AppAccount account = AppAccount.loginByAccessToken("http://127.0.0.1:3000", "iPefUDrrardwZMWQXaZnBDBCLyY3iksJTmYtP2rcrJ0EYCJA");
		
		
		testPushAppMessage(account);

	}

	private static void testPushAppMessage(AppAccount account) {

		
		AppMessage appMsg = new AppMessage(1,"test from message","{\"param1\": 1,\"param2\": 2}");
		try {
			int mid = account.pushAppMessage("ddod", "oajcs3@js.chinamobile.com",appMsg );
			System.out.println("message send out with id:" + mid);
		} catch (ApiErrorException e) {
			e.printStackTrace();
		}

		

	}



}
