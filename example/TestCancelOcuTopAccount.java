import com.minxing.client.app.AppAccount;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestCancelOcuTopAccount {


    public static void main(String[] args) throws IOException {

        testCancelOcuTop();
    }


    /**
     * 发公众号消息测试
     */
    public static void testCancelOcuTop() {


        //创建接入端对象，参数1：敏行地址，参数2：接入端token，在敏行后台中获取这个token，然后加到配置文件或写到代码里
        AppAccount account = AppAccount.loginByAccessToken(
                "http://dev5.dehuinet.com:8015",   //敏行地址
                "xZ10A9ooK-oKLeS5EnzfIrIK8kSBxDh_F8uuj_lkxWLJySNL");  //接入端access token
        //社区ID
        int network_id = 3;
        List<Long> msgIds = new ArrayList<>();
        msgIds.add(33941l);
        HashMap<String, List<Long>> msg = new HashMap<>();
        msg.put("msgIds", msgIds);
        account.cancelOcuTop(msg, network_id);
    }

}
