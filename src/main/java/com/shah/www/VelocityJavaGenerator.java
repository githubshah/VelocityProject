package com.shah.www;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.*;

public class VelocityJavaGenerator {

    static String inputTemplate = "java_example.vm";
    static String className = "VelocityExample";
    static String message = "Hello World!";
    static String outputFile = className + ".java";

    public static void main(String[] args) throws IOException {

        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        velocityEngine.init();

        VelocityContext context = new VelocityContext();
        context.put("className", className);
        context.put("message", message);

        Writer writer = new FileWriter(new File(outputFile));

        Template t = velocityEngine.getTemplate(inputTemplate);
        t.merge(context, writer);
        writer.flush();
        writer.close();

        System.out.println("Generated " + outputFile);
    }
}