
import com.minxing.client.app.AppAccount;
import com.minxing.client.model.ShareLink;

public class SendShareLinkMessageToWorkCycle {

	public static void main(String[] args) {

		// 1.社区管理员登录系统，系统管理-接入端应用管理-创建应用。类型：其他，权限范围：模拟普通用户，ssokey:login_name,生成accessToken:是

		int fromuserid = 20;// 一个用户的id，接口会模拟该id发送消息.web上打开一个用户，从url里获取id
		int MY_SALE_GROUPE = 12;// 工作圈id，web上打开一个工作圈，从url里获取

		AppAccount account = AppAccount.loginByAccessToken("http://192.168.100.230:8030",
				"IXjcsEuQfi0dfbSBKfNpUWVS7XbILmzT-WE40CrKPhbJKpzF");

		String body = "XXX转发了一个连接";

		ShareLink sharelink = new ShareLink();
		sharelink.setTitle("【恩美路演】现场直播《地铁移动宽带》路演");
		sharelink
				.setURL("http:\\/\\/mp.weixin.qq.com\\/s?__biz=MzAxODY3ODM5Mg==&mid=2649730730&idx=2&sn=d1ae57fe41181717067759465b7d3d03&scene=1&srcid=0526Gl98UZuJ5WE1UdCyuqrv&from=groupmessage&isappinstalled=0#wechat_redirect");
//		sharelink
//				.setDescription("此文是著名VC Fred Wilson的文章，在此文中他提出了软件的商品化不可阻挡，所以投资此类公司必须注重“防御性”。他用一个牙科诊所软件的故事来说明如何做到这一点，其合作伙伴Albert后来又为这个故事修改了一个更好的结局");
//		sharelink
//				.setImageURL("http://mmbiz.qpic.cn/mmbiz/WRGz2LWLARDqOAnu1ib7Rum3PWc0KDtCqJHNhcrrXPEnROS4IHXXm73Ik9LtxIcAdm1l6uniaLjdkXkDo9Tdc4AA/0");
		// sharelink.setAppURL("your_native_app_url");
		// 如果需要客户端打开native页面，则填写该信息，例如：nativeLaunch://

		try {
account.setFromUserLoginName("t55");
			account.sendSharelinkToGroup(MY_SALE_GROUPE, null, sharelink);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}