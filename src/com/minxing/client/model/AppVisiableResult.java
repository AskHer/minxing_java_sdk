package com.minxing.client.model;

import java.util.ArrayList;
import java.util.List;

import com.minxing.client.json.JSONException;
import com.minxing.client.json.JSONObject;

public class AppVisiableResult {

	private boolean success;

	private int code;

	private String errorMessage;

	private List<String> errorLoginNames;

	private List<String> errorDeptCodes;

	public AppVisiableResult(JSONObject users) {
		try {

			code = users.getInt("code");
			if (code != 200) {
				errorMessage = users.getString("error");
				if (errorMessage.startsWith("用户名不存在")) {
					errorLoginNames  = new ArrayList<String>();
					String ids = errorMessage.substring(errorMessage.indexOf("[") + 1,
							errorMessage.length() - 1);

					String[] idsStrs = ids.split(",");
					for (String id : idsStrs) {
						String loginName = id.trim().substring(1,
								id.trim().length() - 1);
						errorLoginNames.add(loginName);
					}
				}else if(errorMessage.startsWith("部门不存在")) {
					errorDeptCodes  = new ArrayList<String>();
					String ids = errorMessage.substring(errorMessage.indexOf("[") + 1,
							errorMessage.length() - 1);

					String[] idsStrs = ids.split(",");
					for (String id : idsStrs) {
						String loginName = id.trim().substring(1,
								id.trim().length() - 1);
						errorDeptCodes.add(loginName);
					}
				}
			}else{
				success = true;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public boolean isSuccess() {
		return success;
	}

	public int getCode() {
		return code;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public List<String> getErrorLoginNames() {
		return errorLoginNames;
	}

	public List<String> getErrorDeptCodes() {
		return errorDeptCodes;
	}

}
