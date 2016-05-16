import com.minxing.client.app.AppAccount;
import com.minxing.client.model.ApiErrorException;
import com.minxing.client.ocu.AppMessage;

public class PushAppMessageByApi {

	public static void main(String[] args) throws InterruptedException {



		AppAccount account = AppAccount.loginByAccessToken("http://test.dehuinet.com:8030", "4jszLAnk3O0sjwO88043NmALCxFCEfLQVulrFqKx03czHfz1");
		
		
		testPushAppMessage(account);

	}

	private static void testPushAppMessage(AppAccount account) {

		
		AppMessage appMsg = new AppMessage(1,"test from message","{\"param1\": 1,\"param2\": 2}");
		try {
			int mid = account.pushAppMessage("hot", "t66",appMsg );
			System.out.println("message send out with id:" + mid);
		} catch (ApiErrorException e) {
			e.printStackTrace();
		}

		

	}



}
