package com.minxing.client.ocu;

import lombok.Data;
import lombok.experimental.Accessors;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Data
@Accessors(chain = true)
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

}
