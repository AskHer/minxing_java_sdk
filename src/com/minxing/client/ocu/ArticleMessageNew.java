package com.minxing.client.ocu;


import java.util.List;


public class ArticleMessageNew {
    private String ocuId;
    private String type = "single";
    private boolean secret = false;
    private String ocuSecret;
    private String timestamp = String.valueOf(System.currentTimeMillis() + 1000 * 60);
    private String created_at = String.valueOf(System.currentTimeMillis() + 1000 * 60);
    private Boolean not_send = false;

    public Boolean getNot_send() {
        return not_send;
    }

    public void setNot_send(Boolean not_send) {
        this.not_send = not_send;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    private List<ArticleNew> articles;

    public String getOcuId() {
        return ocuId;
    }

    public ArticleMessageNew setOcuId(String ocuId) {
        this.ocuId = ocuId;
        return this;
    }

    public String getType() {
        return type;
    }

    public ArticleMessageNew setType(String type) {
        this.type = type;
        return this;
    }

    public boolean isSecret() {
        return secret;
    }

    public ArticleMessageNew setSecret(boolean secret) {
        this.secret = secret;
        return this;
    }

    public String getOcuSecret() {
        return ocuSecret;
    }

    public ArticleMessageNew setOcuSecret(String ocuSecret) {
        this.ocuSecret = ocuSecret;
        return this;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public ArticleMessageNew setTimestamp(String timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public List<ArticleNew> getArticles() {
        return articles;
    }

    public ArticleMessageNew setArticles(List<ArticleNew> article) {
        this.articles = article;
        return this;
    }
}
