package com.company;

public abstract class Operacja {
    public Double oblicz() {
        return null;
    }
}

interface Oblicz {
    Double obliczDzialanie(Double a, Double b);
}

class Dodawanie extends Operacja implements Oblicz{
    Oblicz dod = (Double a, Double b) -> a + b;
    @Override
    public Double obliczDzialanie(Double a, Double b) {
        return dod.obliczDzialanie(a, b);
    }
}

class Odejmowanie extends Operacja implements Oblicz{
    Oblicz odej = (Double a, Double b) -> a - b;
    @Override
    public Double obliczDzialanie(Double a, Double b) {
        return odej.obliczDzialanie(a, b);
    }
}

class Mnozenie extends  Operacja implements Oblicz{
    Oblicz pomnoz = (Double a, Double b) -> a * b;
    @Override
    public Double obliczDzialanie(Double a, Double b) {
        return pomnoz.obliczDzialanie(a, b);
    }
}

class Dzielenie extends  Operacja implements Oblicz{
    Oblicz podz = (Double a, Double b) -> a / b;
    @Override
    public Double obliczDzialanie(Double a, Double b) {
        return podz.obliczDzialanie(a, b);
    }
}

