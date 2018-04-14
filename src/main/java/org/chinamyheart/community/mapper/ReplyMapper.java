package org.chinamyheart.community.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.chinamyheart.community.model.Reply;

import java.util.List;

@Mapper
public interface ReplyMapper {

	void deleteByCaseId(int caseId);

	List<Reply> selectByCaseId(int caseId);

	Reply selectById(int id);

	void deleteById(int id);
}
