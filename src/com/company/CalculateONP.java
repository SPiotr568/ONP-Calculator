package com.company;

public class CalculateONP {
    private Stack<String> stack;
    private String functionONP;

    CalculateONP(){
        stack=new Stack<String>(100);
    }

    /*
    Metoda obliczajaca wynik wyrazenia ONP
     */
    public String calculate(String functionONP) throws Exception {
        this.functionONP=functionONP;
        String result = "";
        Double x = 0.0;
        Double y = 0.0;
        String nextChar = "";
        String newChar = "";
        for (int i = 0; i < functionONP.length(); i++) {
            newChar="" + functionONP.charAt(i);
            if(i < functionONP.length()-1){
                nextChar ="" + functionONP.charAt(i+1);
            }
            if (isDigit(i) || newChar.equals(".")) {
                result += functionONP.charAt(i);
                if (!(isDigit(i+1) || nextChar.equals("."))) {
                    stack.push(result);
                    result = "";
                }
            }
            else if (newChar.equals("=")) {
                return stack.pop();
            }
            else if (!newChar.equals(" ")) {
                switch (newChar) {
                    case ("+"): {
                        y = Double.parseDouble(stack.pop());
                        x = Double.parseDouble(stack.pop());
                        stack.push(String.valueOf(Dodawanie.oblicz(x,y)));
                        break;
                    }
                    case ("-"): {
                        y = Double.parseDouble(stack.pop());
                        x = Double.parseDouble(stack.pop());
                        stack.push(String.valueOf(Odejmowanie.oblicz(x,y)));
                        break;
                    }
                    case ("*"): {
                        y = Double.parseDouble(stack.pop());
                        x = Double.parseDouble(stack.pop());
                        stack.push(String.valueOf(Mnozenie.oblicz(x,y)));
                        break;
                    }
                    case ("/"): {
                        y = Double.parseDouble(stack.pop());
                        x = Double.parseDouble(stack.pop());
                        if (y.equals(0)) {
                            throw new java.lang.IllegalArgumentException("Nie można dzielic przez 0!");
                        }
                        stack.push(String.valueOf(Dzielenie.oblicz(x,y)));
                        break;
                    }
                    case ("^"): {
                        y = Double.parseDouble(stack.pop());
                        x = Double.parseDouble(stack.pop());
                        stack.push(String.valueOf(Potegowanie.obilcz(x,y)));
                        break;
                    }
                    case ("R"): {
                        int stopien = (int) Double.parseDouble(nextChar);
                        i++;//pomija kolejny znak bo pobral sobie stopien pierwiastka
                        x = Double.parseDouble(stack.pop());
                        if(stopien==0) {
                            throw new IllegalArgumentException("Blad! Stopien nie moze wynosic 0");
                        }
                        else if(stopien%2==0) {
                            if(x<0) {
                                throw new IllegalArgumentException("Blad! Podstawa jest ujemna zas stopien jest parzysty!");
                            }
                        }
                        stack.push(String.valueOf(Pierwiastkowanie.oblicz(x,stopien)));
                        break;
                    }
                    case ("%"): {
                        y = Double.parseDouble(stack.pop());
                        x = Double.parseDouble(stack.pop());
                        if (y <= 0) {
                            throw new java.lang.IllegalArgumentException("Nie moze dzielic module przez liczbe<=0");
                        }
                        stack.push(String.valueOf(Modulo.oblicz(x,y)));
                        break;
                    }
                    case ("!"): {
                        int s = Integer.parseInt(stack.pop());
                        if (x < 0) {
                            throw new java.lang.IllegalArgumentException("Nie można uzyc silni dla liczby mniejszej od 0");
                        }
                        stack.push(String.valueOf(Silnia.silnia(s)));
                        break;
                    }
                }
            }
        }
        return "0.0";
    }
    public boolean isDigit(int position){
        if(functionONP.charAt(position) >= '0' && functionONP.charAt(position) <= '9'){
            return true;
        }
        return false;
    }
}
