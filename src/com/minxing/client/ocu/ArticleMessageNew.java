package com.minxing.client.ocu;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class ArticleMessageNew {
    private int ocuId;
    private String type = "single";
    private boolean secret = false;
    private String ocuSecret;
    private String timestamp = String.valueOf(System.currentTimeMillis() + 1000 * 60);
    private List<ArticleNew> articles;
}
