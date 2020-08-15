package com.css.demo.mapper;

import com.css.demo.bean.LogsBean;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface LogsBeanMapper {
    @Insert("insert into LOGS(UUID,USER_UUID,CHECK_CONTENT_ID,CREATE_TIME,COMMENT_FLAG,COMMENT) values(#{uuid},#{userUuid},#{checkContentId},#{createTime},#{commentFlag},#{comment})")
    int insert(@Param("uuid") String uuid
            , @Param("userUuid") String userUuid
            , @Param("checkContentId") String checkContentId
            , @Param("createTime") Date createTime
            , @Param("commentFlag") int commentFlag
            , @Param("comment") String comment);


    List<LogsBean> selectLogsByUserId(@Param("userUuid") String userUuid);

    List<LogsBean> selectLogsByUserIdAndCheckContentId(String userUuid, String checkContentId);

    List<LogsBean> selectLogsByUserIdAndTime(String userUuid,Date createTime);
}
