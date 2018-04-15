package org.chinamyheart.community.service;

import org.chinamyheart.community.common.utils.Utils;
import org.chinamyheart.community.mapper.UserMapper;
import org.chinamyheart.community.model.Doctor;
import org.chinamyheart.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

	User selectByUsername(String username);

	String insertByUser(User User);

	User getUser(String token);
}
