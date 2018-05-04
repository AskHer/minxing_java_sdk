import com.minxing.client.app.AppAccount;
import com.minxing.client.app.OcuMessageSendResult;
import com.minxing.client.ocu.SsoKey;
import com.minxing.client.ocu.TextMessage;

/**
 * @author SuZZ on 2018/5/4.
 */
public class TestSendOcuMessage {

    public static void main(String[] args) {
        AppAccount account = AppAccount.loginByAccessToken("http://example.com", "bearerToken");
        TextMessage textMessage = new TextMessage("1341341");
        OcuMessageSendResult result = account.sendOcuMessageExceptUsers(textMessage, "ocuId", "ocuSecret", new String[]{"t1"}, SsoKey.LOGIN_NAME);
        OcuMessageSendResult result2 = account.sendOcuMessageExceptUsers(textMessage, "ocuId", "ocuSecret", new String[]{"t1"});
    }

}
