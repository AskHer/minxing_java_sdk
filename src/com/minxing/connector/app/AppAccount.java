package com.minxing.connector.app;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.minxing.connector.http.HttpClient;
import com.minxing.connector.http.Response;
import com.minxing.connector.json.JSONArray;
import com.minxing.connector.json.JSONException;
import com.minxing.connector.json.JSONObject;
import com.minxing.connector.model.Account;
import com.minxing.connector.model.ApiErrorException;
import com.minxing.connector.model.MxException;
import com.minxing.connector.model.MxVerifyException;
import com.minxing.connector.model.PostParameter;
import com.minxing.connector.model.ShareLink;
import com.minxing.connector.ocu.Message;
import com.minxing.connector.ocu.TextMessage;
import com.minxing.connector.organization.Department;
import com.minxing.connector.organization.Network;
import com.minxing.connector.organization.User;

public class AppAccount extends Account {

	// protected String appId = AppConfig.getValue("AppID");
	// protected String appSecret = AppConfig.getValue("AppSecret");
	// protected String redirectUri = AppConfig.getValue("redirect_uri");
	// protected String authorizeUrl = AppConfig.getValue("authorize_url");
	//
	// protected String apiPrefix = AppConfig.getValue("api_prefix");
	// protected String accessToken = AppConfig.getValue("access_token");

	private String _token = null;
	private String _loginName;
	private String _serverURL;
	private long _currentUserId = 0;

	private AppAccount(String serverURL, String token) {
		this._serverURL = serverURL;
		this._token = token;
		client.setToken(this._token);
		client.setTokenType("Bearer");
	}

	private AppAccount(String serverURL, String loginName, String password,
			String clientId) {
		this._serverURL = serverURL;
		PostParameter grant_type = new PostParameter("grant_type", "password");
		PostParameter login_name = new PostParameter("login_name", loginName);
		PostParameter passwd = new PostParameter("password", password);
		PostParameter app_id = new PostParameter("app_id", clientId);
		PostParameter[] params = new PostParameter[] { grant_type, login_name,
				passwd, app_id };

		HttpClient _client = new HttpClient();
		Response return_rsp = _client.post(serverURL + "/oauth2/token", params,
				new PostParameter[] {}, false);

		if (return_rsp.getStatusCode() == 200) {

			JSONObject o = return_rsp.asJSONObject();
			try {
				_token = o.getString("access_token");
				client.setToken(this._token);
				client.setTokenType("Bearer");

			} catch (JSONException e) {
				throw new MxException("解析返回值出错", e);
			}
		} else {
			throw new MxException("HTTP " + return_rsp.getStatusCode() + ": "
					+ return_rsp.getResponseAsString());
		}

	}

	public void setFromUserLoginName(String loginName) {
		this._loginName = loginName;

	}

	public void setFromUserId(long userId) {
		this._currentUserId = userId;
	}

	public static AppAccount loginByToken(String serverURL, String bearerToken) {
		return new AppAccount(serverURL, bearerToken);
	}

	public static AppAccount loginByPassword(String serverURL,
			String loginName, String password, String clientId) {

		return new AppAccount(serverURL, loginName, password, clientId);
	}

	public JSONObject get(String url, Map<String, String> params)
			throws MxException {
		PostParameter[] pps = createParams(params);
		return this.get(url, pps);
	}

	public JSONObject post(String url, Map<String, String> params,
			Map<String, String> headers) throws MxException {
		PostParameter[] pps = createParams(params);
		PostParameter[] hs = createParams(headers);
		return this.post(url, pps, hs, true);
	}

	public JSONArray post(String url, Map<String, String> params,
			Map<String, String> headers, File file) throws MxException {
		PostParameter[] pps = createParams(params);
		PostParameter[] hs = createParams(headers);
		return this.post(url, pps, hs, file, true);
	}

	public JSONObject put(String url, Map<String, String> params)
			throws MxException {
		PostParameter[] pps = createParams(params);
		return this.put(url, pps);
	}

	public JSONObject delete(String url, Map<String, String> params)
			throws MxException {
		PostParameter[] pps = createParams(params);
		return this.delete(url, pps);
	}

	private PostParameter[] createParams(Map<String, String> params) {
		if (params == null) {
			return new PostParameter[0];
		}
		PostParameter[] pps = new PostParameter[params.size()];
		int i = 0;
		for (Iterator<String> it = params.keySet().iterator(); it.hasNext();) {
			String key = it.next();
			String value = params.get(key);
			PostParameter p = new PostParameter(key, value);
			pps[i++] = p;
		}
		return pps;
	}

