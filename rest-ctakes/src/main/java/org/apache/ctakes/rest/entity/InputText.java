package org.apache.ctakes.rest.entity;

public class InputText {
    private String analysisText;

    public String getAnalysisText() {
        return analysisText;
    }

    public void setAnalysisText(String analysisText) {
        this.analysisText = analysisText;
    }

    @Override
    public String toString() {
        return "InputText{" +
                "analysisText='" + analysisText + '\'' +
                '}';
    }
}
