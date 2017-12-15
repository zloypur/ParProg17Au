import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Main {
    public static void main(String[] args) {
        File dir = new File("Files");

        System.out.print(dir.getAbsolutePath());

        String[] fileNames = dir.list();

        List<String> files = new ArrayList<>();

        for(String s : fileNames){
            FileInputStream inFile = null;
            try {
                inFile = new FileInputStream(dir.getPath() + File.separator + s);
                byte[] str = new byte[inFile.available()];
                inFile.read(str);
                String text = new String(str);
                files.add(text);
            } catch (FileNotFoundException e) {
            e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        
        WordStats stats = new WordStats(files.toArray(new String[files.size()]));

        Map<String, Integer> res = stats.getStatisic();

        System.out.println("res = " + res);
    }
}
