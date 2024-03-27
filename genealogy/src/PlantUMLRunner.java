import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PlantUMLRunner {

    private static String jarPath;

    public static void setJarPath(String jarPath) {
        PlantUMLRunner.jarPath = jarPath;
    }

    public static void generate(String data, String outputDirPath, String outputFileName){
        File directory = new File(outputDirPath);
        directory.mkdirs();

        File outputFile = new File(outputDirPath + '/' + outputFileName);
        try(
            FileWriter outputWriter = new FileWriter(outputFile);
        ){
            outputWriter.write(data);
            outputWriter.close();

            ProcessBuilder processBuilder = new ProcessBuilder(
                    "java", "-jar", jarPath, outputFile.getPath()
            );

            Process process = processBuilder.start();

            process.waitFor();

            outputFile.delete();
        }
        catch (InterruptedException | IOException e){}
    }


}
