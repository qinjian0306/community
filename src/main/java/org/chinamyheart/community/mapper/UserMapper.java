package org.chinamyheart.community.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.chinamyheart.community.common.auth.LoginResponse;
import org.chinamyheart.community.model.User;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface UserMapper {

	User selectByUsername(String username);

	int insert(User User);

	List<User> getUserList(User user);

	List<User> getUserByUsername(User user);
}
