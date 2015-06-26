package com.minxing.connector.organization;

import java.util.HashMap;
import java.util.Map;

public class Department extends Organization {
	private String network_id; // 所在社区ID
	private String short_name; // 部门简称
	private String full_name; // 部门全名
	private String display_order; // 部门排序
	private String dept_code; // 部门编码
	private String parent_dept_code; // 父部门编码
	private Boolean root = false;

	private String network_name;

	public String getNetwork_name() {
		return network_name;
	}

	public void setNetwork_name(String network_name) {
		this.network_name = network_name;
	}

	public String getNetwork_id() {
		return network_id;
	}

	public void setNetwork_id(String network_id) {
		this.network_id = network_id;
	}

	public String getShort_name() {
		return short_name;
	}

	public void setShort_name(String short_name) {
		this.short_name = short_name;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getDisplay_order() {
		return display_order;
	}

	public void setDisplay_order(String display_order) {
		this.display_order = display_order;
	}

	public String getDept_code() {
		return dept_code;
	}

	public void setDept_code(String dept_code) {
		this.dept_code = dept_code;
	}

	public String getParent_dept_code() {
		return parent_dept_code;
	}

	public void setParent_dept_code(String parent_dept_code) {
		this.parent_dept_code = parent_dept_code;
	}

	public Boolean getRoot() {
		return root;
	}

	public void setRoot(Boolean root) {
		this.root = root;
	}

	public HashMap<String, String> toHash() {
		HashMap<String, String> params = new HashMap<String, String>();

		params.put("network_id", this.getNetwork_id());
		params.put("short_name", this.getShort_name());
		params.put("full_name", this.getFull_name());
		params.put("display_order", this.getDisplay_order());
		params.put("dept_code", this.getDept_code());
		params.put("parent_dept_code", this.getParent_dept_code());
		return params;
	}

}
