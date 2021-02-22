package com.test.gigantec.utils;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadUtil {
    public static final ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
}
