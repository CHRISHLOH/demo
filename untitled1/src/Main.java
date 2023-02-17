import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        System.out.println("Введите выражение");
        String value = s.nextLine();
        try {
            System.out.println(calc(value));
        } catch (Exceptions e) {
            throw new RuntimeException(e);
        }
    }

    public static String calc(String input) throws Exceptions {
        String[] arr = input.split(" ");
        if (arr.length != 3){
            throw new Exceptions("Неверное выражение");
        }
        String[] rimArr = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        String[] arArr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String[] signs = {"+", "-", "*", "/"};
        int check = 0;
        for (String a: signs) {
            if (a.equals(arr[1])) {
                check += 1;
            }
        }
        if(check == 0){
            throw new Exceptions("Неверное выражение");
        }
        int rimNum1 = 0;
        int rimNum2 = 0;
        int arNum1 = 0;
        int arNum2 = 0;
        for(int i = 0; i < rimArr.length; i++){
            if(rimArr[i].equals(arr[0])){
                rimNum1 += i + 1;}
        }
        for(int i = 0; i < rimArr.length; i++){
            if(rimArr[i].equals(arr[2])){
                rimNum2 += i + 1;}
        }
        for(int i = 0; i < arArr.length; i++){
            if (arArr[i].equals(arr[0])){
                arNum1 += i + 1;}
        }
        for(int i = 0; i < arArr.length; i++){
            if (arArr[i].equals(arr[2])){
                arNum2 += i + 1;}
        }
        String sign = arr[1];
        int res;
        String resRim ;
        String result = "";

        if (!(rimNum1 > 0 && rimNum2 > 0) && !(arNum1 > 0 && arNum2 > 0)){
            throw new Exceptions("Неверное выражение");}

        if (rimNum1 > 0 && rimNum2 > 0) {
            res = value(rimNum1, rimNum2, sign);
            resRim = ArToRim(res);
            result = resRim;
        }

        if (arNum1 > 0 && arNum2 > 0){
            res = value(arNum1, arNum2, sign);
            result = String.valueOf(res);
        }
        return result;
    }


    static int value(int num1,int num2, String sign){
        return switch (sign) {
            case "+" -> num1 + num2;
            case "-" -> num1 - num2;
            case "/" -> num1 / num2;
            default -> num1 * num2;
        };
    }
    static String ArToRim(int res) throws Exceptions{
        if(res < 1){
            throw new Exceptions("Неверное выражение");
        }
        String [] rimArr = {"","I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        String total = "";
        if (res < 11){
            total += rimArr[res];
        }
        if (res < 40 && res > 10) {
            int ost = res % 10;
            int x = res - ost;
            int in = x / 10;
            for(int i = 0;  i < in; i++){
                total += "X";
            }
            total += rimArr[ost];
        }
        if(res > 39 && res <50){
            int ost = res % 10;
            total += "X" + "L" + rimArr[ost];
        }
        if (res > 49 && res < 90){
            int ost = res % 10;
            int x = res - ost;
            int in = x / 10 - 5;
            total = "L";
            for(int i = 0;  i < in; i++){
                total += "X";}
            total += rimArr[ost];
        }
        if(res > 89 && res <100){
            int ost = res % 10;
            total += "X" + "C" + rimArr[ost];
        }
        if(res == 100){
            total += "C";
        }
        return total;
    }
}



