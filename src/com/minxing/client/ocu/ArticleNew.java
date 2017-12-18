package com.minxing.client.ocu;



import java.util.List;


public class ArticleNew {
    private String title;
    private String body;
    private String image;
    private String description;
    private String author;
    private String expire_time;
    private List<String> attachments;
    private List<String> categories;
    private String link;
    private boolean hasLink = false;
    private boolean show_home_picture = false;
    private boolean isAllowComment = false;
    private boolean isAllowOutsiders = false;
    private boolean isChooseCategory = false;
    private boolean show_by_popup = false;

    public String getTitle() {
        return title;
    }

    public ArticleNew setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getBody() {
        return body;
    }

    public ArticleNew setBody(String body) {
        this.body = body;
        return this;
    }

    public String getImage() {
        return image;
    }

    public ArticleNew setImage(String image) {
        this.image = image;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ArticleNew setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public ArticleNew setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getExpire_time() {
        return expire_time;
    }

    public ArticleNew setExpire_time(String expire_time) {
        this.expire_time = expire_time;
        return this;
    }

    public List<String> getAttachments() {
        return attachments;
    }

    public ArticleNew setAttachments(List<String> attachments) {
        this.attachments = attachments;
        return this;
    }

    public List<String> getCategories() {
        return categories;
    }

    public ArticleNew setCategories(List<String> categories) {
        this.categories = categories;
        return this;
    }

    public String getLink() {
        return link;
    }

    public ArticleNew setLink(String link) {
        this.link = link;
        return this;
    }

    public boolean isHasLink() {
        return hasLink;
    }

    public ArticleNew setHasLink(boolean hasLink) {
        this.hasLink = hasLink;
        return this;
    }

    public boolean isShow_home_picture() {
        return show_home_picture;
    }

    public ArticleNew setShow_home_picture(boolean show_home_picture) {
        this.show_home_picture = show_home_picture;
        return this;
    }

    public boolean isAllowComment() {
        return isAllowComment;
    }

    public ArticleNew setAllowComment(boolean allowComment) {
        isAllowComment = allowComment;
        return this;
    }

    public boolean isAllowOutsiders() {
        return isAllowOutsiders;
    }

    public ArticleNew setAllowOutsiders(boolean allowOutsiders) {
        isAllowOutsiders = allowOutsiders;
        return this;
    }

    public boolean isChooseCategory() {
        return isChooseCategory;
    }

    public ArticleNew setChooseCategory(boolean chooseCategory) {
        isChooseCategory = chooseCategory;
        return this;
    }

    public boolean isShow_by_popup() {
        return show_by_popup;
    }

    public ArticleNew setShow_by_popup(boolean show_by_popup) {
        this.show_by_popup = show_by_popup;
        return this;
    }
}
