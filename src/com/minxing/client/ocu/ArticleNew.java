package com.minxing.client.ocu;


import java.util.List;


public class ArticleNew {
    private String title;
    private String body;
    private String image;
    private String description;
    private String author;
    private String expire_time;
    private List<ArticleNew.Attachment> attachments;
    private List<ArticleNew.Category> categories;
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

    public List<ArticleNew.Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<ArticleNew.Attachment> attachments) {
        this.attachments = attachments;
    }

    public List<ArticleNew.Category> getCategories() {
        return categories;
    }

    public void setCategories(List<ArticleNew.Category> categories) {
        this.categories = categories;
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

    public static class Attachment {
        private String origin_url;
        private String thumb_url;
        private Long size;
        private String name;
        private String type;
        private String original_name;

        public String getOrigin_url() {
            return origin_url;
        }

        public void setOrigin_url(String origin_url) {
            this.origin_url = origin_url;
        }

        public String getThumb_url() {
            return thumb_url;
        }

        public void setThumb_url(String thumb_url) {
            this.thumb_url = thumb_url;
        }

        public Long getSize() {
            return size;
        }

        public void setSize(Long size) {
            this.size = size;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getOriginal_name() {
            return original_name;
        }

        public void setOriginal_name(String original_name) {
            this.original_name = original_name;
        }
    }

    public static class Category {
        private Long id;
        private String name;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
