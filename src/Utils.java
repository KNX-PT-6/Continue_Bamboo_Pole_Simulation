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
            sb.append("\r\n");
        }
        return sb.toString();
    }

    public static String arrayToConfidenceInterval(double[] sorted_array) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(sorted_array[0]);
        sb.append(", ");
        sb.append(sorted_array[sorted_array.length-1]);
        sb.append("]");
        return sb.toString();
    }
}
