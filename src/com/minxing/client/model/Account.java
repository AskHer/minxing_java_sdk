package com.minxing.client.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.minxing.client.http.HttpClient;
import com.minxing.client.http.Response;
import com.minxing.client.json.JSONArray;
import com.minxing.client.json.JSONObject;

public abstract class Account {

	protected HttpClient client = new HttpClient();

	public JSONObject get(String url) throws MxException {
		return get(url, new PostParameter[0]);
	}

	protected JSONObject get(String url, PostParameter[] params)
			throws MxException {
		return api(url, "get", params, new PostParameter[0], true);
	}

	public JSONArray getJSONArray(String url) throws MxException {
		return getJSONArray(url, new PostParameter[0]);
	}

	protected JSONArray getJSONArray(String url, PostParameter[] params)
			throws MxException {
		return apiJSONArray(url, "get", params, new PostParameter[0], true);
	}

	protected JSONObject post(String url, Boolean WithTokenHeader)
			throws MxException {
		return api(url, "post", new PostParameter[0], new PostParameter[0],
				WithTokenHeader);
	}

	protected JSONObject post(String url, PostParameter[] params,
			Boolean WithTokenHeader) throws MxException {
		return api(url, "post", params, new PostParameter[0], WithTokenHeader);
	}

	protected JSONObject post(String url, PostParameter[] params,
			PostParameter[] headers, Boolean WithTokenHeader)
			throws MxException {
		return api(url, "post", params, headers, WithTokenHeader);
	}

	protected JSONArray post(String url, PostParameter[] params, File file,
			Boolean WithTokenHeader) throws MxException {
		return api(url, "post", params, new PostParameter[0], file,
				WithTokenHeader);
	}

	protected JSONArray post(String url, PostParameter[] params,
			PostParameter[] headers, File file, Boolean WithTokenHeader)
			throws MxException {
		return api(url, "post", params, headers, file, WithTokenHeader);
	}

	protected JSONObject put(String url, PostParameter[] params)
			throws MxException {
		return api(url, "put", params, new PostParameter[0], true);
	}

	protected JSONObject delete(String url, PostParameter[] params)
			throws MxException {
		return api(url, "delete", params, new PostParameter[0], true);
	}

	protected JSONObject delete(String url) throws MxException {
		return api(url, "delete", new PostParameter[0], new PostParameter[0],
				true);
	}

	protected JSONObject api(String url, String method, PostParameter[] params,
			PostParameter[] headers, Boolean WithTokenHeader)
			throws MxException {
		return apiForResponse(url, method, params, headers, WithTokenHeader)
				.asJSONObject();
	}

	protected JSONArray api(String url, String method, PostParameter[] params,
			PostParameter[] headers, File file, Boolean WithTokenHeader)
			throws MxException {
		return apiForResponse(url, method, params, headers, file,
				WithTokenHeader).asJSONArray();
	}

	protected JSONArray apiJSONArray(String url, String method,
			PostParameter[] params, PostParameter[] headers,
			Boolean WithTokenHeader) throws MxException {
		return apiForResponse(url, method, params, headers, WithTokenHeader)
				.asJSONArray();
	}

	private Response apiForResponse(String url, String method,
			PostParameter[] params, PostParameter[] headers,
			Boolean WithTokenHeader) throws MxException {
		if (method == null) {
			method = "get";
		}
		method = method.trim().toLowerCase();
		List<PostParameter> paramsList = new ArrayList<PostParameter>(
				Arrays.asList(params));
		List<PostParameter> headersList = new ArrayList<PostParameter>(
				Arrays.asList(headers));

		String tempUrl = beforeRequest(url, paramsList, headersList);
		int paramsSize = paramsList.size();
		int headersSize = headersList.size();
		params = new PostParameter[paramsList.size()];
		headers = new PostParameter[headersList.size()];
		for (int i = 0; i < paramsSize; i++) {
			params[i] = paramsList.get(i);
		}
		for (int i = 0; i < headersSize; i++) {
			headers[i] = headersList.get(i);
		}
		if (tempUrl != null && !tempUrl.trim().equals("")) {
			url = tempUrl;
		}
		Response response = null;
		if (method.equals("get")) {
			response = client.get(url, params, headers);
		} else if (method.equals("post")) {
			response = client.post(url, params, headers, WithTokenHeader);
		} else if (method.equals("put")) {
			response = client.put(url, params, headers, WithTokenHeader);
		} else if (method.equals("delete")) {
			response = client.delete(url, params, headers);
		}

		return response;
	}

	private Response apiForResponse(String url, String method,
			PostParameter[] params, PostParameter[] headers, File file,
			Boolean WithTokenHeader) throws MxException {
		method = method.trim().toLowerCase();
		List<PostParameter> paramsList = new ArrayList<PostParameter>(
				Arrays.asList(params));
		List<PostParameter> headersList = new ArrayList<PostParameter>(
				Arrays.asList(headers));

		String tempUrl = beforeRequest(url, paramsList, headersList);
		int paramsSize = paramsList.size();
		int headersSize = headersList.size();
		params = new PostParameter[paramsList.size()];
		headers = new PostParameter[headersList.size()];
		for (int i = 0; i < paramsSize; i++) {
			params[i] = paramsList.get(i);
		}
		for (int i = 0; i < headersSize; i++) {
			headers[i] = headersList.get(i);
		}
		if (tempUrl != null && !tempUrl.trim().equals("")) {
			url = tempUrl;
		}
		Response response = null;
		response = client.post(url, params, headers, file, WithTokenHeader);

		return response;
	}

	protected abstract String beforeRequest(String url,
			List<PostParameter> paramsList, List<PostParameter> headersList);
}
