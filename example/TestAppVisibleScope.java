import com.minxing.client.app.AppAccount;
import com.minxing.client.model.MxException;

public class TestAppVisibleScope {

    public static void main(String[] args) {
        AppAccount account = AppAccount.loginByAccessToken("http://192.168.100.245",
                "AfzWGXEobd3le3k__q-mfWT5erzP9_kURRojRzPTMo0e4FeW");

        //删除应用可见范围的接口
        String arg0 = "socket";
        String[] arg1 = {"shan"};
        String[] arg2 = {"00340000000000000000"};//00340000000000000000===001001
        try {
            account.deleteAppVisibleScope(arg0, arg1, arg2);
        } catch (MxException e) {
            if (e.getStatusCode() != 204){
                e.printStackTrace();
            }
        }
    }
}
