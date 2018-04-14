package org.chinamyheart.community.service;

import org.chinamyheart.community.model.Case;
import org.chinamyheart.community.model.Reply;

import java.util.List;

public interface CaseService {
    int addCase(Case c);

    void updateCase(Case c);

    void lockCase(int id, int lock);

    Case getCaseById(int id);

    List<Case> getAllCases();

    void deleteCase(int id);

    List<Case> getCasesByUserId(int userId);
}
