package api.v2;

import com.minxing.client.app.AppAccount;
import com.minxing.client.app.AppAccountV2;
import com.minxing.client.organization.User;


public class MultiConversation {

	public static void main(String[] ss) {
		AppAccount acc = AppAccountV2.loginByAccessToken("http://localhost",
				"qd37z3o2AuJhjGpaa9kWEyOmixueaJI1ILhnlKQTGe4Ao4bp");
		acc.setFromUserLoginName("90001");
		//创建一个聊天并发送信息
		//createAndSendMessage( acc);
		//只发一个信息
		//onlySendMessage( acc);
		//sendMessageToUser(acc);
		sendMessageToUserByUser(acc);
	}
	
	
	
	public static void createAndSendMessage(AppAccount acc){
		String[] login_names = new String[]{"90002","90003","90211"};
		acc.createConversation(login_names, "说句话");
	}
	
	public static void onlySendMessage(AppAccount acc){
		acc.sendConversationMessage("20020643", "987654321");
	}
	
	public static void sendMessageToUser(AppAccount acc){
		acc.sendMessageToUser(1416, "31312312");
	}
	
	
	public static void sendMessageToUserByUser(AppAccount acc){
		User u = new User();
		u.setId(1416L);
		acc.sendMessageToUser(u, "31312312");
	}

}
