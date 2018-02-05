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
    private Boolean display_top = false;
    private Integer display_order = 0;

    public Boolean getNot_send() {
        return not_send;
    }

    public ArticleMessageNew setNot_send(Boolean not_send) {
        this.not_send = not_send;
        return this;
    }

    public String getCreated_at() {
        return created_at;
    }

    public ArticleMessageNew setCreated_at(String created_at) {
        this.created_at = created_at;
        return this;
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

    public Boolean getDisplay_top() {
        return display_top;
    }

    public ArticleMessageNew setDisplay_top(Boolean display_top) {
        this.display_top = display_top;
        return this;
    }

    public Integer getDisplay_order() {
        return display_order;
    }

    public ArticleMessageNew setDisplay_order(Integer display_order) {
        this.display_order = display_order;
        return this;
    }
}
