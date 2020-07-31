package com.css.demo.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.css.demo.bean.ContentDesignBean;
import com.css.demo.mapper.ContentDesignMapper;
import com.css.demo.service.ContentDesignService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class ContentDesignServiceImpl implements ContentDesignService {
    @Autowired
    private ContentDesignMapper mapper;

    @Override
    public ContentDesignBean selectObjByUUid(String uuid) {
        return mapper.selectObjByUUid(uuid);
    }

    @Override
    public List<ContentDesignBean> selectAllByScene(String scene) {
        List<ContentDesignBean> list = new ArrayList<>();
        try {
            List<Map<String, ContentDesignBean>> maps = mapper.selectAllByScene(scene);
            for (Map<String, ContentDesignBean> map : maps) {
                list.add(JSON.parseObject(JSON.toJSONString(map), ContentDesignBean.class));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<ContentDesignBean> selectAllInvitationByScene(String scene) {
        {
            List<ContentDesignBean> list = new ArrayList<>();
            try {
                List<Map<String, ContentDesignBean>> maps = mapper.selectAllInvitationByScene(scene);
                for (Map<String, ContentDesignBean> map : maps) {
                    list.add(JSON.parseObject(JSON.toJSONString(map), ContentDesignBean.class));
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return list;
        }
    }
}
