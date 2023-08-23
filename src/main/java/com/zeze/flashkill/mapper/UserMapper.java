package com.zeze.flashkill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zeze.flashkill.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author zhoubin
 * @since 2023-08-22
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
