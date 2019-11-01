package org.apache.ctakes.rest.controller;

import org.apache.ctakes.rest.entity.AnalyzeResult;
import org.apache.ctakes.rest.service.AnalyzeResultService;
import org.apache.ctakes.rest.service.CuiResponse;
import org.apache.ctakes.rest.util.JCasParser;
import org.apache.ctakes.rest.util.JsonParser;
import org.apache.ctakes.rest.util.Pipeline;
import org.apache.log4j.Logger;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.cas.impl.XmiCasSerializer;
import org.apache.uima.fit.factory.AggregateBuilder;
import org.apache.uima.jcas.JCas;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@RestController
public class CtakesNlpController {
    private static final Logger LOGGER = Logger.getLogger(CtakesNlpController.class);

    @Resource
    private AnalyzeResultService arService;

    private static AnalysisEngine pipeline;

    @PostConstruct
    public void init() {
        LOGGER.info("Initializing Pipeline...");
        AggregateBuilder aggregateBuilder;
        try {
            aggregateBuilder = Pipeline.getAggregateBuilder();
            pipeline = aggregateBuilder.createAggregate();
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    @RequestMapping(value = "/analyze", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, List<CuiResponse>> getAnalyzedJSON(@RequestBody String analysisText)
            throws ServletException {
        Map<String, List<CuiResponse>> resultMap = null;

        LOGGER.info(analysisText);
        String inputText = JsonParser.parse(analysisText).getAnalysisText();
        if (analysisText != null && analysisText.trim().length() > 0) {
            try {
                JCas jcas = pipeline.newJCas();
                jcas.setDocumentText(inputText);
                pipeline.process(jcas);
                resultMap = formatResults(jcas);
                jcas.reset();
            } catch (Exception e) {
                e.printStackTrace();
                throw new ServletException(e);
            }
        }
        arService.save("ctakes", inputText, resultMap.toString());
        return resultMap;
    }

    @PostMapping("/AnalyzeResult")
    public List<AnalyzeResult> getAnalyzeResult(){
        return arService.list();
    }

    private Map<String, List<CuiResponse>> formatResults(JCas jcas) throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        XmiCasSerializer.serialize(jcas.getCas(), output);
        String outputStr = output.toString();
        Files.write(Paths.get("Result.xml"), outputStr.getBytes());
        JCasParser parser = new JCasParser();
        return parser.parse(jcas);
    }

}
