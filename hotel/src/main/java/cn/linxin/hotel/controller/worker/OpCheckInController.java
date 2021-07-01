package cn.linxin.hotel.controller.worker;

import cn.linxin.hotel.entity.CheckIn;
import cn.linxin.hotel.response.AjaxResult;
import cn.linxin.hotel.response.ResponseTool;
import cn.linxin.hotel.service.CheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/op/checkin")
public class OpCheckInController {

    @Autowired
    private CheckInService checkInService;

    @RequestMapping(value = "/in")
    public AjaxResult addCheckIn(int orderId, /*int peoCount,*/ String persons/*, String ids*/){
        CheckIn checkIn = new CheckIn();
        checkIn.setOrderId(orderId);
        /*checkIn.setPeoCount(peoCount);*/
        checkIn.setPersons(persons);
        /*checkIn.setIds(ids);*/
        return ResponseTool.success(checkInService.checkIn(checkIn));
    }

    @RequestMapping(value = "/out")
    public AjaxResult checkOut(String roomNumber) {
        int res = checkInService.checkOut(roomNumber);
        if(res < -1)
            return ResponseTool.failed("退房失败");
        if(res == -1)
            return ResponseTool.failed("该订单已退房,不允许重复退房");
        return ResponseTool.success("退房成功");
    }


    @RequestMapping(value = "/delete")
    public AjaxResult deleteCheckIn(int checkId){
        if(checkInService.delete(checkId)!=1)
            return ResponseTool.failed("删除失败");
        return ResponseTool.success("删除成功");
    }

    @RequestMapping(value = "/update")
    public AjaxResult update(int checkId,String roomNumber){
        CheckIn checkIn = new CheckIn();
        checkIn.setCheckInId(checkId);
        checkIn.setRoomNumber(roomNumber);
        if(checkInService.update(checkIn)!=1)
            return ResponseTool.failed("更新失败");
        return ResponseTool.success("更新成功");
    }



    @RequestMapping(value = "/{checkId}")
    public AjaxResult getById(@PathVariable int checkId){
        return ResponseTool.success(checkInService.selectById(checkId));
    }

    @RequestMapping(value = "")
    public AjaxResult getAll(){
        return ResponseTool.success(checkInService.selectAll());
    }

    @RequestMapping("/find{roomNumber}")
    public AjaxResult findCheckInByRoomNumber(@PathVariable String roomNumber){
        CheckIn checkIn = checkInService.selectByRoomNumber(roomNumber);
        if(checkIn.getCheckOutTime() == null)
            return ResponseTool.success(checkIn);
        return ResponseTool.failed("找不到相关入住信息，请检查！");
    }

}
