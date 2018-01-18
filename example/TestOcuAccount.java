
import com.alibaba.fastjson.JSONObject;
import com.minxing.client.app.AppAccount;
import com.minxing.client.ocu.ArticleMessageNew;
import com.minxing.client.ocu.ArticleNew;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestOcuAccount {
    public static void main(String[] args) {
//		testSendOcuMessageToUsers();
        while (true) {
            testSendOcuMessage();
            try {
                Thread.sleep(1000 * 20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

//		clientTest();
    }

/*    public static void testSendOcuMessageToUsers() {
        for (int i = 0; i < 10; i++) {
            // oa.sendMessageToUsersStr(new
            // HtmlMessage("测试<a href='http://www.baidu.com'>百度</a>"),
            // "dev001@dehui220.com.cn");
            String title = "嘟嘟【待办】OA事务申请" + i;// 提醒标题
            String content = "嘟嘟运营FATCA\\CRS需求上线申请（RL04994）贾晓梅【jiaxm06】温馨提示：不支持PC端点击!";// 提醒内容
            ArticleMessage am = new ArticleMessage();
            Article article = new Article(title, content, "", "http://oawxn.taikang.com/moa//m/s?s=EXKVes0tP93BoyetMuqX8mFzl+FLNFjZKd7WlNrBtokpqSMdB3RI9w==&quot", null);
            am.addArticle(article);
            AppAccount account = AppAccount.loginByAccessToken(
                    "http://dev5.dehuinet.com:8015",
                    "oCnV5eM3zfVwdqACyiQCa-P8_Kq_ZeoFBEA2vwWohRvZHEuP");

//			String ocuId = "40dbd78cc10e32d7a36f2a518460f7f7";
//			String ocuSecret = "88cb9f89de14332abc787486a4249b30";
            String ocuId = "app_notification";
            String ocuSecret = "4fa6a29b49a273240a0947c4a20178ed";

            OcuMessageSendResult send_to = account.sendOcuMessageToUsers(
                    new String[]{"69"}, am, ocuId, ocuSecret);
            System.out.println("发送至:" + send_to.getCount() + "人");
            System.out.println("发送消息Id:" + send_to.getMessageId());
            System.out.println("发送用户Id列表:" + send_to.getUserIds());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }*/

    /**
     * 发公众号消息测试
     */
    public static void testSendOcuMessage() {


        //创建接入端对象，参数1：敏行地址，参数2：接入端token，在敏行后台中获取这个token，然后加到配置文件或写到代码里
        AppAccount account = AppAccount.loginByAccessToken(
                "http://192.168.100.185",
                "raWOK3A2PdRCNE-9Gn4ivbM3EcXDrjQ7RxmjEAIR3RwuBX7_");
        //社区ID
        int network_id = 2;
        //ocuId和ocuSecret这俩参数在公众号平台的管理页面里找
        String ocuId = "domain_2";
        String ocuSecret = "4791f3da7d10861d1ec05e1f99870ded";
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
        category1.setId(468l);
        category2.setId(467l);
        category1.setName("23232");
        category2.setName("分享测试");

        List<ArticleNew.Attachment> attList = new ArrayList<>();
        attList.add(attachment);
        List<ArticleNew.Category> catList = new ArrayList<>();
        catList.add(category1);
        catList.add(category2);
        ArticleNew article = new ArticleNew()
//				文章标题
                .setTitle("备降")
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

        articleMessage.setCreated_at("1546272000000");

        System.out.println(JSONObject.toJSONString(articleMessage));

        account.sendOcuMessage(articleMessage, network_id);
    }

    public static void clientTest() {
		/*System.setProperty("http.proxySet", "true");
		System.setProperty("http.proxyHost", "192.168.40.244");
		System.setProperty("http.proxyPort", "8888");*/

        String url = "https://www.baidu.com";
        String params = "";
        HttpClient client = new HttpClient();
        PostMethod postMethod = new PostMethod(url);
        RequestEntity entity = new InputStreamRequestEntity(new ByteArrayInputStream(params.getBytes()), "application/json");
        postMethod.setRequestEntity(entity);
        try {
            client.executeMethod(postMethod);
            String res = postMethod.getResponseBodyAsString();
            System.out.println(res);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
