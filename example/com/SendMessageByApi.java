package com;
import java.io.File;

import com.minxing.client.app.AppAccount;
import com.minxing.client.app.OcuMessageSendResult;
import com.minxing.client.model.Conversation;
import com.minxing.client.model.Graph;
import com.minxing.client.model.ShareLink;
import com.minxing.client.ocu.Article;
import com.minxing.client.ocu.ArticleMessage;
import com.minxing.client.ocu.TextMessage;
import com.minxing.client.organization.User;

public class SendMessageByApi {

	@SuppressWarnings("unused")
	public static void main(String[] args) throws InterruptedException {

		
		SendMessageByApi sendMessageByApi = new SendMessageByApi();
		Thread thread1 = new Thread(sendMessageByApi.new TestSendOcu());
//		Thread thread2 = new Thread(sendMessageByApi.new TestSendOcu());
//		Thread thread3 = new Thread(sendMessageByApi.new TestSendOcu());
//		Thread thread4 = new Thread(sendMessageByApi.new TestSendOcu());
//		Thread thread5 = new Thread(sendMessageByApi.new TestSendOcu());
//		Thread thread6 = new Thread(sendMessageByApi.new TestSendOcu());
//		Thread thread7 = new Thread(sendMessageByApi.new TestSendOcu());
//		Thread thread8 = new Thread(sendMessageByApi.new TestSendOcu());
		thread1.start();
//		thread2.start();
//		thread3.start();
//		thread4.start();
//		thread5.start();
//		thread6.start();
//		thread7.start();
//		thread8.start();
		

//		AppAccount account = AppAccount.loginByAccessToken(
//				"http://localhost:3000",
//				"iPefUDrrardwZMWQXaZnBDBCLyY3iksJTmYtP2rcrJ0EYCJA");
		AppAccount account = AppAccount.loginByAccessToken("http://test3.dehuinet.com:8033", "UrsOPrAa6NRL8j0PHphcoycu_fHgq2rzwJaQR2_Uqxj2pjpN");
		
//		AppAccount account = AppAccount.loginByAccessToken("http://tkimtest.taikang.com", "lLzYhGkPb0Itv_n2cLnWqWNtyRsEqWPmsQrLbUbL6vxgMtq6");

//		account.setFromUserLoginName("admin@dehuinet");
//		account.sendConversationMessage("20046711","123");
		
//		account.uploadUserAvatar("90001", "/Users/helhades/Documents/html5_notification/www/img/img_headPic3.png");
		
//		sendTextMessageToGroup(account);

		// sendMessageAndFile(account);
		//
		// sendSharelinkToGroup(account);
		// sendTextMessageToUser();

		// createConversation(account);
		// createConversationWithGraph(account);
//		sendGroupMessageWithImage(account);
		//createConversation(account);
//		createConversationWithGraph(account);

	}

	private static void sendMessageAndFile(AppAccount account) {

		File file = new File("/Users/liujiang/Downloads/125.jpg");
		String conversation_id = "20020002";// 会话id
		String content = "推送到群聊";
		long fromUserId = 30766;// web上查询某人的详情，从url里获取id
		account.setFromUserId(fromUserId);
		// 推送文件到聊天
		// 推送文字消息到聊天
		TextMessage msg = account.sendConversationMessage(conversation_id,
				content);

		msg = account.sendConversationFileMessage(conversation_id, file);
		System.out.println(msg);

	}

	private static void sendGroupMessageWithImage(AppAccount account) {

		File file = new File("/Users/liujiang/Pictures/bg_full@1x.png");
		long group_id = 49;// 会话id
		
		long fromUserId = 30766;// web上查询某人的详情，从url里获取id
		account.setFromUserId(fromUserId);
		// 推送文件到聊天
		// 推送文字消息到聊天
		TextMessage msg = account.sendGroupMessageWithImage(group_id,"工作圈图片发送测试", file);
		System.out.println(msg);

	}

	
	private static void createConversation(AppAccount account) {

		account.setFromUserLoginName("oajcs3@js.chinamobile.com");

		String[] login_names = new String[] { "w8@js.chinamobile.com",
				"zzzzzzw", "18611112222" };
		Conversation conversation = account.createConversation(login_names,
				"大家在这里讨论吧");
		System.out.println(conversation);

	}

	private static void createConversationWithGraph(AppAccount account) {

		account.setFromUserLoginName("oajcs3@js.chinamobile.com");

		String[] login_names = new String[] { "w8@js.chinamobile.com",
				"zzzzzzw", "18611112222" };
		Graph g = new Graph();
		g.setURL("http://data.com/graph/1");
		g.setTitle("这个是一个Graph的测试");
		// g.setAppURL("lantch_app://oa/001");
		g.setDescription("OA讨论的Graph的描述");

		Conversation conversation = account.createConversationWithGraph(
				login_names, "大家在这里讨论吧", g);
		System.out.println(conversation);

	}

