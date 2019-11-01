package com.job.controller;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.kit.HttpKit;
import com.jfinal.log.Log;
import com.job.utl.RestUtils;


public class IndexController extends Controller {
    private final static Log LOG = Log.getLog(IndexController.class);

    public void index(){
        render("index.html");
    }


    @ActionKey("/ctakes")
    public void ctakes(){
        String analysisText = HttpKit.readData(getRequest());
        LOG.info(analysisText);

        String result = RestUtils.ctakes(analysisText);
        renderJson(result);
    }

    @ActionKey("/pos")
    public void pos(){
        String analysisText = HttpKit.readData(getRequest());
        LOG.info(analysisText);

        String result = RestUtils.pos(analysisText);
        renderJson(result);
    }


}
