import java.util.List;
import java.util.Random;

public record Chapter(String chapter, String description, List<String> proverbs) {
    public String getRandomProverbio() {
        return proverbs.get(new Random().nextInt(proverbs.size()));
    }
}
