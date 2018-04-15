package org.chinamyheart.community.service;

import org.chinamyheart.community.common.utils.Utils;
import org.chinamyheart.community.mapper.UserMapper;
import org.chinamyheart.community.model.Doctor;
import org.chinamyheart.community.common.auth.LoginResponse;
import org.chinamyheart.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface UserService {

	User selectByUsername(String username);

	String insertByUser(User User);

	LoginResponse checkLogin(User user);

	List<User> getUserList(User user);

	List<User> getUserByUsername(User user);

}
