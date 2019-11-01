package org.apache.ctakes.rest.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ctakes.rest.dao.AnalyzeResultMapper;
import org.apache.ctakes.rest.entity.AnalyzeResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AnalyzeResultService {
    @Resource
    private AnalyzeResultMapper mapper;

    public void save(String analyzeType, String analyzeText,String analyzeResult){
        AnalyzeResult ar = new AnalyzeResult();
        ar.setAnalyzeText(analyzeText);
        ar.setAnalyzeResult(analyzeResult);
        ar.setCreateTime(LocalDateTime.now());
        ar.setAnalyzeType(analyzeType);
        mapper.insert(ar);
    }

    public List<AnalyzeResult> list(){
        QueryWrapper<AnalyzeResult> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("create_time");
        IPage<AnalyzeResult> page = new Page<>(1L,15);
        mapper.selectPage(page,queryWrapper);
        return page.getRecords();
    }
}
