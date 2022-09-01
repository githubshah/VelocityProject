package com.shah.www;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class VelocityHtmlGenerator {

    static boolean loadFromResource = true;
    static boolean loadFromFileLocation = true;
    static String inputTemplate = "html_example.vm";
    static String message = "Hello World!";
    static String outputFile = "helloworld.html";

    public static void main(String[] args) throws IOException {

        VelocityEngine velocityEngine = new VelocityEngine();

        if (loadFromResource) {
            loadInputTemplateFromProjectResourceFolder(velocityEngine);
        } else if (loadFromFileLocation) {
            loadInputTemplateFromFileLocation(velocityEngine);
        }

        velocityEngine.init();

        VelocityContext context = new VelocityContext();
        context.put("message", message);

        Writer writer = new FileWriter(new File(outputFile));

        Template t = velocityEngine.getTemplate(inputTemplate);
        t.merge(context, writer);
        writer.flush();
        writer.close();

        System.out.println("Generated " + outputFile);
    }

    private static void loadInputTemplateFromProjectResourceFolder(VelocityEngine velocityEngine) {
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
    }

    private static void loadInputTemplateFromFileLocation(VelocityEngine velocityEngine) {
        velocityEngine.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, "D:\\codebase\\JavaTest\\src\\main\\resources");
    }
}