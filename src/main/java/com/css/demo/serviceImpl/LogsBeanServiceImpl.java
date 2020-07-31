package com.css.demo.serviceImpl;

import com.css.demo.bean.LogsBean;
import com.css.demo.mapper.LogsBeanMapper;
import com.css.demo.service.LogsBeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LogsBeanServiceImpl implements LogsBeanService {

    @Autowired
    private LogsBeanMapper logsBeanMapper;

    @Override
    public int insert(LogsBean logsBean) {
        return logsBeanMapper.insert( logsBean.getUuid(),  logsBean.getUserUuid(),  logsBean.getCheckContentId(),
                logsBean.getCreateTime(),  logsBean.getCommentFlag(),  logsBean.getComment());
    }

    @Override
    public List<LogsBean> selectLogsByUserId(String userUuid) {
        return logsBeanMapper.selectLogsByUserId(userUuid);
    }

}
