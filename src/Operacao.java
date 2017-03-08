
public class Operacao {
    private static String resto;

    private static void divisao(String mensagemStr) {
        System.out.println(mensagemStr);//Mensagem com os zeros extras
        throw new UnsupportedOperationException("Divisão ainda não umplementada");
    }

    public static String divResto(String mensagemStr) {
        divisao(mensagemStr);
        return resto;
    }//fim metodo divResto

}//fim class Operacao
