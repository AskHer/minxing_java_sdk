import com.minxing.client.app.AppAccount;
import com.minxing.client.organization.AppVisibleScope;


public class Test1 {
	
	public static void main(String[] args) throws Exception{

	AppAccount account = AppAccount.loginByAccessToken("http://test.dehuinet.com:8030","hkRMY18-DTe0GHW1qhHrElw97f9ZtYI5YUTN63Incgw_u2sz");
	account.setFromUserLoginName("admin@shequ"); //确保用社区管理员的身份来调用api
	AppVisibleScope s = (AppVisibleScope) account.getAppVisibleScope("mail");

	System.out.println(s.getDepartment().size());
	System.out.println(s.getUsers().size());

	}

}
