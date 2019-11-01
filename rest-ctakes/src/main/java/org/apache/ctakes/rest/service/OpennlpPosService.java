package org.apache.ctakes.rest.service;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class OpennlpPosService {

    private static final Logger LOGGER = Logger.getLogger(OpennlpPosService.class);

    private static final String MODEL_PATH = "en-pos-maxent.bin";

    private static POSModel model;
    private static POSTaggerME tagger;

    static {
        try {
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(MODEL_PATH);
            model = new POSModel(is);
            tagger = new POSTaggerME(model);
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }

    public static List<String> doTagging(POSModel model, String input) {
        POSTaggerME tagger = new POSTaggerME(model);

        return Stream.of(input).map(line -> {
            String[] whitespaceTokenizerLine = WhitespaceTokenizer.INSTANCE.tokenize(line.toString());
            String[] tags = tagger.tag(whitespaceTokenizerLine);

            POSSample sample = new POSSample(whitespaceTokenizerLine, tags);

            return sample.toString();
        }).collect(Collectors.toList());
    }


    public List<String> doTagging(String input) {
        return Stream.of(input).map(line -> {
            String[] whitespaceTokenizerLine = WhitespaceTokenizer.INSTANCE.tokenize(line.toString());
            String[] tags = tagger.tag(whitespaceTokenizerLine);

            POSSample sample = new POSSample(whitespaceTokenizerLine, tags);

            return sample.toString();
        }).collect(Collectors.toList());

    }

}
