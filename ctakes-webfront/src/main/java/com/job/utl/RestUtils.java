package com.job.utl;

import com.jfinal.kit.HttpKit;
import com.jfinal.kit.JsonKit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RestUtils {
    private static final Map<String, String> headers = new HashMap<>();

    static {
        headers.put("Content-Type", "application/json");
    }

    public static String ctakes(String jsonbody) {
        return HttpKit.post("http://127.0.0.1:8080/analyze", null, jsonbody, headers);
    }


    public static String pos(String jsonbody) {

        return HttpKit.post("http://127.0.0.1:8080/pos", null, jsonbody, headers);
    }

    public static String analyzeResult(){
        return HttpKit.post("http://127.0.0.1:8080/AnalyzeResult", null, null, headers);
    }

}
