import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try (Socket socket = new Socket("netology.homework", Server.PORT);
             PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            while (true) {
                System.out.println(bufferedReader.readLine());
                String dialog = scanner.nextLine();
                printWriter.println(dialog);
                if ("Пока".equals(dialog)) {
                    System.out.println("");
                    System.out.println(bufferedReader.readLine());
                    System.out.println("Сеанс завершен.");
                    break;
                }
            }

        } catch (Exception a) {
            throw new RuntimeException(a);
        }
    }
}


