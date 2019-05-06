package com.teste.carloja.utils;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.regex.Pattern;

public class StringUtils {

    public static String getDocument(String document, String type) {
        String response = document.replace(" ", "").replace("-", "").replace("/", "").replace(".", "");
        if (type.equalsIgnoreCase("pf")) {
            if (response.length() < 11) {
                response = ("00000000000" + response).substring(response.length());
            }
        } else { // pj
            if (response.length() < 14) {
                response = ("00000000000000" + response).substring(response.length());
            }
        }
        return response;
    }

    public static String getDocumentClean(String document) {
        return document.replace(" ", "").replace("-", "").replace("/", "").replace(".", "");
    }

    public static String getDocumentFormatted(String document) {
        if (document.length() == 11) { // CPF
            return cpfAdapterFormat(document);
        } else if (document.length() == 14) { // CNPJ
            return cnpjAdapterFormat(document);
        } else {
            return null;
        }
    }

    /**
     * Formata um CNPJ no formato XXXXXXXXXXXXXX para XX.XXX.XXX/XXXX-XX
     *
     * @param cnpj
     * @return
     */
    public static String cnpjAdapterFormat(String cnpj) {
        if (!cnpj.equals("") && cnpj != null) {
            String parteUm = cnpj.substring(0, 2);
            String parteDois = cnpj.substring(2, 5);
            String parteTres = cnpj.substring(5, 8);
            String parteQuatro = cnpj.substring(8, 12);
            String parteCinco = cnpj.substring(12, 14);
            return parteUm + "." + parteDois + "." + parteTres + "/" + parteQuatro + "-" + parteCinco;
        }
        return null;
    }

    /**
     * Formata um CPF no formato XXXXXXXXXXX para XXX.XXX.XXX-XX
     *
     * @param cpf
     * @return
     */
    public static String cpfAdapterFormat(String cpf) {
        if (!cpf.equals("") && cpf != null) {
            String parteUm = cpf.substring(0, 3);
            String parteDois = cpf.substring(3, 6);
            String parteTres = cpf.substring(6, 9);
            String parteQuatro = cpf.substring(9, 11);
            return parteUm + "." + parteDois + "." + parteTres + "-" + parteQuatro;
        }
        return null;
    }

    /**
     * Recebe uma string de um valor e formata no padrão brasileiro (R$ 1.000,00)
     *
     * @param valor Valor não formatado
     * @return valor formatado no padrão brasileiro
     */
    public static String formatarValor(String valor) {
        if (isNullOrEmpty(valor))
            return "R$ 0,00";
        if (valor.contains("R$"))
            return valor;
        valor = valor.replace("$", "");
        Double valorDebito = Double.parseDouble(valor);
        Locale ptBr = new Locale("pt", "BR");
        NumberFormat nf = NumberFormat.getCurrencyInstance(ptBr);
        return nf.format(valorDebito);
    }

    /**
     * Recebe uma string de um valor formatado e converte para double
     *
     * @param valor Valor formatado
     * @return valor convertido para double
     */
    public static double getDoubleValor(String valor) {
        valor = valor.replace("R$", "").replace("$", "");
        return Double.parseDouble(valor);
    }

    public static boolean isNullOrEmpty(String text) {
        return text == null || text.isEmpty() || text.trim().length() == 0 || text.equals("null")
                || text.equals("");
    }

    public static String getPhone(String phone) {
        return phone.replace("(", "").replace(")", "").replace("-", "").replace(" ", "");
    }

    public static String removerCaracteresEspeciais(String string) {
        if (string == null)
            return null;

        string = string.replaceAll("[ÂÀÁÄÃ]", "A");
        string = string.replaceAll("[âãàáä]", "a");
        string = string.replaceAll("[ÊÈÉË]", "E");
        string = string.replaceAll("[êèéë]", "e");
        string = string.replaceAll("[ÎÍÌÏ]", "I");
        string = string.replaceAll("[îíìï]", "i");
        string = string.replaceAll("[ÔÕÒÓÖ]", "O");
        string = string.replaceAll("[ôõòóö]", "o");
        string = string.replaceAll("[ÛÙÚÜ]", "U");
        string = string.replaceAll("[ûúùü]", "u");
        string = string.replace("Ç", "C");
        string = string.replace("ç", "c");
        string = string.replace("Ã", "A");
        string = string.replace("ã", "a");
        string = string.replace("[ýÿ]", "y");
        string = string.replace("Ý", "Y");
        string = string.replace("ñ", "n");
        string = string.replace("Ñ", "N");
        // string = string.replace(".", "");
        string = string.replace(",", "");
        string = string.replace("-", "");
        string = string.replace("&", "e");
        string = string.replace("\\(", "");
        string = string.replace("\\)", "");
        string = string.replace("#", "");
        string = string.replace("@", "");
        string = string.replace("®", "");
        string = string.replace("\\?", "");
        string = string.replace("'", "");
        string = string.replace("´", "");
        string = string.replace("[", "(");
        string = string.replace("]", ")");
        string = string.replace("~", "");
        string = string.replace("\"", "");
        string = string.replace("#", "");
        string = string.replace("¨", "");
        string = string.replace("`", "");
        string = string.replace("|", "");

        Pattern p = Pattern.compile("[0-9]*[a-zA-Z]*[.]*[-]*[\\/]*");
        for (int i = 0; i < string.length(); i++) {
            String c = String.valueOf(string.charAt(i));
            if (!p.matcher(c).matches())
                string = string.replace(c, "");
        }

        return string;
    }

    public static String getCep(String cep) {
        if (!isNullOrEmpty(cep)) {
            cep = cep.replace("-", "");
            cep = cep.replace(".", "");
            while (cep.length() < 8)
                cep = "0" + cep;
        }
        return cep;
    }
}
