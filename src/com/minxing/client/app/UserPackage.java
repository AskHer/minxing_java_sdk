package com.minxing.client.app;

import java.util.Iterator;
import java.util.List;

import com.minxing.client.model.User;

public class UserPackage implements Iterable<UserSet> {

	int userSize = 100;
	private AppAccount account;
	private boolean withExt;

	UserPackage(AppAccount _account, int _userSize) {
		this.account = _account;
		this.userSize = _userSize;
		this.withExt = false;
	}
	UserPackage(AppAccount _account, int _userSize, boolean _withExt) {
		this.account = _account;
		this.userSize = _userSize;
		this.withExt = _withExt;
	}

	@Override
	public Iterator<UserSet> iterator() {
		if(this.withExt){
			return new UserSetIterator(account, userSize, this.withExt);
		}else{
			return new UserSetIterator(account,userSize);
		}
	}
	
}