	@Override
	protected String beforeRequest(String url, List<PostParameter> paramsList,
			List<PostParameter> headersList) {

		if (this._currentUserId != 0L) {
			PostParameter as_user = new PostParameter("X_AS_USER",
					this._currentUserId);
			headersList.add(as_user);
		} else if (this._loginName != null && this._loginName.length() > 0) {
			PostParameter as_user = new PostParameter("X_AS_USER",
					this._loginName);
			headersList.add(as_user);
		}

		// TODO Auto-generated method stub
		if (url.trim().startsWith("http://")
				|| url.trim().startsWith("https://")) {
			return url;
		} else {
			if (!url.trim().startsWith("/")) {
				url = "/" + url.trim();
			}
			// url = rootUrl + apiPrefix + url;
			url = _serverURL + url;
			return url;
		}

	}

	public long[] uploadConversationFile(String conversation_id, File file) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("conversation_id", conversation_id);
		Map<String, String> headers = new HashMap<String, String>();

		JSONArray arr = null;
		long[] filesArray = new long[] {};
		try {
			arr = this.post("api/v1/uploaded_files", params, headers, file);
			filesArray = new long[arr.length()];
			for (int i = 0; i < arr.length(); i++) {
				JSONObject o = arr.getJSONObject(i);
				filesArray[i] = o.getLong("id");
			}
		} catch (MxException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return filesArray;
	}

