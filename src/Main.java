import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static final String INSTRUCTIONS = """
            Cualquier tecla para obtener un proverbio aleatorio.
            proverbios -> Lista completa de proverbios.
            noClean -> Lista no limpia.
            help -> Muestra las instrucciones 😈👍
            exit -> Salir.
            """;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String input = "";

        ProverbiosService service = new ProverbiosService("./resources/CAP_1");

        System.out.println(INSTRUCTIONS);
        while (!input.matches("exit")) {
            input = sc.nextLine();
            switch (input) {
                case "proverbios" -> service.getClean().forEach(System.out::println);
                case "noClean" -> service.getProverbios().forEach(System.out::println);
                case "help" -> System.out.println(INSTRUCTIONS);
                case "exit" -> System.out.println("Vai");
                default -> System.out.println(service.getProverbio());
            }
        }
    }
}