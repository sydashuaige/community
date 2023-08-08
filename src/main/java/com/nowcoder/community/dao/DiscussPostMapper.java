package com.nowcoder.community.dao;

import com.nowcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);
    //@param 用于给参数取别名，
    //sql需要用到动态的条件，有且只有一个条件且在if中使用，则需要起别名
    int selectDiscussPostRows(@Param("userId") int userId);
}
