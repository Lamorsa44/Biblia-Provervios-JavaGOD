import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static final String INSTRUCTIONS = """
            Cualquier tecla para obtener un proverbio aleatorio.
            proverbios -> Lista completa de proverbios.
            chapter -> Capitulo
            desc -> Description
            help -> Muestra las instrucciones 😈👍
            exit -> Salir.
            """;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String input = "";
        ProverbiosService service = new ProverbiosService("./resources/");
        Chapter chapter = service.getRandomChapter();

        System.out.println(INSTRUCTIONS);
        while (!input.matches("exit")) {
            input = sc.nextLine();
            switch (input) {
                case "proverbios" -> chapter.proverbs().forEach(System.out::println);
                case "chapter" -> System.out.println(chapter.chapter());
                case "desc" -> System.out.println(chapter.description());
                case "help" -> System.out.println(INSTRUCTIONS);
                case "exit" -> System.out.println("Vai");
                default -> System.out.println(chapter.getRandomProverbio());

            }
        }
    }
}