package me.yanjobs.macdumper;

import me.yanjobs.macdumper.transformer.Transformer;

import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Dumper {

    public static String directory = "~/Dumper/";

    public static void agentmain(String agentArgs, Instrumentation inst) {
        System.out.println("Loading Dumper...");

        try {
            Files.createDirectories(Paths.get(directory + "classes"));
        } catch (IOException e) {
            System.out.println("Error creating the main directory: " + e.getMessage());
        }

        inst.addTransformer(new Transformer());
    }
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("Loading Dumper...");

        try {
            Files.createDirectories(Paths.get(directory + "classes"));
        } catch (IOException e) {
            System.out.println("Error creating the main directory: " + e.getMessage());
        }

        inst.addTransformer(new Transformer());
    }
}

