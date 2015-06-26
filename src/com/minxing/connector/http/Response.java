package com.minxing.connector.http;

import com.minxing.connector.json.JSONArray;
import com.minxing.connector.json.JSONException;
import com.minxing.connector.json.JSONObject;
import com.minxing.connector.model.MxException;

public class Response {
	
    

    private int statusCode;
    private String responseAsString = null;

    public Response()  {
    	
    }
    
    Response(String content) {
        this.responseAsString = content;
    }
    
    public int getStatusCode() {
        return statusCode;
    }

    public JSONObject asJSONObject() throws MxException {
        try {
            return new JSONObject(responseAsString);
        } catch (JSONException jsone) {
            throw new MxException(jsone.getMessage() + ":" + this.responseAsString, jsone);
        }
    }

    public JSONArray asJSONArray() throws MxException {
        try {
        	return  new JSONArray(responseAsString);  
        } catch (Exception jsone) {
            throw new MxException(jsone.getMessage() + ":" + this.responseAsString, jsone);
        }
    }

    @Override
    public String toString() {
        if(null != responseAsString){
            return responseAsString;
        }
        return "Response{" +
                "statusCode=" + statusCode +
                ", responseString='" + responseAsString + '\'' +
                '}';
    }

	public String getResponseAsString() {
		return responseAsString;
	}

	public void setResponseAsString(String responseAsString) {
		this.responseAsString = responseAsString;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
    
}
