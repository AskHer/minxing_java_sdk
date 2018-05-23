import com.minxing.client.app.AppAccount;
import com.minxing.client.model.Task;

import java.util.Date;

public class TestTasksApi {

    public static void main(String args[]) throws Exception {
        AppAccount appAcount = AppAccount.loginByAccessToken("http://example.com", "example_access_token");
        // 新建一个待办信息,参数依次为 标题,备注,用户ID,类别编码,系统来源名称,待办详情地址,提醒基准时间,提醒时间偏移量,公众号ID,公众号secret,是否需要立即提醒
        Task task = new Task("标题3", "备注6", 18, "12121212", "外部系统", "www.example.com", new Date(), new Integer[]{15}, "13131", "242424", false);
        // 调用接口新增待办事项,返回值为待办事项的ID
        int task_id = appAcount.createTask(task);
        // 更新待办事项,task对象必须有id值
        task.setId(task_id);
        task.setTitle("标题4");
        appAcount.updateTask(task);
        // 更改待办事项状态,参数依次为待办事项ID,是否已完成
        appAcount.changeTaskStatus(task_id, true);
        // 删除待办事项
        appAcount.deleteTask(task_id);
    }

}
