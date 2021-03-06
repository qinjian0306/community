package org.chinamyheart.community.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.chinamyheart.community.model.Reply;

import java.util.List;

@Mapper
public interface ReplyMapper {

	int insert(Reply reply);

	void update(Reply reply);

	Reply selectById(int id);

	List<Reply> selectByCaseId(int caseId);

	void deleteById(int id);

	void deleteByCaseId(int caseId);
}
