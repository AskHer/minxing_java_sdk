package com.minxing.client.ocu;


import java.util.List;


public class ArticleMessageNew {
    private String ocuId;
    private String type = "single";
    private boolean secret = false;
    private String ocuSecret;
    private String timestamp = String.valueOf(System.currentTimeMillis() + 1000 * 60);
    private String created_at = String.valueOf(System.currentTimeMillis() + 1000 * 60);
    private Integer send_type = 0;  //0-存素材并发；1-存素材不发送 ；2-不存库只发送
    private Boolean display_top = false;
    private Integer display_order = 0;

    public Integer getSend_type() {
        return send_type;
    }

    public ArticleMessageNew setSend_type(Integer send_type) {
        this.send_type = send_type;
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
