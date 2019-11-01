package com.job.config;

import com.jfinal.config.*;
import com.jfinal.json.FastJsonFactory;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.PropKit;
import com.jfinal.log.Log;
import com.jfinal.template.Engine;
import com.job.controller.IndexController;


public class Config extends JFinalConfig {

    private final static Log LOG = Log.getLog(Config.class);

    @Override
    public void configConstant(Constants constants) {
       
        constants.setDevMode(PropKit.use("undertow.properties").getBoolean("undertow.devMode", false));

        constants.setJsonFactory(new FastJsonFactory());
        constants.setJsonDatePattern("yyyy-MM-dd");

        
        constants.setInjectDependency(true);
    }

    @Override
    public void configRoute(Routes routes) {
        routes.add("/", IndexController.class);
    }

    @Override
    public void configEngine(Engine engine) {
        engine.setToClassPathSourceFactory();
        engine.setBaseTemplatePath("/templates");

        LOG.info("-->[BaseTemplatePath]: " + engine.getBaseTemplatePath());
        LOG.info("-->[WebRootPath]: " + PathKit.getWebRootPath());
    }

    @Override
    public void configPlugin(Plugins plugins) {

    }

    @Override
    public void configInterceptor(Interceptors interceptors) {

    }

    @Override
    public void configHandler(Handlers handlers) {

    }
}
