package cn.linxin.hotel.controller.worker;

import cn.linxin.hotel.entity.Worker;
import cn.linxin.hotel.response.AjaxResult;
import cn.linxin.hotel.response.ResponseTool;
import cn.linxin.hotel.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @Author: LinXin_
 * @CreateTime: 2021/6/28 11:33
 */
@RestController
@RequestMapping(value = "/op/worker")
public class OpWorkerController {
    @Autowired
    private WorkerService workerService;
    /**
     *  获取个人资料
     * @param request
     * @return
     */
    @RequestMapping(value = "/profile")
    public AjaxResult getProfile(HttpServletRequest request){
        String workerName = (String) request.getSession().getAttribute("workerName");
        Worker worker = workerService.selectByUsername(workerName);
        if(worker==null) return ResponseTool.failed("未知错误");
        worker.setPassword(null);
        return ResponseTool.success(worker);
    }
}
