class CalculusSystem {

    private int number;
    final String symbolsAfter9 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public CalculusSystem (int number) throws CalculusSystemNumberException {
        if (number < 0) throw new CalculusSystemNumberException("Число не должно быть меньше 0");
        this.number = number;
    }

    public CalculusSystem (String number, int base) throws CalculusSystemBaseException, CalculusSystemNumberException {
        if (base <= 1 || base > 36) throw new CalculusSystemBaseException("База неверна");
        checkNumber(number, base, symbolsAfter9);
        this.number = inDecimal(number, base, "");
    }

    public CalculusSystem (String number, int base, String symbolsAfter36) throws CalculusSystemBaseException,
            CalculusSystemNumberException, CalculusSystemSymbolsAfter36Exception {
        if (base <= 1) throw new CalculusSystemBaseException("База неверна");
        checkNumber(number, base, symbolsAfter9 + symbolsAfter36);
        checkSymbolsAfter36(base, symbolsAfter36);
        this.number = inDecimal(number, base, symbolsAfter36);
    }

    public void set(int number) throws CalculusSystemNumberException {
        if (number < 0) throw new CalculusSystemNumberException("Число не должно быть меньше 0");
        this.number = number;
    }

    public void set(String number, int base) throws CalculusSystemNumberException, CalculusSystemBaseException {
        if (base <= 1 || base > 36) throw new CalculusSystemBaseException("База неверна");
        checkNumber(number, base, symbolsAfter9);
        this.number = inDecimal(number, base, "");
    }

    public void set (String number, int base, String symbolsAfter36) throws CalculusSystemBaseException, CalculusSystemSymbolsAfter36Exception, CalculusSystemNumberException {
        if (base <= 1) throw new CalculusSystemBaseException("База неверна");
        checkNumber(number, base, symbolsAfter9 + symbolsAfter36);
        checkSymbolsAfter36(base, symbolsAfter36);
        this.number = inDecimal(number, base, symbolsAfter36);
    }

    public int getNumber() {return number;}

    public String conversation(int base) {
        if (number == 0) return "0";
        int number = this.number;
        StringBuilder result = new StringBuilder();
        while (number > 0) {
            if (number % base < 10) result.append(number % base);
            else result.append(symbolsAfter9.charAt(number % base - 10));
            number /= base;
        }
        return result.reverse().toString();
    }

    //метод переводит число в систему счисления с основанием больше 37
    public String conversation(int base, String symbolsAfter36) throws CalculusSystemSymbolsAfter36Exception, CalculusSystemBaseException {
        if (base < 37) throw new CalculusSystemBaseException("Вам не нужно устанавливать больше символов в вашей системе");
        checkSymbolsAfter36(base, symbolsAfter36);
        if (number == 0) return "0";
        int number = this.number;
        StringBuilder result = new StringBuilder();
        while (number > 0) {
            if (number % base < 10) result.append(number % base);
            else result.append((symbolsAfter9 + symbolsAfter36).charAt(number % base - 10));
            number /= base;
        }
        return result.reverse().toString();
    }

    private int inDecimal(String number, int base, String symbolsAfter36) throws CalculusSystemNumberException {
        int numberInDecimal = 0;
        for (int i = 0; i < number.length(); i++) {
            double pow = Math.pow(base, number.length() - i - 1);
            if (charIsNumber(number.charAt(i)))
                numberInDecimal += (Integer.parseInt(String.valueOf(number.charAt(i))))
                        * pow;
            else if (symbolIsIncludedInString(number.charAt(i), (symbolsAfter9 + symbolsAfter36)))
                numberInDecimal += ((symbolsAfter9 + symbolsAfter36).indexOf(number.charAt(i)) + 10)
                        * pow;
            else throw new CalculusSystemNumberException("Вы ввели неверное число");
        }
        return numberInDecimal;
    }

    private boolean symbolIsIncludedInString(char symbol, String string) {
        for (int i = 0; i < string.length(); i++) {
            if (symbol == string.charAt(i) || symbol == string.toLowerCase().charAt(i)) return true;
        }
        return false;
    }

    private boolean charIsNumber(char symbol) {
        return symbol >= '0' && symbol <= '9';
    }

    private boolean stringHasSameSymbols(String string) {
        for (int i = 0; i < string.length() - 1; i++) {
            for (int j = i + 1; j < string.length(); j++) {
                if (string.charAt(i) == string.charAt(j)) return true;
            }
        }
        return false;
    }

    private void checkSymbolsAfter36(int base, String symbolsAfter36) throws CalculusSystemSymbolsAfter36Exception {
        if (symbolsAfter36.length() != base - 36) throw new CalculusSystemSymbolsAfter36Exception("Символы после 9 в вашей системе исчисления не подходят базе");
        if (symbolsAfter9.length() - 26 < symbolsAfter36.length()) {
            for (int i = symbolsAfter9.length() - 26; i < symbolsAfter36.length(); i++) {
                if (charIsNumber(symbolsAfter36.charAt(i)) ||
                        symbolIsIncludedInString(symbolsAfter36.charAt(i), symbolsAfter9))
                    throw new CalculusSystemSymbolsAfter36Exception("Число не допускаются");
            }
        }
        if (stringHasSameSymbols(symbolsAfter36)) {
            throw new CalculusSystemSymbolsAfter36Exception("После 36 не допускаются одинаковые символы");
        }
    }

    private void checkNumber(String number, int base, String symbolsAfter9) throws CalculusSystemNumberException {
        if (!numberIsSuitedBase(String.valueOf(number), base))
            throw new CalculusSystemNumberException("Число имеет символ больше, чем база");
        if (number.charAt(0) == '-') throw new CalculusSystemNumberException("Число не может быть меньше 0");
        for (int i = 0; i < number.length(); i++) {
            if (!charIsNumber(number.charAt(i)) && !symbolIsIncludedInString(number.charAt(i), symbolsAfter9))
                throw new CalculusSystemNumberException("Это число не разрешено");
        }
    }

    private boolean numberIsSuitedBase(String number, int base) {
        for (int i = 0; i < number.length(); i++) {
            if (charIsNumber(number.charAt(i))) {
                if (Integer.parseInt(String.valueOf(number.charAt(i))) >= base)
                    return false;
            }else {
                if (symbolsAfter9.indexOf(number.charAt(i) + 10) >= base)
                    return false;
            }
        }
        return true;
    }
}
