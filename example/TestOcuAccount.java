
import com.minxing.client.app.AppAccount;
import com.minxing.client.app.OcuMessageSendResult;
import com.minxing.client.ocu.Article;
import com.minxing.client.ocu.ArticleMessage;

public class TestOcuAccount {
	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			// oa.sendMessageToUsersStr(new
			// HtmlMessage("测试<a href='http://www.baidu.com'>百度</a>"),
			// "dev001@dehui220.com.cn");
			String title = "嘟嘟【待办】OA事务申请" + i;// 提醒标题
			String content = "嘟嘟运营FATCA\\CRS需求上线申请（RL04994）贾晓梅【jiaxm06】温馨提示：不支持PC端点击!";// 提醒内容
			ArticleMessage am = new ArticleMessage();
			Article article = new Article(title, content, "", "http://oawxn.taikang.com/moa//m/s?s=EXKVes0tP93BoyetMuqX8mFzl+FLNFjZKd7WlNrBtokpqSMdB3RI9w==&quot", null);
			am.addArticle(article);
			AppAccount account = AppAccount.loginByAccessToken(
					"http://192.168.100.230:8030",
					"45hmk4pjz5h80lk8imNXzhnJWW_haUznfwYDI1cRrKBFUOkG");

//			String ocuId = "40dbd78cc10e32d7a36f2a518460f7f7";
//			String ocuSecret = "88cb9f89de14332abc787486a4249b30";
			String ocuId = "app_notification";
			String ocuSecret = "4fa6a29b49a273240a0947c4a20178ed";

			OcuMessageSendResult send_to = account.sendOcuMessageToUsers(
					new String[] {"69"}, am, ocuId, ocuSecret);
			System.out.println("发送至:" + send_to.getCount() + "人");
			System.out.println("发送消息Id:" + send_to.getMessageId());
			System.out.println("发送用户Id列表:" + send_to.getUserIds());
			Thread.currentThread().sleep(1000);
		}
	}

}