	private static void sendTextMessageToGroup(AppAccount account)
			throws InterruptedException {

		account.setFromUserId(96);
		for (int i = 0; i < 1000; i++) {
			Thread.sleep(333);
			// 发送工作圈消息
			TextMessage message = account.sendTextMessageToGroup(3,
					"一条工作圈消息 - " + i);
			System.out.println(message);
			message = account.sendConversationMessage("20038545", "发送一条聊天信息 - "+i);
			System.out.println(message);
		}
	}

	private static void sendTextMessageToUser() {
//		AppAccount account = AppAccount.loginByAppSecret(
//				"http://localhost:3000", "1002",
//				"d9e17fd5d3ad54f348492f673029af45");
		
		AppAccount account = AppAccount.loginByAccessToken("http://tkimtest.taikang.com", "lLzYhGkPb0Itv_n2cLnWqWNtyRsEqWPmsQrLbUbL6vxgMtq6");
		
		// account.setFromUserId(30938);
		account.setFromUserLoginName("admin@taikang");

		// 发送消息给莫个人

//		User a = new User();
//		a.setLoginName("renky01");
		User user = account.findUserByLoginname("test91");
		// a.setId(30766L);
		
		ArticleMessage m = new ArticleMessage();
		Article article = new Article("【待办】111", "", "", "http://www.baidu.com", null);
		m.addArticle(article);
		
		

		TextMessage message = account.sendMessageToUser(user.getId(), m.getBody(), String.valueOf(m.messageType()));
		System.out.println(message);
	}

	private static void sendSharelinkToGroup(AppAccount account) {

		ShareLink slink = new ShareLink();

		slink.setURL("https://www.baidu.com");
		slink.setTitle("分享链接");
		slink.setImageURL("https://www.baidu.com/img/bdlogo.png");
		slink.setDescription("描述信息");

		account.setFromUserLoginName("oajcs3@js.chinamobile.com");
		TextMessage message = account
				.sendSharelinkToGroup(50, "测试api分享", slink);
		System.out.println(message);
	}
	
	private class TestSendOcu implements Runnable {

		@Override
		public void run() {
//			AppAccount account = AppAccount.loginByAccessToken("http://test3.dehuinet.com:8033", "UrsOPrAa6NRL8j0PHphcoycu_fHgq2rzwJaQR2_Uqxj2pjpN");
//			AppAccount account = AppAccount.loginByAccessToken("http://tkimtest.taikang.com", "lLzYhGkPb0Itv_n2cLnWqWNtyRsEqWPmsQrLbUbL6vxgMtq6");
			AppAccount account = AppAccount.loginByAccessToken("https://tkim.taikang.com", "In-v2TEc2AhLpML074pc_Ac3AyXPz02jeDihYGTNswJ5zA1-");
			
			
			long begin = System.currentTimeMillis();
			for (int i = 0; i < 1; i++) {
				// 推送公众号
//				String title = "【待办】早会提醒" + i;// 提醒标题
//				String content = "5月2日\t5月3日\t5月4日\t5月5日\r\n谢锦文\t张晶晶\t 陈然\t李云燕\r\n  "+""+"温馨提示：不支持PC端点击!";// 提醒内容
				
				String title = "99嘟嘟侠【待办】OA事务+-+申+-+请" + i;// 提醒标题
				String content = "泰康集团\\集团总部\\数据信息中心\\移动互联部 - 张杰 嘟嘟运营FATCA\\CRS需求 - 上线\\申请（RL04994）贾晓梅【jiaxm06】温\\馨提示：不支持<br>PC端点击!";// 提醒内容
				content = content.replaceAll("\t", " ");
				content = content.replaceAll("\\\\", "\\\\\\\\");
				ArticleMessage m = new ArticleMessage();
				Article article = new Article(title, content, "", "http://oawxn.taikang.com/moa//m/s?s=EXKVes0tP93BoyetMuqX8mFzl+FLNFjZKd7WlNrBtokpqSMdB3RI9w==", null);// http://www.baidu.com
				m.addArticle(article);
				
				String[] toUserIds = new String[]{"test94","test98"};
//				String ocuId = "8ad0934c57d110871b7f1545cde88b52";
//				String ocuSecret = "eb59a4038f3e14d36f043534951af234";
				String ocuId = "4ef45f3a6edf7ee3c826bf896fdb8a7a";
				String ocuSecret = "47a397c9278b3d7c9f3a06fa3ce80160";
				
				OcuMessageSendResult omsr = account.sendOcuMessageToUsers(toUserIds, m, ocuId, ocuSecret);
				System.out.println(">>>>>> 第" + Thread.currentThread().getName() + "-" + i + " : " + omsr.getCount());
			}
			
			long end = System.currentTimeMillis();
			System.out.println(">>> 共耗时：" + (end - begin));
		}
		
	}

}
