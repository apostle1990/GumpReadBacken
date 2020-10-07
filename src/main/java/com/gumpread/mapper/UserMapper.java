package com.gumpread.mapper;

import com.gumpread.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User getUserByUserName(String userName);

}
