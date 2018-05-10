package org.chinamyheart.community.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.chinamyheart.community.common.auth.LoginResponse;
import org.chinamyheart.community.model.Case;
import org.chinamyheart.community.model.User;
import org.chinamyheart.community.model.UserVo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

	User selectByUsername(String username);

	int insert(User User);

	void update(User User);

	User getUserById(Integer id);

	List<User> getUserList(User user);

	List<User> getUserByUsername(User user);

	List<User> selectAllPend(User user);


	int countPage(Map<String, Object> map);

	List<UserVo> getPageList(Map<String, Object> map);


}
