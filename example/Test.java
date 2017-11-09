import com.minxing.client.app.AppAccount;
import com.minxing.client.organization.User;


public class Test {

    public static void main(String[] args) throws Exception {
//		String url = "http://192.168.100.245";
//		String token = "XrhAoAjWyWktkeLvVqAi8lf9z444mYgRxImwOx8K8gphnBbm";
        String url = "http://dev3.dehuinet.com:8013";
        String token = "NdLtIi9QpWY7ZGhf4cOpVmc5BpEyFJMPf34J1mXuWdXjNcbA";
        AppAccount account = AppAccount.loginByAccessToken(url, token);
        User toUser = new User();
        toUser.setLoginName("t13");
        account.setFromUserLoginName("t11");
        System.out.println(account.sendMessageToUser(toUser, "test"));

    }

}

