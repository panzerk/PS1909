package org.apache.ctakes.rest.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

@TableName("analyze_result")
public class AnalyzeResult implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId("id")
    private int id;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("analyze_type")
    private String analyzeType;

    @TableField("analyze_text")
    private String analyzeText;

    @TableField("analyze_result")
    private String analyzeResult;

    public String getAnalyzeType() {
        return analyzeType;
    }

    public void setAnalyzeType(String analyzeType) {
        this.analyzeType = analyzeType;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getAnalyzeText() {
        return analyzeText;
    }

    public void setAnalyzeText(String analyzeText) {
        this.analyzeText = analyzeText;
    }

    public String getAnalyzeResult() {
        return analyzeResult;
    }

    public void setAnalyzeResult(String analyzeResult) {
        this.analyzeResult = analyzeResult;
    }
}
