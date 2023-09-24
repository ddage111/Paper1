import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("На сколько человек делить счет?");
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");
        int count = 0;
        while(count <= 1){
            try {
                count = Integer.parseInt(scanner.next());
            }catch (NumberFormatException | InputMismatchException exception){
                System.out.println("Введены некоректные данные! Необходимо ввести целоечисленное значение");
                continue;
            }

            if(count == 1){
                System.out.println("Нечего делить. Количество должно быть больше 1");
            }else if (count < 1){
                System.out.println("Некорректный ввод. Количество должно быть больше 1");
            }else{
                break;
            }
        }
        Calculator calc = new Calculator();
        do{
            System.out.println("Введите наименование товара111");
            Goods goods = new Goods();
            goods.name = scanner.next();
            
            System.out.println("Введите стоимость товара");
            double price = 0;
            while(price <= 0){
                try {
                    price = Double.parseDouble(scanner.next());
                }catch(NumberFormatException | InputMismatchException exception){
                    System.out.println("Введены некоректные данные! Необходимо ввести число");
                    continue;
                }
            }

            goods.price = price;

            calc.addGoods(goods);

            System.out.println("Товар добавлен!");

            System.out.println("Для завершения введите слово ЗАВЕРШИТЬ. Чтобы продолжить введите любой символ");
            if(scanner.next().toUpperCase().equals("ЗАВЕРШИТЬ")){
                break;
            }
        }while(true);

        System.out.println("Добавленные товары: ");
        for (Goods goods: calc.goods) {
            System.out.println(goods.name);
        }
        double sumForEach = calc.sum / count;
        System.out.println(String.format("Общая сумма %.2f %s. Каждый должен заплатить %.2f %s",
                calc.sum, calc.getRubleAddition((int)calc.sum), sumForEach, calc.getRubleAddition((int)sumForEach)));
    }
}

class Calculator{
    double sum = 0;
    ArrayList<Goods> goods = new ArrayList<>();

    double getSum(){
        return sum;
    }

    void addGoods(Goods goods){
        this.goods.add(goods);
        this.addSum(goods.price);
    }

    void addSum(double sum){
        this.sum = this.sum + sum;
    }

    String getRubleAddition(int num){
        var preLastDigit = num % 100 / 10;
        if (preLastDigit == 1)
        {
            return "рублей";
        }

        switch (num % 10)
        {
            case 1:
                return "рубль";
            case 2:
            case 3:
            case 4:
                return "рубля";
            default:
                return "рублей";
        }
    }
}

class Goods{
    String name;
    double price;

    Goods(){

    }
    Goods(String name, double price){
        this.name = name;
        this.price = price;
    }
}