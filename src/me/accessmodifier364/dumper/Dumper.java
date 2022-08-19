package me.accessmodifier364.dumper;

import me.accessmodifier364.dumper.transformer.Transformer;

import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * https://docs.oracle.com/javase/9/docs/api/java/lang/instrument/package-summary.html
 *
 * @author accessmodifier364
 * @since 11-Nov-2021
 */

public class Dumper {

    public static String directory = System.getenv("USERPROFILE") + "/Desktop/dump/";

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

