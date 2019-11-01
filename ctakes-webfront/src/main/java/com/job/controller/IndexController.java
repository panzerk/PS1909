package com.job.controller;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.kit.HttpKit;
import com.jfinal.log.Log;
import com.jfinal.upload.UploadFile;
import com.job.utl.RestUtils;
import io.undertow.server.handlers.resource.ClassPathResourceManager;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class IndexController extends Controller {
    private final static Log LOG = Log.getLog(IndexController.class);

    public void index() {
        render("index.html");
    }


    @ActionKey("/ctakes")
    public void ctakes() {
        String analysisText = HttpKit.readData(getRequest());
        LOG.info(analysisText);

        String result = RestUtils.ctakes(analysisText);
        renderJson(result);
    }

    @ActionKey("/pos")
    public void pos() {
        String analysisText = HttpKit.readData(getRequest());
        LOG.info(analysisText);

        String result = RestUtils.pos(analysisText);
        renderJson(result);
    }

    @ActionKey(("/AnalyzeResult"))
    public void AnalyzeResult() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", 0);
        result.put("data", JSONObject.parse(RestUtils.analyzeResult()));
        renderJson(result);
    }

    @ActionKey("/upload")
    public void upload() throws IOException {
        LOG.info("start file upload");
        UploadFile uploadFile = getFile();
        StringBuilder s = new StringBuilder();
        byte[] buffer = new byte[1024];
        int l = 0;
        FileInputStream fileInputStream = new FileInputStream(uploadFile.getFile());
        while ((l = fileInputStream.read(buffer)) != -1) {
            s.append(new String(buffer, 0, l));
        }
        fileInputStream.close();
        renderJson(s.toString());
    }

    @ActionKey("/download")
    public void download() throws IOException {
        LOG.info("start download result");
        String text = getRequest().getParameter("text");
        String fileName = "result.txt";
        File file = new File("src/main/webapp/download/result.txt");
        if (!file.exists()) file.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(text.getBytes());
        fileOutputStream.close();
        LOG.info("" + file.getPath());
        renderFile("result.txt");
    }
}
