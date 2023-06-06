package com.idrey.rpc.hook;

import com.idrey.rpc.util.NacosUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShutdownHook {

    private static final Logger logger = LoggerFactory.getLogger(ShutdownHook.class);

    private static final ShutdownHook shutdownHook = new ShutdownHook();

    public static ShutdownHook getShutdownHook() {
        return shutdownHook;
    }

    public void addClearAllHook() {
        logger.info("Shutdown will clear all registry");
        Runtime.getRuntime().addShutdownHook(new Thread(NacosUtil::clearRegistry));
    }

}
