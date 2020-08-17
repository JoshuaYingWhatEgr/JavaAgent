package com.joshuayingwhat.agent;

import java.lang.instrument.Instrumentation;

public class SimpleAgent {

    public static void premain(String agentArgs, Instrumentation instrumentation) {
        System.out.println("agent premain");
        instrumentation.addTransformer(new CustomTransformer(), true);
    }

    public static void agentmain(String agentArgs, Instrumentation instrumentation) {
        System.out.println("agent agentmain");
        instrumentation.addTransformer(new CustomTransformer(), true);
    }
}
