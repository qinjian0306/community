package org.chinamyheart.community.service;

import org.chinamyheart.community.model.Reply;
import org.chinamyheart.community.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ReplayService {

	int insert(Reply reply);

	void update(Reply reply);

	Reply selectById(int id);

	List<Reply> selectByCaseId(int caseId);

	void deleteById(int id);

	void deleteByCaseId(int caseId);
}
