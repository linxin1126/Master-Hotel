package cn.linxin.hotel.service.impl;

import cn.linxin.hotel.entity.OrderType;
import cn.linxin.hotel.mapper.OrderTypeMapper;
import cn.linxin.hotel.service.OrderTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderTypeServiceImpl implements OrderTypeService {
    @Autowired
    private OrderTypeMapper orderTypeMapper;

    @Override
    public int insertOrderType(OrderType orderType) {
        return orderTypeMapper.insertSelective(orderType);
    }

    @Override
    public int deleteOrderType(int typeId) {
        return orderTypeMapper.deleteByPrimaryKey(typeId);
    }

    @Override
    public int updateOrderType(OrderType orderType) {
        return orderTypeMapper.updateByPrimaryKeySelective(orderType);
    }

    @Override
    public OrderType selectById(int typeId) {
        return orderTypeMapper.selectByPrimaryKey(typeId);
    }

    @Override
    public List<OrderType> selectAll() {
        return orderTypeMapper.selectAll();
    }
}
