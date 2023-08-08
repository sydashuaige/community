package com.nowcoder.community.dao;

import org.springframework.stereotype.Repository;

@Repository("alphaDaoHibernateImpl")
//当其他bean设置优先级后，还想调用这个bean，
//可以通过名字调用默认为类名首字母小写
//访问数据库的bean需要添加该注解，底层是conpoment
public class AlphaDaoHibernateImpl implements AlphaDao{
    @Override
    public String select() {
        return "hello";
    }
}
