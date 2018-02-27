package com.minxing.client.ocu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ArticleMessage implements Message {
	private List<Article> articles;
	private boolean secret;
	private boolean show_by_popup;// 如果为true时，将在终端上弹屏显示
	private String invalid_time;
    private Boolean not_send = false;

    public Boolean getNot_send() {
        return not_send;
    }

    public void setNot_send(Boolean not_send) {
        this.not_send = not_send;
    }

    public ArticleMessage() {
        this(false);
    }

	public ArticleMessage(boolean secret) {
		this(secret, false, null);
	}

	/**
	 * @param secret
	 * @param show_by_popup 如果为true时，将在终端上弹屏显示
	 */
	public ArticleMessage(boolean secret, boolean show_by_popup, Date date) {
		articles = new ArrayList<Article>();
		this.secret = secret;
		this.show_by_popup = show_by_popup;
		this.invalid_time = date == null ? "" : new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(date);
	}


    public List<Article> getArticles() {
        return articles;
    }

    public void addArticle(Article pt) {
        if (!articles.contains(pt)) {
            articles.add(pt);
        }
    }

    public void addArticles(List<Article> pts) {
        if (pts != null) {
            for (Article pt : pts) {
                addArticle(pt);
            }
        }
    }

    public Resource getMessageResource() {

        for (int i = 0, s = articles.size(); i < s; i++) {
            Article pt = articles.get(i);
            if ("resource".equals(pt.getType())) {
                Resource res = pt.getResource();
                return res;
            }

        }
        return null;
    }

    public String getBody() {
        if (articles.size() <= 0) {
            return "";
        }

		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"article_count\":").append(articles.size()).append(",");
		sb.append("\"secret\":").append(secret).append(",");
		if (show_by_popup) {
			sb.append("\"show_by_popup\":").append(show_by_popup).append(",");
			sb.append("\"invalid_time\":").append("\"").append(invalid_time).append("\",");
		}

        sb.append("\"articles\":[");
        Article pt = null;
        for (int i = 0, s = articles.size(); i < s; i++) {
            pt = articles.get(i);
            if (i != 0) {
                sb.append(",");
            }
            sb.append("{");
            if ("resource".equals(pt.getType())) {
                sb.append("\"type\":\"resource\"").append(",");
                sb.append("\"resource_id\":\"").append(pt.getResourceId())
                        .append("\"");
                if (pt.getTitle() != null && !pt.getTitle().trim().equals("")) {
                    sb.append(",");
                    sb.append("\"title\":\"").append(pt.getTitle())
                            .append("\"");
                }

                if (pt.getPicUrl() != null && !pt.getPicUrl().trim().equals("")) {
                    sb.append(",");
                    sb.append("\"pic_url\":\"").append(pt.getPicUrl())
                            .append("\"");
                }

                if (pt.getDescription() != null
                        && !pt.getDescription().trim().equals("")) {
                    sb.append(",");
                    sb.append("\"description\":\"").append(pt.getDescription())
                            .append("\"");
                }
                sb.append(",");
                sb.append("\"enable_readed_status\":").append(pt.getEnable_readed_status());
            } else {
                sb.append("\"title\":").append("\"").append(pt.getTitle())
                        .append("\"").append(",");
                sb.append("\"description\":").append("\"")
                        .append(pt.getDescription()).append("\"");
                if (pt.getPicUrl() != null) {
                    sb.append(",");
                    sb.append("\"pic_url\":").append("\"")
                            .append(pt.getPicUrl()).append("\"");
                }
                if (pt.getApp_url() != null) {
                    sb.append(",");
                    sb.append("\"app_url\":").append("\"")
                            .append(pt.getApp_url()).append("\"");
                }
                if (pt.getUrl() != null) {
                    sb.append(",");
                    sb.append("\"url\":").append("\"").append(pt.getUrl())
                            .append("\"");
                }
                if (pt.getAction_label() != null) {
                    sb.append(",");
                    sb.append("\"action_label\":").append("\"")
                            .append(pt.getAction_label()).append("\"");
                }
                sb.append(",");
                sb.append("\"enable_readed_status\":").append(pt.getEnable_readed_status());
            }

            sb.append("}");
        }
        sb.append("]");
        sb.append(",");
        sb.append("\"not_send\":").append(not_send);
        sb.append("}");
        return sb.toString();
    }

    @Override
    public int messageType() {
        // TODO Auto-generated method stub
        return RICH_TEXT_MESSAGE;
    }



//	public static void main(String[] args) {
//		ArticleMessage am = new ArticleMessage();
//		Article pt = new Article("标题\r\n", "内容\r详细内容", "", "", "");
//		am.addArticle(pt);
//		System.out.println("am:" + am.getBody());
//	}
}
