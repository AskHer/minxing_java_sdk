

import com.minxing.connector.app.AppAccount;
import com.minxing.connector.ocu.Article;
import com.minxing.connector.ocu.ArticleMessage;

public class TestOcuAccount {
		public static void main(String[] args){
				
//				oa.sendMessageToUsersStr(new HtmlMessage("测试<a href='http://www.baidu.com'>百度</a>"), "dev001@dehui220.com.cn");
				ArticleMessage am = new ArticleMessage();
				Article pt = new Article("标题\r\n", "内容\r详细内容","","","");
				am.addArticle(pt);
				
				
				AppAccount account = AppAccount.loginByToken("http://localhost:3000",
						"cGTsJXPAJeEwUEXvt2SvJ--0q7cPunUqvc4AMKb8i6y-PUti");
				
				String ocuId = "oa";
				String ocuSecret = "xxxwww";
				
				account.sendOcuMessageToUsers(new String[] {"dev001@dehui220.com.cn"}, am, ocuId, ocuSecret);
				
		}
		
}
