import com.minxing.client.app.AppAccount;
import com.minxing.client.model.Task;

import java.util.Date;

public class TestTasksApi {

    public static void main(String args[]) throws Exception {
        AppAccount appAcount = AppAccount.loginByAccessToken("http://example.com", "example_access_token");
        // 待办的标题,将显示在待办列表中,必传
        String title = "标题";
        // 待办的备注,将显示在待办列表中,可不传
        String remark = "备注";
        // 用户ID,必传
        int userId = 18;
        // 分类编码,必传
        String categoryCode = "12121212";
        // 来源系统名称,必传,更新时可不传
        String source = "审批系统";
        // 来源详情地址,必传
        String url = "www.example.com";
        // 待办开始时间,必传
        Date startAt = new Date();
        // 待办结束时间,可不传
        Date endAt = new Date();
        // 提醒基准时间,不需要进行定时提醒时可不传
        Date remindTime = new Date();
        // 提醒时间偏移量,单位分钟,以基准时间进行增减,计算出实际提醒时间,不需要进行定时提醒时可不传
        Integer[] remindTimeOffset = {15, -15};
        // 公众号ID,不需要提醒时可不传,如需要提醒则必须传递
        String ocuId = "example_ocu_id";
        // 公众号Secret,不需要提醒时可不传,如需要提醒则必须传递
        String ocuSecret = "example_ocu_secret";
        // 是否需要即时提醒,必传,更新时传递true也会提醒
        boolean instantRemind = false;
        Task task = new Task(title, remark, userId, categoryCode, source, url, startAt, endAt, remindTime, remindTimeOffset, ocuId, ocuSecret, instantRemind);
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
