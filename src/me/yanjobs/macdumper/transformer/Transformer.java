package me.yanjobs.macdumper.transformer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.ProtectionDomain;
import java.util.Arrays;
import java.util.List;

import static me.yanjobs.macdumper.Dumper.directory;

public class Transformer implements ClassFileTransformer {

    @SuppressWarnings("all")
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {

        if (shouldDump(className)) {


            String newName = className + ".class";

            if (newName.contains("/")) {
                try {
                    Files.createDirectories(Paths.get(directory + "classes/" + newName.substring(0, newName.lastIndexOf('/'))));
                } catch (IOException e) {
                    System.out.println("Error creating subdirectory: " + e.getMessage());
                }
            }

            try {
                FileOutputStream fos = new FileOutputStream(directory + "classes/" + newName);
                fos.write(classfileBuffer);
                fos.close();
                System.out.println("Dumping: " + className + "on:" +  directory + "classes/" + newName);
            } catch (IOException e) {
                System.out.println("Error writing class: " + e.getMessage());
            }
        }
        return classfileBuffer;
    }

    private boolean shouldDump(String className) {

        if (className == null) return false;

        for (String e : exclusions) {
            if (className.startsWith(e)) {
                return false;
            }
        }

        return true;
    }

    //This classes won't be dumped from the JVM.
    private final List<String> exclusions = Arrays.asList(
            "java", "sun", "javax", "jdk", "net/minecraft",
            "com/sun", "org/spongepowered", "com/google"
    );

}
