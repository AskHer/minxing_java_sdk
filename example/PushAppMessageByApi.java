import com.minxing.client.app.AppAccount;
import com.minxing.client.model.ApiErrorException;
import com.minxing.client.ocu.AppMessage;

public class PushAppMessageByApi {

	public static void main(String[] args) throws InterruptedException {



		AppAccount account = AppAccount.loginByAccessToken("http://dev3.dehuinet.com:8013", "aq6QB5O6TQR3RbHBl_oMOOCdjOYZyeZjI-bUmpKDhUh6U6zy");
		
		
		testPushAppMessage(account);

	}

	private static void testPushAppMessage(AppAccount account) {

		
		AppMessage appMsg = new AppMessage(5,"test from message","{\"param1\": 1,\"param2\": 2}",false);
		try {
			int mid = account.pushAppMessage("OA", "t66",appMsg );
			System.out.println("message send out with id:" + mid);
		} catch (ApiErrorException e) {
			e.printStackTrace();
		}

		
 
	}



}
