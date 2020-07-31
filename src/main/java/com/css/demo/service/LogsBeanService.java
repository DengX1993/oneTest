package com.css.demo.service;

import com.css.demo.bean.LogsBean;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface LogsBeanService {
    int insert (LogsBean logsBean);
    List<LogsBean> selectLogsByUserId( String userUuid);

}
