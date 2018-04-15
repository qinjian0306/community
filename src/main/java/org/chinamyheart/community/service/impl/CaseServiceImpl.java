package org.chinamyheart.community.service.impl;

import org.chinamyheart.community.mapper.CaseMapper;
import org.chinamyheart.community.model.Case;
import org.chinamyheart.community.service.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
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
    public void lockCase(Case c) {
        caseMapper.updateStatus(c);
    }

    @Override
    public ResponseEntity downloadCase(File file) {
        String downFileName = file.getName();
        try {
            downFileName = new String(file.getName().getBytes("GBK"), Charset.forName("iso-8859-1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", "attachment; filename=" + downFileName);
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new FileSystemResource(file));
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
