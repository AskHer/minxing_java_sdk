package com.minxing.client.ocu;


public class AppMessage implements Message {

	private String title;
	private int badge;
	private String custom;

	public AppMessage( int badge,String title, String custom) {
		this.title = title;
		this.badge = badge;
		this.custom = custom;
	}

	public String getBody() {

		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"badge\":").append(badge).append(",");

		sb.append("\"title\":\"").append(title).append("\"");

		if (custom != null) {
			sb.append(",");
			sb.append("\"custom\":").append(custom);
		}
		sb.append("}");
		return sb.toString();
	}

	@Override
	public int messageType() {
		
		return APP_MESSAGE;
	}
}
