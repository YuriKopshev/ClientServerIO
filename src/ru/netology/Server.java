package ru.netology;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int PORT = 8181;

    // В данной задаче выбран способ взаимодействия (BlockingIO), так как числа передаются последовательно,
    // и результат соответственно рассчитывается для одного переданного числа...

    //в данной реализации ряд Фибоначчи начинается с 1,1.....
    public static int getFibonacciValue(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return getFibonacciValue(n - 1) + getFibonacciValue(n - 2);
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket servSocket = new ServerSocket(PORT);
        while (true) {
            try (Socket socket = servSocket.accept();
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new
                         InputStreamReader(socket.getInputStream()))) {
                String line;
                while ((line = in.readLine()) != null) {
                    int count = Integer.parseInt(line);
                    if (count>=47){
                        out.println("Слишком большое число для моего типа(((");
                       continue;
                    }
                    int result = getFibonacciValue(count);
                    out.println("Число Фибоначчи под порядковым номером " + count + " равно " + result);
                    if (line.equals("end")) {
                        break;
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace(System.out);
            }
        }
    }
}

