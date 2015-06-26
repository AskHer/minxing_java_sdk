

import com.minxing.connector.app.AppAccount;
import com.minxing.connector.model.Error;
import com.minxing.connector.organization.Department;
import com.minxing.connector.organization.Network;
import com.minxing.connector.organization.User;

public class TestOrgSync {
	static String network_name = "t.cn";
	public static void main(String[] args){
		AppAccount account = AppAccount.loginByToken("http://localhost:3000",
				"cGTsJXPAJeEwUEXvt2SvJ--0q7cPunUqvc4AMKb8i6y-PUti");
		
		//addNetwork(account);
		//addDepart(account);
		//addUser(account);
		updateUser(account);
	}
	
	private static void addNetwork(AppAccount account){
		
		Network n = new Network();
		n.setDisplay_name("电访测试");
		n.setName(network_name);
		Error e = account.createNetwork(n);
		System.out.println(e.getErrorCode() + e.getErrorMessage());
	}
	private static void addDepart(AppAccount account){
		
		Department d = new Department();
		d.setDept_code("001001");
		d.setShort_name("办公室");
		d.setNetwork_name(network_name);
		Error e = account.addNewDepartment(d);
		System.out.println(e.getErrorCode() + e.getErrorMessage());
	}
	private static void addUser(AppAccount account){
		
		User u = new User();
		u.setNetwork_name(network_name);
		u.setLogin_name("java");
		u.setName("(（test）)");
		u.setEmail("java@t.cn");
		u.setPassword("123456");
		u.setTitle("经理");
		u.setCellvoice1("13145678912");
		u.setDept_code("10");
		Error e = account.addNewUser(u);
		System.out.println(e.getErrorCode() + e.getErrorMessage());
	}
	private static void updateUser(AppAccount account){
		
		User u = new User();
		u.setNetwork_name(network_name);
		u.setLogin_name("ruby");
		u.setName("(（test）)");
		u.setEmail("ruby@t.cn");
		u.setPassword("123456");
		u.setTitle("经理");
		u.setCellvoice1("13145678912");
		u.setDept_code("10");
		Error e = account.updateUser(u);
		System.out.println(e.getErrorCode() + e.getErrorMessage());
	}
	
}
