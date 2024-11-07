import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    static int PORT = 8080;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Сервер запускается...");
            while (true) {
                try (Socket Clirntsocket = serverSocket.accept();
                     PrintWriter printWriter = new PrintWriter(Clirntsocket.getOutputStream(), true);
                     BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Clirntsocket.getInputStream()))) {
                    System.out.println("К серверу подключаются с хоста " + Clirntsocket.getLocalAddress() +
                            " порт: " + Clirntsocket.getPort());
                    printWriter.println("Как Вас зовут?");
                    final String name = bufferedReader.readLine();
                    System.out.println("Меня зовут " + name);
                    printWriter.println("Сколько " + name + " Вам лет?");
                    final int age = Integer.parseInt(bufferedReader.readLine());
                    System.out.println("Мне " + age);
                    if (age <= 17) {printWriter.println(name + " вам " + age + " лет и вам разрешен доступ к детскому контенту" +
                            "Для завершения сеанса введите 'Пока'");
                    }
                    if (age >= 18){printWriter.println(name + " вам " + age + " лет/года и вам разрешен доступ к контенту 18+." +
                            "Для завершения сеанса введите 'Пока'" );
                    }

                    final String exit = bufferedReader.readLine();
                    if ("Пока".equals(exit)) {
                        printWriter.println("Досвидания");
                    }


                }
            }
        } catch (Exception a) {
            throw new RuntimeException(a);
        }

    }


}
