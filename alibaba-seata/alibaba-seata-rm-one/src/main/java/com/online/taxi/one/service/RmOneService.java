package com.online.taxi.one.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.online.taxi.common.dto.sms.SmsSendRequest;
import com.online.taxi.one.entity.One;
import com.online.taxi.one.mapper.OneMapper;

import io.seata.spring.annotation.GlobalTransactional;

/**
 * @author yueyi2019
 */
@Service
public class RmOneService {

    @Autowired
    OneMapper mapper;

    public String rm1() {

        //调用远程服务rm2和rm3
        rm2();
        rm3();

        //给本服务插入一条数据
        One o = new One();
        o.setId(1);
        o.setName("rm1");
        mapper.insertSelective(o);
        //异常测试，模拟rm1服务抛出一个异常时,rm2和rm3服务数据库数据是否都回滚
        // int i = 1/0;

        return "";
    }

    public String rm2Update() {
        //rm2 更新
        rm2UpdateRemote();

        //接着往rm1中在新增一条数据，第一次请求正常，第二次再请求，因为表里已经有了，会报错主键冲突，观察rm2的undo表是否有数据
        One rm1 = new One(2, "rm1");
        mapper.insertSelective(rm1);
        //异常测试，发生异常时，rm1发生异常时，所有的数据是否回滚
        // int i = 1/0;
        return "";
    }

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 调用seata 2 插入一条新数据
     */
    private void rm2() {
        restTemplate.getForEntity("http://seata-two/test/rm2", null);
    }

    /**
     * 调用seata 2 更新数据
     */
    private void rm2UpdateRemote() {
        restTemplate.getForEntity("http://seata-two/test/rm2-update", null);
    }

    /**
     * 调用seata 3
     */
    private void rm3() {
        restTemplate.getForEntity("http://seata-three/test/rm3", null);
    }
}
