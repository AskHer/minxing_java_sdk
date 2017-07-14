

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.minxing.client.app.AppAccount;
import com.minxing.client.model.ApiErrorException;
import com.minxing.client.ocu.UserInfo;
import com.minxing.client.organization.User;

public class BgctvKickTest {
	
	public static void main(String[] args) throws Exception{
		product();
		
	}
	private static void product() throws ApiErrorException{
		List<String> loginnames = new ArrayList<String>();
//		loginnames.add("zhangweijia");
		loginnames.add("liangyan");
		loginnames.add("sunxiaoxue");
//		loginnames.add("xieying");
//		loginnames.add("yanshi");
		
		AppAccount account = AppAccount.loginByAccessToken("https://111oa.bgctv.com.cn","g5B-zwTk6uUXeVpoA8k1wjSq0CBLyGwWMJaXEIJMhsFej3d8");
		List<UserInfo> userlist = account.getAllUsersInDepartment("001", true);
		System.out.println(userlist.size());
		Iterator<UserInfo> users = userlist.iterator();
		int i= 1;
		while(users.hasNext()){
			UserInfo userinfo = users.next();
			String login_name = userinfo.getLogin_name();
			System.out.println(i + login_name);
			if(!"admin@bgctv".equals(login_name)){
//				if(loginnames.contains(login_name)){
					if(!userinfo.isSuppended()){
						System.out.println("-------处理过的-----"+login_name);
//						account.kick(login_name);
						User user = account.findUserByLoginname(login_name);
//						user.setSuspended(true);
//						account.updateUser(user);
					}
//				}
				i++;
			}
		}
	}
	private static void test() throws ApiErrorException{
		AppAccount account = AppAccount.loginByAccessToken("http://oatest.bgctv.com.cn","HOzQOwMFyFTaiEIFjxNN3RiyORo4O91LFY_a6bIUorD-2boR");
		List<UserInfo> userlist = account.getAllUsersInDepartment("001", true);
		System.out.println(userlist.size());
		Iterator<UserInfo> users = userlist.iterator();
		int i= 1;
		while(users.hasNext()){
			UserInfo userinfo = users.next();
			String login_name = userinfo.getLogin_name();
			System.out.println(i + login_name);
			if(!"admin@bgctv".equals(login_name)){
//				if("zhangweijia".equals(login_name)){
				if(!userinfo.isSuppended()){
					System.out.println("-------处理过的-----"+login_name);
					account.kick(login_name);
					User user = account.findUserByLoginname(login_name);
					user.setSuspended(true);
					account.updateUser(user);
				}
//				}
				i++;
			}
		}
	}
	
}

