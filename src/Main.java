public class Main {
    public static void main(String[] args) throws CalculusSystemBaseException, CalculusSystemNumberException, CalculusSystemSymbolsAfter36Exception {
        System.out.println("Проверка простых математических операция");
        CalculusSystem cs1 = new CalculusSystem("0110101100011010", 2);
        CalculusSystem cs2 = new CalculusSystem("1010011100101110", 2);
        CalculusSystem cs3 = new CalculusSystem(cs1.getNumber() + cs2.getNumber());
        System.out.println(cs3.conversation(2));
        System.out.println();

        System.out.println("Проверка работы в 10 системе счисления");
        CalculusSystem example1 = new CalculusSystem(2683);
        System.out.println(example1.conversation(2));
        System.out.println(example1.conversation(16));

        System.out.println();
        System.out.println("Проверка работы в системе счисления с основанием отличным от 10");
        CalculusSystem example2 = new CalculusSystem("101001111011", 2);
        System.out.println(example2.conversation(10));
        System.out.println(example2.conversation(16));

        System.out.println();
        System.out.println("Проверка работы системы счисления с основанием большем 10");
        CalculusSystem example3 = new CalculusSystem("a7B", 16);
        System.out.println(example3.conversation(10));
        System.out.println(example3.conversation(2));

        System.out.println();
        System.out.println("Проверка работы сеттеров и геттеров");
        example3.set("ABC", 16);
        System.out.println(example3.getNumber());
        example3.set(1095);
        System.out.println(example3.getNumber());
        example3.set("$2@!", 40, "!@#$");
        System.out.println(example3.getNumber());

        System.out.println();
        System.out.println("Проверка работы в системе с основанием большим 36");
        CalculusSystem example4 = new CalculusSystem("$2!", 40, "!@#$");
        System.out.println(example4.conversation(10));
        CalculusSystem example5 = new CalculusSystem("$2!", 40, "!@#$");
        System.out.println(example5.conversation(16));
        CalculusSystem example6 = new CalculusSystem("f434", 16);
        System.out.println(example6.conversation(40, "!@#$"));
        CalculusSystem example7 = new CalculusSystem("$2!", 40, "!@#$");
        System.out.println(example7.conversation(44, "!@#$%^&*"));
        CalculusSystem example8 = new CalculusSystem("WC!", 44, "!@#$%^&*");
        System.out.println(example8.conversation(40, "!@#$"));
        System.out.println("Проверка прошла успешно");
    }
}
