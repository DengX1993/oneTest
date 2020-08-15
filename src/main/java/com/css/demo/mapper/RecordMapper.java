package com.css.demo.mapper;

import com.css.demo.bean.RecordBean;
import org.springframework.stereotype.Component;

@Component
public interface RecordMapper {
    /**
     *
     * @mbggenerated 2020-08-03
     */
    int deleteByPrimaryKey(String uuid);

    /**
     *
     * @mbggenerated 2020-08-03
     */
    int insert(RecordBean record);

    /**
     *
     * @mbggenerated 2020-08-03
     */
    int insertSelective(RecordBean record);

    /**
     *
     * @mbggenerated 2020-08-03
     */
    RecordBean selectByPrimaryKey(String uuid);

    /**
     *
     * @mbggenerated 2020-08-03
     */
    int updateByPrimaryKeySelective(RecordBean record);

    /**
     *
     * @mbggenerated 2020-08-03
     */
    int updateByPrimaryKey(RecordBean record);
}