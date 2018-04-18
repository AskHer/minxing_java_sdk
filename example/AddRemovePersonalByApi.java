import com.minxing.client.app.AppAccount;
import com.minxing.client.json.JSONObject;

public class AddRemovePersonalByApi {
    public static void main(String[] args){
       fromOutsideCommunityAddPersonal();
//        removePartTimePersonal();
    }
    private static void fromOutsideCommunityAddPersonal(){
        AppAccount account = AppAccount.loginByAccessToken("http://dev8.dehuinet.com:8018", "OxQL65s9WUtiKGWl1rmrNcrg4pp-EKpvWcQ5d8UtIMXZ9v1X");

        account.setFromUserLoginName("admin");
        String[] network_ids = new String[]{};//登陆人所在社区ID
        String[] dept_ids = new String[]{};//登陆人所在部门ID
        String[] user_ids = new String[]{"2322"};//导入用户ID
        int dept_id = 25572;//导入用户部门ID
        boolean recursive = false;//导入包括子部门的用户
        boolean create_dept = false;//按照原有组织建立部门
        JSONObject message = account.fromOutsideCommunityAddPersonal(network_ids,dept_ids,user_ids,
                                                dept_id,recursive,create_dept);
        System.out.println(message);
    }
    private static void removePartTimePersonal(){
        AppAccount account = AppAccount.loginByAccessToken("http://dev8.dehuinet.com:8018", "OxQL65s9WUtiKGWl1rmrNcrg4pp-EKpvWcQ5d8UtIMXZ9v1X");

        int dept_id = 25572;//登陆人所在部门ID
        int user_id = 2322;//移除的用户ID
        boolean bool = account.removePartTimePersonal(dept_id,user_id);

        System.out.println(bool);
    }
}
