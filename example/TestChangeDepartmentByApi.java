

import com.minxing.client.app.AppAccount;
import com.minxing.client.model.ApiErrorException;
import com.minxing.client.model.MxException;
import com.minxing.client.organization.Department;

public class TestChangeDepartmentByApi {
	public static void main(String[] args) {
		AppAccount account = AppAccount.loginByAccessToken("http://localhost:3000",
				"iPefUDrrardwZMWQXaZnBDBCLyY3iksJTmYtP2rcrJ0EYCJA");
		
		
		
		try {
			Department de = new Department(); 
			de.setDept_code("23000000"); // department root dept_code
			de.setParent_dept_code("23225202"); // new department parent 23225202
			account.updateDepartment(de);
			
		} catch (MxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ApiErrorException e) {
			e.printStackTrace();
		}
	}
	
}
