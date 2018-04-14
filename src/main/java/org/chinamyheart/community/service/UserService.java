package org.chinamyheart.community.service;

import org.chinamyheart.community.model.User;
import org.springframework.stereotype.Service;

public interface UserService {
	User getUser(String token);
}
