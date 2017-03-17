
public class Operacao {
    private static String resto = "";
    private static String mensagemPart;
    private static String geradorEsp = "";//Inicialmente vazio
    private static char charM;
    private static char charG;
    private static int ct = 0;

    private static void divisao(String mensagemStr, String geradorStr) {
        //Criação do gerador Especial
        for (int i = 0; i < geradorStr.length(); i++) {
            geradorEsp = geradorEsp.concat("0");
        }
        
        mensagemPart = mensagemStr.substring(0, geradorStr.length() + ct);// pega o primeiro numero para dividir
        
        for (int j = 0; j < mensagemStr.length()-geradorStr.length(); j++) {
            System.out.println("mensagemPart.charAt(0) -> "+ mensagemPart.charAt(0));
            if (mensagemPart.charAt(0) != 0) {
                for (int i = 0; i < geradorStr.length(); i++) {
                    charM = mensagemPart.charAt(i);
                    charG = geradorStr.charAt(i);
                    if (charM == charG) // se forem iguais
                        resto = resto.concat("0");//resulta zero
                    else//se forem diferentes
                        resto = resto.concat("1");//resulta um                
                }//fim for
            } else {//se iniciar com zero usa o gerador especial
                for (int i = 0; i < geradorStr.length(); i++) {
                    charM = mensagemPart.charAt(i);
                    charG = geradorEsp.charAt(i);
                    if (charM == charG) // se forem iguais
                        resto = resto.concat("0");//resulta zero
                    else//se forem diferentes
                        resto = resto.concat("1");//resulta um                
                }//fim for
            }//fim else
            //elimina a primeira pos do resto e adiciona a proxima posição depois dos que já foram pegos
            System.out.println("resto -> "+resto);
            mensagemPart = resto.substring(1) + mensagemStr.charAt(geradorStr.length() + ct);
            System.out.println("mensagemPart -> "+mensagemPart);
            ct++;//usado no próximo ciclo(se houver)
            resto="";
        }//fim for externo
        System.out.println("mensagemPart -> "+mensagemPart);
    }//fim metodo divisão

    public static String divResto(String mensagemStr, String geradorStr) {
        divisao(mensagemStr, geradorStr);// Faz a divisão
        return resto;
    }//fim metodo divResto

}//fim class Operacao
