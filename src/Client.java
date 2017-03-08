
import java.io.*;
import java.net.*;
import java.util.Scanner;

class Client {

    //Mensagem (ou polinomio)
    private static String mensagemStr;
    //Gerador
    private static String geradorStr;
    private static int grauGerador;
    //Resto
    private static String divRestoStr;
    //Mensagem com o Resto da Divisão
    private static String mensagemRes;
    

    public static void main(String args[]) {
        try {
            System.out.println("Iniciando cliente.\nIniciando conexão com o servidor.");
            // Objeto Socket para estabelecer a conexão com o servidor 
            Socket skt = new Socket("127.0.0.1", 1234); //new Socket("localhost", 1234);

            System.out.print("Conexão estabelecida!\n\"FIM\" para encerrar\n");

            //<editor-fold defaultstate="collapsed" desc="Leitura do Socket">
            // E/S de dados associados a conexão
            InputStream input = skt.getInputStream();
            OutputStream output = skt.getOutputStream();

            // InputStream oferece uma interface para leitura de Bytes
            // BufferedReader oferece uma interface para leitura de Strings
            BufferedReader in = new BufferedReader(new InputStreamReader(input));

            // OutputStream oferece uma interface para Gravação de Bytes
            // PrintStream oferece uma interface para Gravação de Strings
            PrintStream out = new PrintStream(output);
            //</editor-fold>

            Scanner scanner = new Scanner(System.in);
            
            /*
             Algoritmo CRC
             */
            System.out.print("Digite o gerador: ");
            geradorStr = scanner.nextLine();// Recebe o gerador
            grauGerador = geradorStr.length() - 1; // Meno um pois o grau de um polinomio inicia-se em zero

            while (true) {
                System.out.print("Digite a mensagem: ");
                mensagemStr = scanner.nextLine();// Lê mensagem do teclado
                
                String msgZero = mensagemStr;// Copia da mensagem para a divisão
                for (int i = 0; i < grauGerador; i++) {// Adiciona os zeros extras
                    msgZero = msgZero.concat("0");
                }
                
                divRestoStr = Operacao.divResto(msgZero);// Faz a divisão e retorna o divResto
                
                mensagemRes = mensagemStr.concat(divRestoStr);

                out.println(mensagemRes);// Envia a mensagem ao servidor

                if ("FIM".equals(mensagemStr)) {
                    break;
                } else {
                    mensagemStr = in.readLine(); // aguarda a resposta do servidor
                    System.out.println("Mensagem do servidor: " + mensagemRes);
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
    }//fim metodo main
}//fim classe Client
