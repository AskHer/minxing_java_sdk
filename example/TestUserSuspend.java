import com.minxing.client.app.AppAccount;
import com.minxing.client.json.JSONObject;

public class TestUserSuspend {

    public static void main(String[] args) throws Exception {
        AppAccount account = AppAccount.loginByAccessToken("http://dev8.dehuinet.com:8018",
                "r69S9Yl2owuP98W_w0OOuPUr2ud1eNTAaq-rqFCPtb56tj6n");

        JSONObject jb = account.suspend("t123,tooo");

        System.out.println(jb);

    }
}
