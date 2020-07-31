package com.css.demo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public interface CePingMapper {
    @Insert("insert into ceping(UUID,USER_NUMBER,Y1,O1,Y2,O2,Y3,O3,Y4,O4,Y5,O5,Y6,O6) values(#{uuid},#{userNumber},#{y1},#{o1},#{y2},#{o2},#{y3},#{o3},#{y4},#{o4},#{y5},#{o5},#{y6},#{o6})")
    int insert(@Param("uuid") String uuid, @Param("userNumber") String userNumber
            , @Param("y1") String y1, @Param("o1") String o1, @Param("y2") String y2, @Param("o2") String o2
            , @Param("y3") String y3, @Param("o3") String o3, @Param("y4") String y4, @Param("o4") String o4
            , @Param("y5") String y5, @Param("o5") String o5, @Param("y6") String y6, @Param("o6") String o6);
}
