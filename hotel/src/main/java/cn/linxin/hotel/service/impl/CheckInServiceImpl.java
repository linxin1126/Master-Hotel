package cn.linxin.hotel.service.impl;

import cn.linxin.hotel.entity.*;
import cn.linxin.hotel.enums.OrderStatus;
import cn.linxin.hotel.enums.RoomStatus;
import cn.linxin.hotel.mapper.CheckInMapper;
import cn.linxin.hotel.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CheckInServiceImpl implements CheckInService {
    @Autowired
    private CheckInMapper checkInMapper;
    @Autowired
    private OrderService orderService;
    @Autowired
    private RoomTypeService roomTypeService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private UserService userService;

    @Override
    public int insert(CheckIn checkIn) {
        return checkInMapper.insert(checkIn);
    }

    /**
     * 入住登记
     * @param checkIn
     * 1.获取订单
     * 2.获取房间类型
     * 3.获取房间
     * @return
     */
    @Override
    @Transactional
    public Room checkIn(CheckIn checkIn) {
        Order order = orderService.selectById(checkIn.getOrderId());
        /*RoomType rt = roomTypeService.selectById(order.getRoomTypeId());*/
        Room r = roomService.selectById(roomService.inRoom(order.getRoomTypeId()));
        checkIn.setRoomId(r.getRoomId());
        checkIn.setRoomNumber(r.getRoomNumber());

        User user = userService.selectByName(checkIn.getPersons());
        checkIn.setIds(user.getIdcard());
        /*roomTypeService.updateRest(rt.getTypeId(),-1);*/
        order.setOrderStatus(OrderStatus.CHECK_IN.getCode());
        orderService.update(order);
        checkInMapper.insert(checkIn);
        return r;
    }

    /**
     * 退房登记
     * 1.获取房间
     * 2.获取房型
     * 3.获取checkIn
     * @param roomNumber
     * @return
     */
    @Override
    public int checkOut(String  roomNumber){
        CheckIn checkIn = checkInMapper.selectLatestByRoomNumber(roomNumber);
        if(checkIn.getCheckOutTime() == null) {
            Room r = roomService.selectByNumber(roomNumber);
            RoomType ty = roomTypeService.selectById(r.getTypeId());
            r.setRoomStatus(RoomStatus.AVAILABLE.getCode());
            if(roomService.update(r) <=0 )return -3;
            if (roomTypeService.updateRest(ty.getTypeId(),1)<=0)return -2;
            return checkInMapper.checkOut(checkIn.getCheckInId());
        }else {
            return -1;
        }
    }

    @Override
    public int delete(int checkInId) {
        return checkInMapper.deleteByPrimaryKey(checkInId);
    }

    @Override
    public int update(CheckIn checkIn) {
        return checkInMapper.updateByPrimaryKeySelective(checkIn);
    }



    @Override
    public int updateByRoomNumber(String roomNumber) {
        return checkInMapper.updateByRoomNumber(roomNumber);
    }

    @Override
    public CheckIn selectById(int checkInId) {
        return checkInMapper.selectByPrimaryKey(checkInId);
    }

    @Override
    public List<CheckIn> selectAll() {
        return checkInMapper.selectAll();
    }

    @Override
    public CheckIn selectByRoomNumber(String roomNumber) {
        return checkInMapper.selectLatestByRoomNumber(roomNumber);
    }
}
