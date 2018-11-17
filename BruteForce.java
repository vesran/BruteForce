import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.InvalidParameterException;
import java.util.function.Predicate;

public class BruteForce {
    /**
     * Create a file list.txt with all possible sequences that can be made with the given array.
     * @param maxLength Maximal length of the sequences
     * @param alphabet  Contains each individual element that will be used for building a sequence.
     */
    public static <T> void inFile(int maxLength, T[] alphabet) {
        if (maxLength <= 0 || alphabet == null || alphabet.length <= 0) {
            System.out.println("Parameters are incorrect !");
            throw new InvalidParameterException("First parameter should be a positive integer and the second non null with at least one element inside.");
        }

        CycleList<T> cList = new CycleList<>(alphabet);
        Cogwheel<T> cw;
        PrintWriter writer = null;

        try {
            writer = new PrintWriter("list.txt", "UTF-8");
            int length = 1;
            do {
                cw = new Cogwheel<>(length++, cList);
                cw.action(writer);
            } while (length <= maxLength);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            System.out.println(e);

        } finally {
            if (writer != null) writer.close();
        }
    }

    /**
     * Find the first sequence that makes the predicate test returns true.
     * @param maxLength Maximal length of the sequences
     * @param alphabet  Contains each individual element that will be used for building a sequence.
     * @param pred         Is the condition to return a sequence.
     * @return This method returns the sequence as a String and null if nothing has been found.
     */
    public static <T> String find(int maxLength, T[] alphabet, Predicate<String> pred) {
        if (maxLength <= 0 || alphabet == null || alphabet.length <= 0) {
            System.out.println("Parameters are incorrect !");
            throw new InvalidParameterException("First parameter should be a positive integer and the second non null with at least one element inside.");
        }

        CycleList<T> cList = new CycleList<>(alphabet);
        Cogwheel<T> cw;
        StringBuilder result;
        int length = 1;

        do {
            cw = new Cogwheel<>(length++, cList);
            if ((result = cw.action(pred)) != null) return result.toString(); // Check if cw.action(p) returns null
        } while (length <= maxLength);

        System.out.println("Nothing has been found.");
        return null;
    }
}
