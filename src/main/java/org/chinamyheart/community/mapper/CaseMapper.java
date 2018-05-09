package org.chinamyheart.community.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.chinamyheart.community.model.Case;

import java.util.List;
import java.util.Map;

@Mapper
public interface CaseMapper {

    List<Case> selectByUserId(int userId);

    List<Case> selectAll();

    void insertByMap(Map<String, String> paramMap);

    void insert(Case c);

    void update(Case c);

    void updateStatus(Case c);

    Case selectById(int id);

    void deleteById(int id);

    int countPage(Map<String, Object> map);

    List<Case> getPageList(Map<String, Object> map);

    void lock(int id);
}
