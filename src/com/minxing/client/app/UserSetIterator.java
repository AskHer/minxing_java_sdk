package com.minxing.client.app;

import java.util.Iterator;

import com.minxing.client.organization.User;

public class UserSetIterator implements Iterator<UserSet> {
	
	int userSize = 100;
	private AppAccount account;
	private int currentPage = 0;
	private boolean withExt;

	UserSetIterator(AppAccount _account, int _userSize) {
		this.account = _account;
		this.userSize = _userSize;
		this.withExt = false;
	}
	UserSetIterator(AppAccount _account, int _userSize, boolean _withExt) {
		this.account = _account;
		this.userSize = _userSize;
		this.withExt = _withExt;
	}

	@Override
	public boolean hasNext() {
		if (currentPage == -1) {
			return false;	
		} else {
			return true;
		}
		
	}

	@Override
	public UserSet next() {
		currentPage = currentPage + 1;
		User[] users = account.getAllUsers(currentPage,userSize, this.withExt).toArray(new User[]{});
		UserSet us = new UserSet(users);
		
		if (users.length == 0) {
			currentPage = -1;
		}
		return us;
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

}
