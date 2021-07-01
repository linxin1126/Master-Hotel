package cn.linxin.hotel.mapper;

import cn.linxin.hotel.entity.Hotel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface HotelMapper {
    int deleteByPrimaryKey(Integer hotelId);

    int insert(Hotel record);

    int insertSelective(Hotel record);

    Hotel selectByPrimaryKey(Integer hotelId);

    Hotel selectByName(String hotelName);

    List<Hotel> selectAll();

    int updateByPrimaryKeySelective(Hotel record);

    int updateByPrimaryKey(Hotel record);


}