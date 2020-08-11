import java.lang.instrument.Instrumentation;

public class SimpleAgent {

    public static void premain(String agentArgs, Instrumentation instrumentation) {
        System.out.println("agent premain");
    }

    public static void agentmain(String agentArgs, Instrumentation instrumentation) {
        System.out.println("agent agentmain");
    }
}
