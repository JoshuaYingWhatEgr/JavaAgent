package com.joshuayingwhat.agent;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class CustomTransformer implements ClassFileTransformer {

    private CtClass ctClass;

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {

        if (!className.startsWith("com/joshuayingwhat/agent/")) {
            return classfileBuffer;
        }

        try {
            ClassPool aDefault = ClassPool.getDefault();

            ctClass = aDefault.makeClass(new ByteArrayInputStream(classfileBuffer));

            for (CtMethod method : ctClass.getMethods()) {

                //提价一个局部变量 start 所以需要
                method.addLocalVariable("start", CtClass.longType);


                method.insertBefore("start = System.currentTimeMillis();");

                String methodName = method.getLongName();

                method.insertAfter("System.out.println(\"" + "方法名称: " + methodName + " 耗时: cost: \" + (System" +
                        ".currentTimeMillis() - start));");
            }

            return ctClass.toBytecode();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        }


        return new byte[0];
    }
}
