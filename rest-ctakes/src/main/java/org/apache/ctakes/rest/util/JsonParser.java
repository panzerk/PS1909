package org.apache.ctakes.rest.util;

import org.apache.ctakes.rest.entity.InputText;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.Map;

public class JsonParser {

    public static InputText parse(String input) {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            InputText inputText = objectMapper.readValue(input, InputText.class);
            return inputText;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

//    public static void main(String[] args) {
//        InputText result = parse("{\"analysisText\":\"The patient treats their type 1 diabetes with humalog\"}");
//        System.out.println(result);
//    }
}
