package com.minxing.client.app;

import java.util.HashMap;
import java.util.Map;

import com.minxing.client.json.JSONArray;
import com.minxing.client.json.JSONException;
import com.minxing.client.json.JSONObject;
import com.minxing.client.model.Conversation;
import com.minxing.client.model.MxException;
import com.minxing.client.ocu.TextMessage;
import com.minxing.client.organization.User;

public class AppAccountV2 extends AppAccount {

	public AppAccountV2(String serverURL, String loginName, String password,
			String clientId) {
		super(serverURL, loginName, password, clientId);
		// TODO Auto-generated constructor stub
	}

	public AppAccountV2(String serverURL, String app_id, String secret) {
		super(serverURL, app_id, secret);
		// TODO Auto-generated constructor stub
	}

	public AppAccountV2(String serverURL, String token) {
		super(serverURL, token);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 发送消息到会话中。需要调用setFromUserLoginname()设置发送者身份
	 * 
	 * @param sender_login_name
	 *            发送用户的账户名字，该账户做为消息的发送人
	 * @param conversation_id
	 *            会话的Id
	 * @param message
	 *            消息内容
	 * @return
	 */
	public TextMessage sendConversationMessage(String conversation_id,
			String message) {
		// 会话id，web上打开一个会话，从url里获取。比如社区管理员创建个群聊，里面邀请几个维护人员进来

		Map<String, String> params = new HashMap<String, String>();
		params.put("body", message);
		Map<String, String> headers = new HashMap<String, String>();

		JSONObject return_json = this.post(
				"/api/v2/conversations/" + conversation_id + "/messages",
				params, headers).asJSONObject();

		try {
			return TextMessage.fromJSON(return_json.getJSONArray("items")
					.getJSONObject(0));
		} catch (JSONException e) {
			throw new MxException("解析Json出错.", e);
		}

	}
	
	/**
	 * 发送消息到会话中。需要调用setFromUserLoginname()设置发送者身份
	 * 
	 * @param login_names
	 *            创建会话的用户列表，不需要包括创建人自己
	 * @param message
	 *            消息内容,如果不提供，只会得到一条系统消息
	 * @return Conversation对象和对象的Id。
	 */
	public Conversation createConversation(String[] login_names, String message) {
		return createConversation(login_names, message, null);
	}
	
	private Conversation createConversation(String[] login_names,
			String messageBody, Long graphId) {
		// 会话id，web上打开一个会话，从url里获取。比如社区管理员创建个群聊，里面邀请几个维护人员进来

		Map<String, String> params = new HashMap<String, String>();
		if (messageBody != null) {
			params.put("body", messageBody);
		}

		StringBuilder user_ids = new StringBuilder();
		for (int i = 0, n = login_names.length; i < n; i++) {
			User u = findUserByLoginname(null, login_names[i]);
			if (u != null) {
				if (i > 0) {
					user_ids.append(",");
				}

				user_ids.append(u.getId());

			}
		}

		params.put("direct_to_user_ids", user_ids.toString());

		if (graphId != null && graphId > 0) {
			params.put("attached[]", String.format("graph:%d", graphId));
		}

		Map<String, String> headers = new HashMap<String, String>();

		JSONObject return_json = this.post("/api/v2/conversations", params,
				headers).asJSONObject();

		Conversation created = null;
		try {
			JSONArray references_itmes = return_json.getJSONArray("references");
			for (int i = 0, n = references_itmes.length(); i < n; i++) {
				JSONObject r = references_itmes.getJSONObject(i);

				if ("conversation".equals(r.getString("type"))) {
					long convesation_id = r.getLong("id");
					created = new Conversation(convesation_id);
					break;
				}

			}

		} catch (JSONException e) {
			throw new MxException("解析Json出错.", e);
		}
		return created;
	}
	
	
	
	public TextMessage sendMessageToUser(long toUserId, String message) {
		// 会话id，web上打开一个会话，从url里获取。比如社区管理员创建个群聊，里面邀请几个维护人员进来

		Map<String, String> params = new HashMap<String, String>();
		params.put("body", message);
		Map<String, String> headers = new HashMap<String, String>();

		JSONObject new_message = this.post(
				"/api/v2/conversations/to_user/" + toUserId, params, headers)
				.asJSONObject();
		try {
			return TextMessage.fromJSON(new_message.getJSONArray("items")
					.getJSONObject(0));
		} catch (JSONException e) {
			throw new MxException("解析Json出错.", e);
		}
	}
	
	
	
	
	/**
	 * 使用接入端的Token登录系统
	 * 
	 * @param serverURL
	 *            服务器的访问地址
	 * @param bearerToken
	 *            bearerToken，从接入端的配置中获取
	 * @return
	 */
	public static AppAccount loginByAccessToken(String serverURL,
			String bearerToken) {
		return new AppAccountV2(serverURL, bearerToken);
	}

	/**
	 * 使用接入端的appid、appsecret登录系统，
	 * 
	 * @param serverURL
	 *            系统的url.
	 * @param app_id
	 *            接入端应用的Id,在接入端管理的页面上可以找到。
	 * @param secret
	 *            接入端应用的秘钥，可以在接入端的页面上看到。
	 * @return
	 */
	public static AppAccount loginByAppSecret(String serverURL, String app_id,
			String secret) {
		return new AppAccountV2(serverURL, app_id, secret);
	}

	/**
	 * 使用用户名密码方式登录系统
	 * 
	 * @param serverURL
	 *            服务器的访问地址
	 * @param loginName
	 *            系统登录名
	 * @param password
	 *            用户密码
	 * @param clientId
	 *            使用的注册客户端，可以设置为4,表示PC的客户端。0-web 1-ios 2-android
	 * @return
	 */
	public static AppAccount loginByPassword(String serverURL,
			String loginName, String password, String clientId) {

		return new AppAccountV2(serverURL, loginName, password, clientId);
	}


}
