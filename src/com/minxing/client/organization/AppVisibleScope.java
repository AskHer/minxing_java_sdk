package com.minxing.client.organization;

import java.util.List;

public class AppVisibleScope {
	private List<User> users;
	private List<Department> department;

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Department> getDepartment() {
		return department;
	}

	public void setDepartment(List<Department> department) {
		this.department = department;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(users!=null){
			sb.append(users.toString());
		}
		if(department!=null){
			department.toString();
			sb.append(department.toString());
		}
		return sb.toString();
	}
	
	
}
