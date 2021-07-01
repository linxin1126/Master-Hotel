package cn.linxin.hotel.controller.common;

import cn.linxin.hotel.response.AjaxResult;
import cn.linxin.hotel.response.ResponseTool;
import cn.linxin.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/hotel")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @RequestMapping(value = "")
    public AjaxResult getAllHotel(){
        return ResponseTool.success(hotelService.selectAll());
    }
}
