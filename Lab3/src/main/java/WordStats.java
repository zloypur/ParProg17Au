import java.util.Map;
import java.util.TreeMap;


public class WordStats{

    private Map<String, Integer> statisic;

    WordStats(String[] files){
        statisic = new TreeMap<String, Integer>();

        Thread[] tr = new Thread[files.length];

        for(int i = 0; i < files.length; i++){
            FileStats f = new FileStats(files[i]);
            tr[i] = new Thread(f);
            tr[i].start();
            try {
                tr[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Map<String, Integer> getStatisic() {
        return statisic;
    }

    private class FileStats implements Runnable {

        private String text;

        public FileStats(String text){
            this.text = text;
        }


        @Override
        public void run() {
            String[] words = text.split("[\\W]+");//this regexp works only with english words

            for(String s : words)
                System.out.println(s);

            for(int i = 0; i < words.length && !Thread.interrupted(); i++)
                synchronized (statisic) {
                    if (statisic.containsKey(words[i]))
                        statisic.replace(words[i], statisic.get(words[i]) + 1);
                    else
                        statisic.put(words[i], 1);
                }
        }
    }
}
