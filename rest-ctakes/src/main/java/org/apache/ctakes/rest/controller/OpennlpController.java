package org.apache.ctakes.rest.controller;

import org.apache.ctakes.rest.service.AnalyzeResultService;
import org.apache.ctakes.rest.service.OpennlpPosService;
import org.apache.ctakes.rest.util.JsonParser;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class OpennlpController {

    @Resource
    private OpennlpPosService posService;

    @Resource
    private AnalyzeResultService arService;

    @RequestMapping(value = "/pos", method = RequestMethod.POST)
    @ResponseBody
    public List<String> pos(@RequestBody String analysisText) {

        String inputText = JsonParser.parse(analysisText).getAnalysisText();

        List<String> result = posService.doTagging(inputText);
        arService.save("pos", inputText, result.toString());

        return result;
    }
}
