import com.minxing.client.app.AppAccount;
import com.minxing.client.model.ApiErrorException;

public class TestPing {

	public static void main(String[] args) {
		AppAccount account = AppAccount.loginByAccessToken(
				"http://test.dehuinet.com:8030",
				"45hmk4pjz5h80lk8imNXzhnJWW_haUznfwYDI1cRrKBFUOkG");

		try {

			Long user_id = account.ping();
			System.out.println("user_id:" + user_id);

		} catch (ApiErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
