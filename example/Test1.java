import com.minxing.client.app.AppAccount;
import com.minxing.client.organization.AppVisibleScope;
import com.minxing.client.organization.User;


public class Test1 {
	
	public static void main(String[] args) throws Exception{

        AppAccount account = AppAccount.loginByAccessToken("http://dev8.dehuinet.com:8018",
                "DDQ_ltvOeFI2q6_GYywAo5c2c4qfE6nGeh16iZN8LFu632y3");
	account.setFromUserLoginName("t63"); //确保用社区管理员的身份来调用api
//	AppVisibleScope s = (AppVisibleScope) account.getAppVisibleScope("mail");
////
////	System.out.println(s.getDepartment().size());
////	System.out.println(s.getUsers().size());

        String[] str = new String[]{"t63"};
        User[] users = account.findUserByLoginNames(str);
        System.out.println(users[0]);

	}

}
