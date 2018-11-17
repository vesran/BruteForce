import java.time.Duration;
import java.time.Instant;

public class Main {

    public static void main(String [] args) {
        String [] tab = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

        Instant start = Instant.now();
        BruteForce.find(6, tab, (x -> x.equals("zzzzzz")));
        Instant end = Instant.now();

        // Results
        Duration time = Duration.between(start, end);
        double timeLength = time.toMillis()/1000.0;
        System.out.println("Chrono : " + timeLength + " ms");
    }

}
