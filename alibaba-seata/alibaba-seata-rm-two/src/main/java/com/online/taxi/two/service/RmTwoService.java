package com.online.taxi.two.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.taxi.two.entity.Two;
import com.online.taxi.two.mapper.TwoMapper;

/**
 * @author yueyi2019
 */
@Service
public class RmTwoService {
	
	@Autowired
	TwoMapper mapper;
	
	public String rm2() {
		Two o = new Two();
		o.setId(2);
		o.setName("rm2");
		mapper.insertSelective(o);

        //异常测试，发生异常时，rm2发生异常时，所有的数据是否回滚
        //int i = 1/0;
		
		return "";
	}

    public String rm2Update(){
        List<Two> lists = mapper.selectAll();
        Two two = lists.get(0);
        two.setName(two.getName() + new Random().nextInt(100));
        mapper.updateByPrimaryKeySelective(two);
        //异常测试，发生异常时，rm2发生异常时，所有的数据是否回滚
        //int i = 1/0;
        return "";
    }
}
