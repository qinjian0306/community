package org.chinamyheart.community.service.impl;

import org.chinamyheart.community.mapper.ReplyMapper;
import org.chinamyheart.community.model.Reply;
import org.chinamyheart.community.service.ReplayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReplayServiceImpl implements ReplayService{

	/**
	 * 日志
	 */
	private static final Logger logger = LoggerFactory.getLogger(ReplayServiceImpl.class);

	@Autowired
	private ReplyMapper replyMapper;

	@Override
	public int insert(Reply reply) {
		reply.setReplyTime(new Date());
		try {
			int result = replyMapper.insert(reply);
			if(result > 0){
				return 1;
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void update(Reply reply) {
		reply.setReplyTime(new Date());
		try {
			replyMapper.update(reply);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public Reply selectById(int id) {
		Reply reply = replyMapper.selectById(id);
		return reply;
	}

	@Override
	public List<Reply> selectByCaseId(int caseId) {
		List<Reply> replyList = replyMapper.selectByCaseId(caseId);
		return replyList;
	}

	@Override
	public void deleteById(int id) {
		replyMapper.deleteById(id);

	}

	@Override
	public void deleteByCaseId(int caseId) {
		replyMapper.deleteById(caseId);
	}
}
