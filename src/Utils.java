import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Utils {
    public static void logTo(String fileName, String fileContent){


        try {
            File Log = new File(fileName);
            if (Log.createNewFile()) {
                System.out.println("File created: " + fileName);
            } else {
                System.out.println("File already exists.");
            }
            FileWriter Logger = new FileWriter(fileName);
            Logger.write(fileContent);
            Logger.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static String arrayToString(double[] array) {
        StringBuilder sb = new StringBuilder();
        for (double d : array) {
            sb.append(d);
            sb.append("\n");
        }
        return sb.toString();
    }
}
