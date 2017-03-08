
import java.io.*;
import java.net.*;
import java.util.Scanner;

class Client {

    public static void main(String args[]) {
        try {
            System.out.println("Iniciando cliente.\nIniciando conexão com o servidor.");
            // Objeto Socket para estabelecer a conexão com o servidor 
            Socket skt = new Socket("127.0.0.1", 1234); //new Socket("localhost", 1234);

            System.out.print("Conexão estabelecida!\n");

            // E/S de dados associados a conexão
            InputStream input = skt.getInputStream();
            OutputStream output = skt.getOutputStream();

            // InputStream oferece uma interface para leitura de Bytes
            // BufferedReader oferece uma interface para leitura de Strings
            BufferedReader in = new BufferedReader(new InputStreamReader(input));

            // OutputStream oferece uma interface para Gravação de Bytes
            // PrintStream oferece uma interface para Gravação de Strings
            PrintStream out = new PrintStream(output);

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("Digite: ");
                String msg = scanner.nextLine();// Lê mensagem do teclado
                out.println(msg);// Envia a mensagem ao servidor

                if ("FIM".equals(msg)) {
                    break;
                } else {
                    msg = in.readLine(); // aguarda a resposta do servidor
                    System.out.println("Mensagem do servidor: " + msg);
                }
            }//fim while
            
            System.out.println("Encerrando conexão.");
            in.close();
            out.close();
            skt.close();
        } catch (Exception e) {
            System.out.print("Ops! Não deu certo!\n");
            System.err.println(e);
        }
    }
}
