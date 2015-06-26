import java.io.File;

import com.minxing.connector.app.AppAccount;
import com.minxing.connector.model.ShareLink;
import com.minxing.connector.ocu.TextMessage;

public class SendMessageByApi {

	public static void main(String[] args) {
		AppAccount account = AppAccount.loginByToken("http://localhost:3000",
				"iPefUDrrardwZMWQXaZnBDBCLyY3iksJTmYtP2rcrJ0EYCJA");

		sendTextMessageToGroup(account);
		
//		sendMessageAndFile(account);
//	
//		sendSharelinkToGroup(account);

	}

	private static void sendMessageAndFile(AppAccount account) {

		File file = new File("/Users/liujiang/Downloads/125.jpg");
		String conversation_id = "20020002";// 会话id
		String content = "推送到群聊";
		long fromUserId = 30766;// web上查询某人的详情，从url里获取id

		account.setFromUserId(fromUserId);

		// 推送文字消息到聊天
		TextMessage msg = account.sendConversationMessage(conversation_id, content);
		System.out.println(msg);

		// 推送文件到聊天
		msg = account.sendConversationFileMessage(conversation_id, file);
		System.out.println(msg);
	}

	
	private static void sendTextMessageToGroup(AppAccount account) {

		account.setFromUserId(30766);
		
		//发送工作圈消息
		TextMessage message = account.sendTextMessageToGroup(50, "一条工作圈消息");
		System.out.println(message);
	}

	private static void sendSharelinkToGroup(AppAccount account) {

		ShareLink slink = new ShareLink();

		slink.setURL("https://www.baidu.com");
		slink.setTitle("分享链接");
		slink.setImageURL("https://www.baidu.com/img/bdlogo.png");
		slink.setDescription("描述信息");
				
		account.setFromUserLoginName("oajcs3@js.chinamobile.com");
		TextMessage message =  account.sendSharelinkToGroup(50, "测试api分享", slink);
		System.out.println(message);
	}

}
