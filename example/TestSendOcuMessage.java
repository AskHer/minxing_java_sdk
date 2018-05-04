import com.minxing.client.app.AppAccount;
import com.minxing.client.app.OcuMessageSendResult;
import com.minxing.client.ocu.SsoKey;
import com.minxing.client.ocu.TextMessage;

/**
 * @author SuZZ on 2018/5/4.
 */
public class TestSendOcuMessage {

    public static void main(String[] args) {
        AppAccount account = AppAccount.loginByAccessToken("http://dev8.dehuinet.com:8018", "OWgiMPiENmJlefpUaJiXZfKKeFR3Xx4y50P1A1hi5GuoA0Ry");
        TextMessage textMessage = new TextMessage("1341341");
        OcuMessageSendResult attendance_cccb = account.sendOcuMessageExceptUsers(textMessage, "attendance_cccb", "9483cbabb69eac8bc0f08b2fd9d4074f", new String[]{"800052", "800053"}, SsoKey.LOGIN_NAME);
        System.out.println(attendance_cccb.getCount());
    }

}
