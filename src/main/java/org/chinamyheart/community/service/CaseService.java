package org.chinamyheart.community.service;

import org.chinamyheart.community.common.PageUtils.Pagination;
import org.chinamyheart.community.model.Case;
import org.chinamyheart.community.model.Reply;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.util.List;

public interface CaseService {
    int addCase(Case c);

    void updateCase(Case c);

    void lockCase(Case c);

    ResponseEntity downloadCase(File file);

    Case getCaseById(int id);

    List<Case> getAllCases();

    void deleteCase(int id);

    Pagination<Case> getCasesByUserId(int userId, Pagination<Case> pagination);

    Pagination<Case> getAllCases(Pagination<Case> pagination);
}
