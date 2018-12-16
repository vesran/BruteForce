import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.time.Duration;
import java.time.Instant;

import java.security.*;
import java.util.Arrays;

public class Main {

    public static boolean equalsMD5(String str, byte[] hash) {

        try {
            byte [] hash1 = MessageDigest.getInstance("MD5").digest(str.getBytes());
            return Arrays.equals(hash1, hash);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String [] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String target = "zzzzz";
        byte [] hash = MessageDigest.getInstance("MD5").digest(target.getBytes());
        String [] tab = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

        Instant start = Instant.now();
        System.out.println(BruteForce.find(5, tab, x -> Main.equalsMD5(x,  hash) ));
        Instant end = Instant.now();
        System.out.println(BruteForce.find(5, tab, x -> x.equals(target) ));
        Instant end2 = Instant.now();

        // Results
        Duration time = Duration.between(start, end);
        double timeLength = time.toMillis()/1000.0;
        System.out.println("Chrono : " + timeLength + " s");

        Duration time2 = Duration.between(end, end2);
        double timeLength2 = time2.toMillis()/1000.0;
        System.out.println("Chrono : " + timeLength2 + " s");
    }

}
