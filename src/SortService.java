import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SortService {

    public int sort(String name) {
        Random random = new Random();
        return random.nextInt(1,3);
    }
}
