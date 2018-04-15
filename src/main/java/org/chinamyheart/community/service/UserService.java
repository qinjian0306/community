package org.chinamyheart.community.service;

import org.chinamyheart.community.common.auth.LoginResponse;
import org.chinamyheart.community.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

	LoginResponse checkLogin(User user);

	List<User> getUserList(User user);

	List<User> getUserByUsername(User user);

}
