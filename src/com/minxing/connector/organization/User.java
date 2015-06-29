package com.minxing.connector.organization;

import java.util.HashMap;

public class User extends Organization {
	private Long id; // 用户id
	private String login_name; // Account's login_name
	private String password; // 密码
	private String email; // 邮箱
	private String name; // 姓名
	private String title; // 职务
	private String cellvoice1; // 手机号码
	private String cellvoice2; // 备用手机号码
	private String workvoice; // 固定电话
	private String emp_code; // 工号
	private String dept_code; // 部门标识
	private String display_order; // 排序
	
	private long network_id;

	private String hidden; // set user be hidden “true” "false"
	private String suspended; // 是否禁用 “true” "false"

	private String with_account; // if true also delete the user account
	// 扩展字段
	private String ext1;
	private String ext2;
	private String ext3;
	private String ext4;
	private String ext5;
	private String ext6;
	private String ext7;
	private String ext8;
	private String ext9;
	private String ext10;
	private String network_name;

	public String getNetwork_name() {
		return network_name;
	}

	public void setNetwork_name(String network_name) {
		this.network_name = network_name;
	}

	public String getLogin_name() {
		return login_name;
	}

	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}

	public long getNetworkId() {
		return network_id;
	}

	public void setNetworkId(long network_id) {
		this.network_id = network_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCellvoice1() {
		return cellvoice1;
	}

	public void setCellvoice1(String cellvoice1) {
		this.cellvoice1 = cellvoice1;
	}

	public String getCellvoice2() {
		return cellvoice2;
	}

	public void setCellvoice2(String cellvoice2) {
		this.cellvoice2 = cellvoice2;
	}

	public String getWorkvoice() {
		return workvoice;
	}

	public void setWorkvoice(String workvoice) {
		this.workvoice = workvoice;
	}

	public String getEmp_code() {
		return emp_code;
	}

	public void setEmp_code(String emp_code) {
		this.emp_code = emp_code;
	}

	public String getDept_code() {
		return dept_code;
	}

	public void setDept_code(String dept_code) {
		this.dept_code = dept_code;
	}

	public String getDisplay_order() {
		return display_order;
	}

	public void setDisplay_order(String display_order) {
		this.display_order = display_order;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHidden() {
		return hidden;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
	}

	public String getWith_account() {
		return with_account;
	}

	public void setWith_account(String with_account) {
		this.with_account = with_account;
	}

	public String getSuspended() {
		return suspended;
	}

	public void setSuspended(String suspended) {
		this.suspended = suspended;
	}

	public String getExt1() {
		return ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	public String getExt2() {
		return ext2;
	}

	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}

	public String getExt3() {
		return ext3;
	}

	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}

	public String getExt4() {
		return ext4;
	}

	public void setExt4(String ext4) {
		this.ext4 = ext4;
	}

	public String getExt5() {
		return ext5;
	}

	public void setExt5(String ext5) {
		this.ext5 = ext5;
	}

	public String getExt6() {
		return ext6;
	}

	public void setExt6(String ext6) {
		this.ext6 = ext6;
	}

	public String getExt7() {
		return ext7;
	}

	public void setExt7(String ext7) {
		this.ext7 = ext7;
	}

	public String getExt8() {
		return ext8;
	}

	public void setExt8(String ext8) {
		this.ext8 = ext8;
	}

	public String getExt9() {
		return ext9;
	}

	public void setExt9(String ext9) {
		this.ext9 = ext9;
	}

	public String getExt10() {
		return ext10;
	}

	public void setExt10(String ext10) {
		this.ext10 = ext10;
	}

	public HashMap<String, String> toHash() {
		HashMap<String, String> params = new HashMap<String, String>();

		params.put("id", String.valueOf(this.getId()));
		params.put("login_name", this.getLogin_name());
		params.put("password", this.getPassword());
		params.put("email", this.getEmail());
		params.put("name", this.getName());
		params.put("title", this.getTitle());

		params.put("cellvoice1", this.getCellvoice1());
		params.put("cellvoice2", this.getCellvoice2());
		params.put("workvoice", this.getWorkvoice());
		params.put("emp_code", this.getEmp_code());
		params.put("dept_code", this.getDept_code());
		params.put("display_order", this.getDisplay_order());

		params.put("hidden", this.getHidden());
		params.put("suspended", this.getSuspended());
		params.put("display_order", this.getDisplay_order());
		params.put("network_name", this.getNetwork_name());

		return params;
	}

	@Override
	public String toString() {
		return "User<id:" + this.id + ",name:" + this.name + ",login_name:"
				+ this.login_name + ">";
	}

}
