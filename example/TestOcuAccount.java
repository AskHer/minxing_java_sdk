
import com.minxing.client.app.AppAccount;
import com.minxing.client.ocu.ArticleMessageNew;
import com.minxing.client.ocu.ArticleNew;

import java.util.ArrayList;
import java.util.List;

public class TestOcuAccount {
    public static void main(String[] args) {

            testSendOcuMessage(true);

    }



    /**
     * 发公众号消息测试
     */
    public static void testSendOcuMessage(boolean not_send) {


        //创建接入端对象，参数1：敏行地址，参数2：接入端token，在敏行后台中获取这个token，然后加到配置文件或写到代码里
        AppAccount account = AppAccount.loginByAccessToken(
                "http://dev5.dehuinet.com:8015",   //敏行地址
                "HUu6MXtooVHV_wXQ3ieaeRDT6VuQ0xj0BPY6gkP15G-SFDCS");  //接入端access token
        //社区ID
        int network_id = 3;
        //ocuId和ocuSecret这俩参数在公众号平台的管理页面里找
        String ocuId = "domain_17";
        //公众号Secret
        String ocuSecret = "f8aac0ae2cb7e0cb0db779407f5d81a1";
        //创建附件对象
        ArticleNew.Attachment attachment = new ArticleNew.Attachment();
        attachment.setName("6a702689-9b60-4e2e-b4e9-ed89ccf1fb4c (1) 2_1513765766550.zip");
        //附件下载地址
        attachment.setOrigin_url("http://www.kfpolice.com/WEB/Files/Bgxz/%e9%99%84%e4%bb%b61%e3%80%8a%e6%9c%ba%e5%8a%a8%e8%bd%a6%e9%a9%be%e9%a9%b6%e8%af%81%e7%94%b3%e8%af%b7%e8%a1%a8%e3%80%8b%e5%bc%8f%e6%a0%b7_20160328105816.xls");
        //附件名称
        attachment.setOriginal_name("附件1《机动车驾驶证申请表》式样_20160328105816.xls");
        //附件大小，单位：字节
        attachment.setSize((long) (59.5 * 1024l));
        //附件类型
        attachment.setType("application/vnd.ms-excel");

        //创建分类信息
        ArticleNew.Category category1 = new ArticleNew.Category();
        ArticleNew.Category category2 = new ArticleNew.Category();
        category1.setId(473l);
//        category2.setId(467l);
        category1.setName("测试1111");
//        category2.setName("分享测试");

        List<ArticleNew.Attachment> attList = new ArrayList<>();
        attList.add(attachment);
        List<ArticleNew.Category> catList = new ArrayList<>();
        catList.add(category1);
        catList.add(category2);
        ArticleNew article = new ArticleNew()
//				文章标题
                .setTitle("备降" + System.currentTimeMillis())
//				封面的图片地址
                .setImage("http://up.henan.china.cn/2017/0517/1494992198452.jpg")
//				文章简介
                .setDescription("11月3日，从纽约飞往广州的南航CZ600航班上，一名女性旅客空中突发病情。")
//				文章作者
                .setAuthor("小程序")
//				内容，是一段html
                .setBody("<html>这是body<html>");
        article.setAttachments(attList);
        article.setCategories(catList);
        ArrayList<ArticleNew> articles = new ArrayList<ArticleNew>();
        articles.add(article);
//		可以添加多个文章
        ArticleMessageNew articleMessage = new ArticleMessageNew()
                .setOcuId(ocuId)
                .setOcuSecret(ocuSecret)
                .setArticles(articles);

        //发布时间
        articleMessage.setCreated_at("1516860000000");
       //是否发送
        articleMessage.setNot_send(not_send);

        //发送消息
        account.sendOcuMessage(articleMessage, network_id);
    }


}
