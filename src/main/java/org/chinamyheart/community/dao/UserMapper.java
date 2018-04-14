package org.chinamyheart.community.dao;

import org.apache.ibatis.annotations.Mapper;
import org.chinamyheart.community.model.User;

import java.util.HashMap;

@Mapper
public interface UserMapper {

	int insertByMap(HashMap<String, String> paramMap);

	User selectByUsername(String username);
}
