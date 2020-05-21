package com.company;


public class ONP {
    private Stack<String> stack = new Stack<>(100);

    boolean czyPoprawneRownanie(String rownanie) {
        if (rownanie.endsWith("="))
            return true;
        else
            return false;
    }


    private static double silnia(double i) {
        if (i < 1) {
            return 1;
        } else {
            return i * silnia(i - 1);
        }
    }

    public String obliczOnp(String rownanie) throws Exception {
        if (czyPoprawneRownanie(rownanie)) {
            stack.setSize(0);
            String wynik = "";
            Double a = 0.0;

            Double b = 0.0;
            for (int i = 0; i < rownanie.length(); i++) {
                if (rownanie.charAt(i) >= '0' && rownanie.charAt(i) <= '9') {
                    wynik += rownanie.charAt(i);
                    if (!(rownanie.charAt(i + 1) >= '0' && rownanie.charAt(i + 1) <= '9')) {
                        stack.push(wynik);
                        wynik = "";
                    }

                } else if (rownanie.charAt(i) == '=') {
                    return stack.pop();
                } else if (rownanie.charAt(i) != ' ') {
                    b = Double.parseDouble(stack.pop());
                    a = Double.parseDouble(stack.pop());
                    switch (rownanie.charAt(i)) {
                        case ('+'): {
                            stack.push((a + b) + "");
                            break;
                        }
                        case ('-'): {
                            stack.push((a - b) + "");
                            break;
                        }
                        case ('x'):
                            ;
                        case ('*'): {
                            stack.push((a * b) + "");
                            break;
                        }
                        case ('/'): {
                            try {
                                if (b.equals(0))
                                    throw new java.lang.IllegalArgumentException("Nie można dzielic przez 0");
                            } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                                System.exit(0);
                            }
                            stack.push((a / b) + "");
                            break;
                        }
                        case ('^'): {
                            stack.push(Math.pow(a, b) + "");
                            break;
                        }
                        case ('V'): {//dodane dla pierwiastkowania
                            stack.push(b + "");
                            try {
                                if (a < 0)
                                    throw new java.lang.IllegalArgumentException("Nie można otrzymac pierwisatka z liczby ujemnej");
                            } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                                System.exit(0);
                            }
                            stack.push(java.lang.Math.sqrt(a) + "");
                            break;
                        }
                        case ('%'): {//dodane dla dzielenia modulo
                            try {
                                if (b <= 0)
                                    throw new java.lang.IllegalArgumentException("B nie moze byc w wyrazeniu a%b mniejsze badz rowne 0");
                            } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                                System.exit(0);
                            }
                            stack.push((a % b) + "");
                            break;
                        }
                        case ('!'): {//dodane dla silnii (wyzej metoda silnia)
                            try {
                                if (a <= 0)
                                    throw new java.lang.IllegalArgumentException("Nie można uzyc silni dla liczby <=0");
                            } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                                System.exit(0);
                            }
                            stack.push(silnia(a) + "");
                            break;
                        }
                    }
                }
            }
            return "0.0";
        } else {
            return "Brak '='";
        }

    }


    public String przeksztalcNaOnp(String rownanie) throws Exception {
        if (czyPoprawneRownanie(rownanie)) {
            String wynik = "";
            for (int i = 0; i < rownanie.length(); i++) {
                if (rownanie.charAt(i) >= '0' && rownanie.charAt(i) <= '9') {
                    wynik += rownanie.charAt(i);
                    if (!(rownanie.charAt(i + 1) >= '0' && rownanie.charAt(i + 1) <= '9'))
                        wynik += " ";
                } else
                    switch (rownanie.charAt(i)) {
                        case ('+'):
                            ;
                        case ('-'): {
                            while (stack.getSize() > 0 && !stack.showValue(stack.getSize() - 1).equals("(")) {
                                wynik = wynik + stack.pop() + " ";
                            }
                            String str = "" + rownanie.charAt(i);
                            stack.push(str);
                            break;
                        }
                        case ('x'):
                            ;
                        case ('*'):
                            ;
                        case ('/'): {
                            while (stack.getSize() > 0 && !stack.showValue(stack.getSize() - 1).equals("(")
                                    && !stack.showValue(stack.getSize() - 1).equals("+")
                                    && !stack.showValue(stack.getSize() - 1).equals("-")) {
                                wynik = wynik + stack.pop() + " ";
                            }
                            String str = "" + rownanie.charAt(i);
                            stack.push(str);
                            break;
                        }
                        case ('^'): {
                            while (stack.getSize() > 0 && stack.showValue(stack.getSize() - 1).equals("^")) {
                                wynik = wynik + stack.pop() + " ";
                            }
                            String str = "" + rownanie.charAt(i);
                            stack.push(str);
                            break;
                        }
                        case ('('): {
                            String str = "" + rownanie.charAt(i);
                            stack.push(str);
                            break;
                        }
                        case (')'): {
                            while (stack.getSize() > 0 && !stack.showValue(stack.getSize() - 1).equals("(")) {
                                wynik = wynik + stack.pop() + " ";
                            }
                            // zdjęcie ze stosu znaku (
                            stack.pop();
                            break;
                        }
                        case ('='): {
                            while (stack.getSize() > 0) {
                                wynik = wynik + stack.pop() + " ";
                            }
                            wynik += "=";
                        }
                    }
            }
            return wynik;
        } else
            return "null";
    }
}