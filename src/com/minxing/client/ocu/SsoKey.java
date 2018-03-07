package com.minxing.client.ocu;

/**
 * @author SuZZ on 2018/3/7.
 */
public enum SsoKey {

    LOGIN_NAME("login_name"),EMIAL("email"),USER_ID("user_id");

    private String sso_key;

    SsoKey(String sso_key){
        this.sso_key = sso_key;
    }

    public String getSso_key() {
        return sso_key;
    }
}
