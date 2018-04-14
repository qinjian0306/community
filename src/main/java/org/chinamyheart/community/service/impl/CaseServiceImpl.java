package org.chinamyheart.community.service.impl;

import org.chinamyheart.community.mapper.CaseMapper;
import org.chinamyheart.community.model.Case;
import org.chinamyheart.community.service.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaseServiceImpl implements CaseService {

    @Autowired
    private CaseMapper caseMapper;

    @Override
    public int addCase(Case c) {
        caseMapper.insert(c);
        return 0;
    }

    @Override
    public void updateCase(Case c) {
        caseMapper.update(c);
    }

    @Override
    public void lockCase(int id,int status) {
        caseMapper.updateStatus(id,status);
    }

    @Override
    public Case getCaseById(int id) {
        return caseMapper.selectById(id);
    }

    @Override
    public List<Case> getAllCases() {
        return caseMapper.selectAll();
    }

    @Override
    public void deleteCase(int id) {
        caseMapper.deleteById(id);
    }

    @Override
    public List<Case> getCasesByUserId(int userId) {
        return caseMapper.selectByUserId(userId);
    }
}
