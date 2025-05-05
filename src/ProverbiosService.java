import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProverbiosService {
    private final List<String> file;
    private final List<String> proverbios;
    private final List<String> clean;
    private final String chapter;
    private final String description;

    public ProverbiosService(String path) {
        try {
            file = Files.readAllLines(Path.of(path));
            chapter = file.getFirst();
            description = file.stream().skip(1).takeWhile(s -> !s.matches("\\d.+"))
                    .flatMap(this::lineToWords).collect(Collectors.joining(" "));
            proverbios = file.stream().dropWhile(s -> !s.matches("\\d.+")).toList();

            var tmp = new ArrayList<String>();
            String s = "";
            for (String proverbio : proverbios) {
                if (proverbio.matches("\\d.*")) {
                    tmp.add(s);
                    s = "";
                }
                s += s.matches(".*([,\\w])") ? " " + proverbio : proverbio;
            }
            clean = tmp;
        } catch (IOException e) {
            throw new RuntimeException("Error reading file", e);
        }
    }

    public String getProverbio() {
        return clean.get(new Random().nextInt(clean.size()));
    }

    public List<String> getProverbios() {
        return proverbios;
    }

    private Stream<String> lineToWords(String line) {
        return Arrays.stream(line.split(" "));
    }

    public List<String> getClean() {
        return clean;
    }

    public List<String> getFile() {
        return file;
    }

    public String getChapter() {
        return chapter;
    }

    public String getDescription() {
        return description;
    }
}
