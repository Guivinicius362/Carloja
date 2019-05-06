package com.teste.carloja.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.Months;
import org.joda.time.Years;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateTimeUtils {

    public final static String TIME_ZONE = "America/Sao_Paulo";

    /**
     * Retorna a data e hora atual no formato: yyyy-MM-dd HH:mm:ss.SSS
     *
     * @return data e hora atual
     */
    public static String getNow() {
        TimeZone timeZone = TimeZone.getTimeZone(TIME_ZONE);
        LocalDateTime localDateTime = new LocalDateTime(DateTimeZone.forID(timeZone.getID()));
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS");
        return localDateTime.toString(fmt);
    }

    /**
     * Retorna a data e hora atual no formato: dd/MM/yyyy HH:mm:ss
     *
     * @return data e hora atual
     */
    public static String getNowHeader() {
        TimeZone timeZone = TimeZone.getTimeZone(TIME_ZONE);
        LocalDateTime localDateTime = new LocalDateTime(DateTimeZone.forID(timeZone.getID()));
        DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
        return localDateTime.toString(fmt);
    }

    /**
     * Retorna a idade de acordo com a data de nascimento recebida por parâmetro
     *
     * @param date data de nascimento
     * @return idade
     */
    public static int getAge(String date) {
        LocalDate birthDate = LocalDate.parse(date, DateTimeFormat.forPattern("dd/MM/yyyy"));
        LocalDate now = new LocalDate();
        return Years.yearsBetween(birthDate, now).getYears();
    }

    /**
     * Retorna a idade de acordo com a data de nascimento recebida por parâmetro
     *
     * @param date data de nascimento
     * @param pattern padrão da data
     * @return idade
     */
    public static int getAgeFromPattern(String date, String pattern) {
        LocalDate birthDate = LocalDate.parse(date, DateTimeFormat.forPattern(pattern));
        LocalDate now = new LocalDate();
        return Years.yearsBetween(birthDate, now).getYears();
    }

    /**
     * Compara duas datas e retorna a mais recente
     *
     * @param date1
     * @param pattern1
     * @param date2
     * @param pattern2
     * @return data no formato dd/MM/yyyy
     */
    public static String getRecentDate(String date1, String pattern1, String date2, String pattern2) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        LocalDate dataUm = LocalDate.parse(date1, DateTimeFormat.forPattern(pattern1));
        LocalDate dataDois = LocalDate.parse(date2, DateTimeFormat.forPattern(pattern2));
        if (dataUm.isAfter(dataDois))
            return formato.format(dataUm.toDate());
        else
            return formato.format(dataDois.toDate());
    }

    /**
     * Obtem a data mais recente de uma lista
     *
     * @param dates
     * @param pattern
     * @return data no formato do pattern
     */
    public static String getNewerDateFromList(List<String> dates, String pattern) {
        String maiorDataStr = dates.get(0);
        LocalDate maiorData = LocalDate.parse(maiorDataStr, DateTimeFormat.forPattern(pattern));
        for (int i = 0; i < dates.size(); i++) {
            LocalDate data = LocalDate.parse(dates.get(i), DateTimeFormat.forPattern(pattern));
            if (data.isAfter(maiorData)) {
                maiorDataStr = dates.get(i);
                maiorData = data;
            }
        }
        return maiorDataStr;
    }

    /**
     * Obtem a data mais antiga de uma lista
     *
     * @param dates
     * @param pattern
     * @return data no formato do pattern
     */
    public static String getOlderDateFromList(List<String> dates, String pattern) {
        String oldDataStr = dates.get(0);
        LocalDate oldData = LocalDate.parse(oldDataStr, DateTimeFormat.forPattern(pattern));
        for (int i = 0; i < dates.size(); i++) {
            LocalDate data = LocalDate.parse(dates.get(i), DateTimeFormat.forPattern(pattern));
            if (data.isBefore(oldData)) {
                oldDataStr = dates.get(i);
                oldData = data;
            }
        }
        return oldDataStr;
    }

    /**
     * Retorna a diferença de meses entre as datas recebidas como parâmetro
     *
     * @param startDate Data inicial
     * @param endDate Data final
     * @return número de meses entre as datas
     */
    public static int getMonthsBetween(String startDate, String endDate) {
        LocalDate start = LocalDate.parse(startDate, DateTimeFormat.forPattern("dd/MM/yyyy"));
        LocalDate end = LocalDate.parse(endDate, DateTimeFormat.forPattern("dd/MM/yyyy"));
        return Months.monthsBetween(start, end).getMonths();
    }

    /**
     * Retorna a diferença de meses entre as datas recebidas como parâmetro
     *
     * @param startDate Data inicial
     * @param pattern1 Formato da Data inicial
     * @param endDate Data final
     * @param pattern2 Formato da Data final
     * @return número de meses entre as datas
     */
    public static int getMonthsBetweenWithPattern(String startDate, String pattern1, String endDate,
                                                  String pattern2) {
        LocalDate start = LocalDate.parse(startDate, DateTimeFormat.forPattern(pattern1));
        LocalDate end = LocalDate.parse(endDate, DateTimeFormat.forPattern(pattern2));
        return Months.monthsBetween(start, end).getMonths();
    }

    /**
     * Converte a data recebida como parâmetro (no formato recebido pelo JSON do provider ex:
     * /Date(1492992000000+0000)/) para o formato datetime formatado (dd/MM/yyyy HH:mm:ss)
     *
     * @param data data no formato recebido pelo JSON do provider ex: /Date(1492992000000+0000)/
     * @return data formatada para dd/MM/yyyy HH:mm:ss
     */
    public static String convertDataJSONToDateTime(String data) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String numero = data.substring(data.indexOf('(') + 1, data.indexOf('-'));
        Date date = new Date(Long.valueOf(numero));
        return formato.format(date).toUpperCase();
    }

    /**
     * Converte a data recebida como parâmetro (no formato recebido pelo JSON do provider ex:
     * /Date(1492992000000+0000)/) para o formato date formatado (dd/MM/yyyy)
     *
     * @param data data no formato recebido pelo JSON do provider ex: /Date(1492992000000+0000)/
     * @return data formatada para dd/MM/yyyy
     */
    public static String convertDataJSONToDate(String data) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String numero = "";
        try {
            numero = data.substring(data.indexOf('(') + 1, data.lastIndexOf('-'));
        } catch (Exception e) {
            numero = data.substring(data.indexOf('(') + 1, data.indexOf('+'));
        }
        Date date = new Date(Long.valueOf(numero));
        return formato.format(date).toUpperCase();

    }

    /**
     * Converte a data recebida (yyyy-MM-dd) como parâmetro (no formato recebido pelo provideer ex:
     * (1997-14-08) para o formato (dd/MM/yyyy)
     *
     * @param data data no formato recebido pelo provider ex: (1997-08-14)
     * @return data formatada para dd/MM/yyyy
     */

    public static String convertYMDToDMY(String data) {
        if (!data.equals("") && data != null && (data.length() >= 10)) {
            String dia = "";
            try {
                dia = data.substring(data.lastIndexOf("-") + 1, data.lastIndexOf("-") + 3);
            } catch (Exception e) {
                dia = data.substring(data.lastIndexOf("-") + 1);
            }
            String mes = data.substring(data.indexOf("-", data.indexOf('-')) + 1, data.lastIndexOf("-"));
            String ano = data.substring(0, data.indexOf('-'));

            String newData = dia + "/" + mes + "/" + ano;
            return newData;
        }
        return null;
    }

    /**
     * Converte a data recebida (dd/MM/yyyy) como parâmetro (no formato recebido pelo provider ex:
     * (08/14/1997) para o formato (yyyy-MM-dd)
     *
     * @param data data no formato recebido pelo provider ex: (08/14/1997)
     * @return data formatada para yyyy-MM-dd
     */

    public static String convertDMYToYMD(String data) {
        String dia = data.substring(0, data.indexOf('/'));
        String mes = data.substring(data.indexOf("/", data.indexOf('/')) + 1, data.lastIndexOf("/"));
        String ano = data.substring(data.lastIndexOf("/") + 1);

        String newData = ano + "-" + mes + "-" + dia;
        return newData;
    }

    /**
     * Converte uma data no formato YYYY-MM-DDT00:00:00-03:00 para dd/MM/yyyy
     *
     * @param date data no formato YYYY-MM-DDT00:00:00-03:00
     * @return data formatada para dd/MM/yyyy
     */
    public static String convertDateTimeZoneToDMY(String date) {
        if (!date.equals("") && date != null && (date.length() >= 10)) {
            String dia = date.substring(8, 10);
            String mes = date.substring(5, 7);
            String ano = date.substring(0, 4);
            return dia + "/" + mes + "/" + ano;
        }
        return null;
    }

    /**
     * Converte uma data no formato DDMMYYYY para dd/MM/yyyy
     *
     * @param date data no formato DDMMYYYY
     * @return data formatada para dd/MM/yyyy
     */
    public static String convertDMYJToDMY(String date) {
        if (!date.equals("") && date != null && (date.length() >= 8)) {
            String dia = date.substring(0, 2);
            String mes = date.substring(2, 4);
            String ano = date.substring(4, 8);
            return dia + "/" + mes + "/" + ano;
        }
        return null;
    }

    public static String getFaixaIdade(String data) {
        int idade = getAge(data);

        if (idade >= 0 && idade <= 14)
            return "0 a 14 anos";
        else if (idade >= 15 && idade <= 19)
            return "15 a 19 anos";
        else if (idade <= 24)
            return "20 a 24 anos";
        else if (idade <= 29)
            return "25 a 29 anos";
        else if (idade <= 34)
            return "30 a 34 anos";
        else if (idade <= 39)
            return "35 a 39 anos";
        else if (idade <= 44)
            return "40 a 44 anos";
        else if (idade <= 44)
            return "40 a 44 anos";
        else if (idade <= 49)
            return "45 a 49 anos";
        else if (idade <= 54)
            return "50 a 54 anos";
        else if (idade <= 59)
            return "55 a 59 anos";
        else if (idade <= 64)
            return "60 a 64 anos";
        else if (idade <= 69)
            return "65 a 69 anos";
        else if (idade <= 74)
            return "70 a 74 anos";
        else if (idade <= 79)
            return "75 a 79 anos";
        else if (idade <= 84)
            return "80 a 84 anos";
        else if (idade <= 89)
            return "85 a 89 anos";
        else if (idade <= 94)
            return "90 a 94 anos";
        else if (idade <= 99)
            return "95 a 99 anos";
        else if (idade >= 100)
            return "Mais de 100 anos";
        else
            return "Data de Nascimento não Fornecida";

    }

    /**
     * Recupera o número do mês da data recebida (dd/MM/yyyy) como parâmetro.
     *
     * @param data
     * @return
     */
    public static Integer getMonthInt(String data) {
        if (StringUtils.isNullOrEmpty(data) || data.length() < 6)
            return null;
        return Integer
                .parseInt(data.substring(data.indexOf("/", data.indexOf('/')) + 1, data.lastIndexOf("/")));
    }

    /**
     * Recupera o nome do mês da data recebida (dd/MM/yyyy) como parâmetro.
     *
     * @param mes
     * @return
     */
    public static String getMonthName(Integer mes) {
        switch (mes) {
            case 1:
                return "Jan";
            case 2:
                return "Fev";
            case 3:
                return "Mar";
            case 4:
                return "Abr";
            case 5:
                return "Mai";
            case 6:
                return "Jun";
            case 7:
                return "Jul";
            case 8:
                return "Ago";
            case 9:
                return "Set";
            case 10:
                return "Out";
            case 11:
                return "Nov";
            case 12:
                return "Dez";
            default:
                return null;
        }
    }

}
