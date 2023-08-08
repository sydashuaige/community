package com.nowcoder.community.service;

import com.nowcoder.community.dao.AlphaDao;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.aop.aspectj.SingletonAspectInstanceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;

@Scope("singleton")
//singleton 默认实例一次 prototype 默认可以实例化多次
@Service
public class AlphaService {

    //通过注解实现依赖注入，调用数据库
    @Autowired
    private AlphaDao alphaDao;

    public AlphaService(){
        System.out.println("实例化AlphaService");
    }
    @PostConstruct
    //该方法会在构造器之后调用
    public void  init(){
        System.out.println("初始化AlphaService");
    }

    @PreDestroy
    //该方法会在销毁之前调用
    public void destory(){
        System.out.println("销毁AlphaService");
    }

    public String find(){
        return alphaDao.select();
    }
}
