package org.example.util;
/*
Igor Correa Trifilio Campos 202365092A
Brendo Lee Visconde de Almeida	202365067A
Gabriel Toledo Gonçalves Barreto 202365083A
*/
public class AvaliadorSenha
{
    /*
    * Senha tem que ter pelo menos 8 dígitos
    * tem que ter numeral
    * tem que ter pelo menos uma maiúscula
    * Não pode ter espaços em branco
    */
    public static String AvaliarSenha(String senha)
    {
        int tamanhoSenha = senha.length();
        String temMaiuscula = ".*[A-Z].*";
        String temDig = ".*\\d.*";
        String temBlankSpace = ".*[\\s.]*";

        boolean vefT = tamanhoSenha > 8;
        boolean vefM = senha.matches(temMaiuscula);
        boolean vefD = senha.matches(temDig);
        boolean vefBP = senha.matches(temBlankSpace);

        String res ="";
        if(!vefT) res = res + "<p style ='color:red'>Tamanho menor que 8</p>";
        if(!vefM) res = res + "<p style ='color:red'>não tem maiúsculas </p>";
        if(!vefD) res = res + "<p style ='color:red'>não tem dígitos</p>";
        if(!vefBP)res = res + "<p style ='color:red'>A senha não pode conter minúsculas</p>";

        return res.isEmpty()?"Senha adequada" : res;
    }
}
