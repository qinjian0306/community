package org.chinamyheart.community.dao;

import org.apache.ibatis.annotations.Mapper;
import org.chinamyheart.community.model.Case;

import java.util.List;
import java.util.Map;

@Mapper
public interface CaseMapper {

	List<Case> selectByUserId(int userId);

	List<Case> selectAll();

	void insertByMap(Map<String,String> paramMap);

	Case selectById(int id);

	void deleteById(int id);

	void lock(int id);
}
