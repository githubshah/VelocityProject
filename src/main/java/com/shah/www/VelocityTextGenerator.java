package com.shah.www;

import com.sun.istack.internal.NotNull;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.IOException;
import java.io.StringWriter;

public class VelocityTextGenerator {

    public static void main(String[] args) throws IOException {

        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.init();

        VelocityContext context = new VelocityContext();
        context.put("name", "Mark");
        context.put("invoiceNumber", "42123");
        context.put("dueDate", "June 6, 2009");

        String template = "Hello $name. Please find attached invoice" +
                " $invoiceNumber which is due on $dueDate.";
        StringWriter writer = new StringWriter();
        Velocity.evaluate(context, writer, "TemplateName", template);

        writer.flush();
        writer.close();

        System.out.println(writer);
    }
}