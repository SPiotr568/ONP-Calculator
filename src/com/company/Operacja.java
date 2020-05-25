package com.company;

public abstract class Operacja {
    public static Double oblicz(Double a, Double b) {
        return null;
    }
    public static Double oblicz(Double a){
        return null;
    }
}

interface Oblicz {
    Double obliczDzialanie(Double a, Double b);
}

class Dodawanie extends Operacja{
    static Oblicz dod = (Double a, Double b) -> a + b;
    public static Double oblicz(Double a, Double b) {
        return dod.obliczDzialanie(a, b);
    }
}

class Odejmowanie extends Operacja{
    static Oblicz odej = (Double a, Double b) -> a - b;
    public static Double oblicz(Double a, Double b) {
        return odej.obliczDzialanie(a, b);
    }
}

class Mnozenie extends  Operacja{
    static Oblicz pomnoz = (Double a, Double b) -> a * b;
    public static Double oblicz(Double a, Double b) {
        return pomnoz.obliczDzialanie(a, b);
    }
}

class Dzielenie extends  Operacja{
    static Oblicz podz = (Double a, Double b) -> a / b;
    public static Double oblicz(Double a, Double b) {
        return podz.obliczDzialanie(a, b);
    }
}

class Potegowanie extends Operacja{
    static Oblicz poteg = (Double a, Double b) -> Math.pow(a,b);
    public static Double obilcz(Double a,Double b){
        return poteg.obliczDzialanie(a,b);
    }
}

class Pierwiastkowanie extends  Operacja{
    public static Double oblicz(Double a,int b){
        if(b<0){
            return -Math.pow(-a,(double) 1/b);
        }
        else return Math.pow(a,(double) 1/b);
    }
}

class Modulo extends Operacja{
    static Oblicz mod = (Double a, Double b) -> a % b;
    public static Double oblicz(Double a, Double b) {
        return mod.obliczDzialanie(a, b);
    }
}

class Silnia extends Operacja{
    public static int silnia(int a){
        if (a < 1) {
            return 1;
        }
        else {
            return a * silnia(a - 1);
        }
    }
}