	public String getIdByLoginname(String networkname, String loginname) {

		try {
			JSONObject o = this.get("/api/v1/users/" + loginname
					+ "/by_login_name?network_name=" + networkname);
			return o.getString("id");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public User findUserByLoginname(String loginname) {

		JSONObject o = this
				.get("/api/v1/users/" + loginname + "/by_login_name");

		try {
			User user = new User();
			user.setId(o.getLong("id"));
			user.setLogin_name(o.getString("login_name"));
			user.setPassword(o.getString("password"));
			user.setEmail(o.getString("email"));
			user.setName(o.getString("name"));
			user.setTitle(o.getString("login_name"));
			user.setCellvoice1(o.getString("cellvoice1"));
			user.setCellvoice2(o.getString("cellvoice2"));
			user.setWorkvoice(o.getString("workvoice"));
			user.setEmp_code(o.getString("emp_code"));
			user.setDept_code(o.getString("dept_code"));

			user.setHidden(o.getString("hidden"));
			user.setSuspended(o.getString("suspended"));

			return user;
		} catch (JSONException e) {
			throw new MxException("解析Json出错.", e);
		}

	}

	// ///////////////////////////////////////////////////////////////////////////////////////////////
	// Send messages
	//

	/**
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

		JSONObject return_json = this.post("/api/v1/conversations/"
				+ conversation_id + "/messages", params, headers);

		return TextMessage.fromJSON(return_json);

	}

	public TextMessage sendConversationFileMessage(String conversation_id,
			File file) {
		long[] file_ids = uploadConversationFile(conversation_id, file);
		Map<String, String> params = new HashMap<String, String>();
		for (int i = 0; i < file_ids.length; i++) {
			params.put("attached[]",
					String.format("uploaded_file:%d", file_ids[i]));
		}
		Map<String, String> headers = new HashMap<String, String>();

		JSONObject return_json = this.post("/api/v1/conversations/"
				+ conversation_id + "/messages", params, headers);
		return TextMessage.fromJSON(return_json);
	}

	public TextMessage sendTextMessageToGroup(long groupId, String message) {
		return sendTextMessageToGroup(groupId, message, null);
	}

	public TextMessage sendSharelinkToGroup(long groupId, String message,
			ShareLink shareLink) {
		return sendTextMessageToGroup(groupId, message, shareLink.toJson());
	}

	public TextMessage sendTextMessageToGroup(long groupId, String message,
			String story) {

		Map<String, String> params = new HashMap<String, String>();
		params.put("group_id", String.valueOf(groupId));
		params.put("body", message);

		if (story != null) {
			params.put("story", story);
		}

		Map<String, String> headers = new HashMap<String, String>();

		JSONObject new_message = this.post("/api/v1/messages", params, headers);
		return TextMessage.fromJSON(new_message);

	}

	public void sendMessageToUser(long toUserId, String message) {
		// 会话id，web上打开一个会话，从url里获取。比如社区管理员创建个群聊，里面邀请几个维护人员进来

		Map<String, String> params = new HashMap<String, String>();
		params.put("body", message);
		Map<String, String> headers = new HashMap<String, String>();

		this.post("/api/v1/conversations/to_user" + toUserId, params, headers);

	}

	public void sendOcuMessageToUsers(String[] toUserIds, Message message,
			String ocuId, String ocuSecret) {
		// 会话id，web上打开一个会话，从url里获取。比如社区管理员创建个群聊，里面邀请几个维护人员进来

		Map<String, String> params = new HashMap<String, String>();
		params.put("body", message.toString());
		params.put("content_type", String.valueOf(message.messageType()));

		StringBuffer sb = new StringBuffer();
		int i = 0;
		for (String id : toUserIds) {
			sb.append(id);
			if (i > 0) {
				sb.append(",");
			}
			i++;
		}

		String direct_to_user_ids = sb.toString();

		params.put("direct_to_user_ids", direct_to_user_ids);
		Map<String, String> headers = new HashMap<String, String>();

		this.post("/api/v1/conversations/ocu_messages", params, headers);

	}

	// ///////////////////////////////////////////////////////////////////////////////////////////////
	//
	//
	public String createMXSSOToken(String loginName) {

		Map<String, String> params = new HashMap<String, String>();
		params.put("login_name", loginName);

		Map<String, String> headers = new HashMap<String, String>();

		try {
			JSONObject json = this.post("/api/v1/conversations/ocu_messages",
					params, headers);
			return json.getString("token");
		} catch (MxException e) {
			e.printStackTrace();

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	// //////////////////////////////////////////////////////////////////////////////////////////
	//
	// Department Api
	//
	public Department addNewDepartment(Department departement)
			throws ApiErrorException {

		try {

			HashMap<String, String> params = departement.toHash();
			Map<String, String> headers = new HashMap<String, String>();

			JSONObject json_result = post("/api/v1/departments", params,
					headers);
			int code = json_result.getInt("code");

			if (code > 0 && code != 200 && code != 201) {

				String msg = json_result.getString("message");
				throw new ApiErrorException(code, msg);

			}

			departement.setId(json_result.getLong("id"));
			departement.setNetworkId(json_result.getLong("network_id"));
			
			return departement;

		} catch (JSONException e) {
			throw new ApiErrorException("返回JSON错误", 500, e);
		}

	}

	public void updateDepartment(Department departement)
			throws ApiErrorException {

		try {

			HashMap<String, String> params = departement.toHash();

			JSONObject json_result = put("/api/v1/departments", params);

			int code = json_result.getInt("code");

			if (code != 200 && code != 201) {

				String msg = json_result.getString("message");
				throw new ApiErrorException(code, msg);

			}

		} catch (JSONException e) {
			throw new ApiErrorException("返回JSON错误", 500, e);
		}

	}

	public void deleteDepartment(String departmentCode, boolean deleteWithUsers)
			throws ApiErrorException {

		try {

			HashMap<String, String> params = new HashMap<String, String>();
			if (deleteWithUsers) {
				params.put("force", "true");
			}

			JSONObject json_result = delete("/api/v1/departments/"
					+ departmentCode, params);
			int code = json_result.getInt("code");

			if (code != 200 && code != 201) {

				String msg = json_result.getString("message");
				throw new ApiErrorException(code, msg);

			}

		} catch (JSONException e) {
			throw new ApiErrorException("返回JSON错误", 500, e);
		}

	}

	// //////////////////////////////////////////////////////////////////////////////////////////
	//
	// User Api
	//
	public User addNewUser(User user) throws ApiErrorException {

		try {

			HashMap<String, String> params = user.toHash();
			Map<String, String> headers = new HashMap<String, String>();

			JSONObject json_result = post("/api/v1/departments", params,
					headers);
			int code = json_result.getInt("code");

			if (code > 0 && code != 200 && code != 201) {

				String msg = json_result.getString("message");
				throw new ApiErrorException(code, msg);

			}
			user.setId(json_result.getLong("id"));
			return user;
			
		} catch (JSONException e) {
			throw new ApiErrorException("返回JSON错误", 500, e);
		}

	}

	public void updateUser(User user) throws ApiErrorException {

		try {

			HashMap<String, String> params = user.toHash();

			JSONObject json_result = put("/api/v1/users", params);
			int code = json_result.getInt("code");

			if (code != 200 && code != 201) {

				String msg = json_result.getString("message");
				throw new ApiErrorException(code, msg);

			}
			

		} catch (JSONException e) {
			throw new ApiErrorException("返回JSON错误", 500, e);
		}

	}

	public void deleteUser(String loginName) throws ApiErrorException {
		deleteUser(loginName, false);
	}

	public void deleteUser(String loginName, boolean withDeleteAccount)
			throws ApiErrorException {

		try {

			HashMap<String, String> params = new HashMap<String, String>();
			params.put("login_name", loginName);
			if (withDeleteAccount) {
				params.put("with_account", "true");
			}

			JSONObject json_result = delete("/api/v1/users/deleted", params);
			int code = json_result.getInt("code");

			if (code != 200 && code != 201) {

				String msg = json_result.getString("message");
				throw new ApiErrorException(code, msg);

			}

		} catch (JSONException e) {
			throw new ApiErrorException("返回JSON错误", 500, e);
		}

	}

	// //////////////////////////////////////////////////////////////////////////////////////////
	//
	// Network Api
	//
	public Network createNetwork(Network network) throws ApiErrorException {

		try {

			HashMap<String, String> params = network.toHash();
			Map<String, String> headers = new HashMap<String, String>();

			JSONObject json_result = post("/api/v1/networks", params, headers);
			int code = json_result.getInt("code");

			if (code > 0 && code != 200 && code != 201) {

				String msg = json_result.getString("message");
				throw new ApiErrorException(code, msg);

			}

			network.setId(json_result.getLong("id"));
			return network;

		} catch (JSONException e) {
			throw new ApiErrorException("返回JSON错误", 500, e);
		}

	}

	public void updateNetwork(Network network) throws ApiErrorException {

		try {

			HashMap<String, String> params = network.toHash();

			JSONObject json_result = put("/api/v1/networks", params);

			int code = json_result.getInt("code");

			if (code != 200 && code != 201) {

				String msg = json_result.getString("message");
				throw new ApiErrorException(code, msg);

			}

		} catch (JSONException e) {
			throw new ApiErrorException("返回JSON错误", 500, e);
		}

	}

	public void deleteNetwork(String name) throws ApiErrorException {

		try {

			HashMap<String, String> params = new HashMap<String, String>();
			params.put("name", name);

			JSONObject json_result = delete("/api/v1/networks", params);
			int code = json_result.getInt("code");

			if (code != 200 && code != 201) {
				String msg = json_result.getString("message");
				throw new ApiErrorException(code, msg);

			}

		} catch (JSONException e) {
			throw new ApiErrorException("返回JSON错误", 500, e);
		}

	}

	/**
	 * 校验应用商店的应用携带的SSOTOken是否有效，通过连接minxing服务器，检查token代表的敏行用户的身份。
	 * 
	 * @param token
	 *            客户端提供的SSO Token.
	 * @param app_id
	 *            校验客户端提供的Token是不是来自这个app_id产生的，如果不是，则校验失败。
	 * @return 如果校验成功，返回token对应的用户信息
	 * @throws MxVerifyException
	 *             校验失败，则抛出这个异常.
	 */
	public User verifyAppSSOToken(String token, String app_id)
			throws MxVerifyException {

		try {
			JSONObject o = this.get("/api/v1/oauth/user_info/" + token);

			User user = new User();
			user.setId(o.getLong("user_id"));
			user.setLogin_name(o.getString("login_name"));

			user.setEmail(o.getString("email"));
			user.setName(o.getString("name"));
			user.setTitle(o.getString("login_name"));
			user.setCellvoice1(o.getString("cell_phone"));
			user.setCellvoice2(o.getString("preferred_mobile"));

			user.setEmp_code(o.getString("emp_code"));
			user.setNetworkId(o.getLong("network_id"));

			return user;
		} catch (JSONException e) {
			throw new MxVerifyException("校验Token:" + token + "错误", e);
		}

	}

	/**
	 * 校验公众号消息打开时携带的 SSOTOken，通过连接minxing服务器，检查token代表的敏行用户的身份。
	 * 
	 * @param token
	 *            客户端提供的SSO Token.
	 * @param app_id
	 *            校验客户端提供的Token是不是来自这个app_id产生的，如果不是，则校验失败。
	 * @return 如果校验成功，返回token对应的用户信息
	 * @throws MxVerifyException
	 *             校验失败，则抛出这个异常.
	 */

	public User verifyOcuSSOToken(String token, String ocu_id) {

		try {
			JSONObject o = this.get("/api/v1/oauth/user_info/" + token);

			User user = new User();
			user.setId(o.getLong("user_id"));
			user.setLogin_name(o.getString("login_name"));

			user.setEmail(o.getString("email"));
			user.setName(o.getString("name"));
			user.setTitle(o.getString("login_name"));
			user.setCellvoice1(o.getString("cell_phone"));
			user.setCellvoice2(o.getString("preferred_mobile"));

			user.setEmp_code(o.getString("emp_code"));
			user.setNetworkId(o.getLong("network_id"));

			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
