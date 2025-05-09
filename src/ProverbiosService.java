import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ProverbiosService {
    private static final List<Chapter> chapters = new ArrayList<>();

    public ProverbiosService(String path) {
        Arrays.stream(Path.of(path).toFile().list()).forEach(s -> {
            try {
                List<String> yea = Files.readAllLines(Path.of(path + s));
                chapters.add(toChapter(yea));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public List<Chapter> getChapters() {
        return chapters;
    }

    public Chapter getRandomChapter() {
        return chapters.get(new Random().nextInt(chapters.size()));
    }

    private Chapter toChapter(List<String> chapter) {
        return new Chapter(chapter.get(0)
                , chapter.stream().skip(1).takeWhile(s -> !s.matches("^\\d.*")).collect(Collectors.joining(" "))
                , chapter.stream().dropWhile(s -> !s.matches("^\\d.*")).toList());
    }
}
