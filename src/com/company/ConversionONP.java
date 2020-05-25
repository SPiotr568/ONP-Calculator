package com.company;

import java.util.HashMap;
import java.util.Map;

public class ConversionONP {
    private String startFunction;
    private String result;
    private Stack<String> stack;
    private Map<String,Integer> operationPriority;

    ConversionONP(){
        startFunction="";
        result="";
        stack=new Stack<String>(100);
        operationPriority=new HashMap<>();
        operationPriority.put("(", 0);
        operationPriority.put(".", 0);
        operationPriority.put("=", 0);
        operationPriority.put("-", 1);
        operationPriority.put("+", 1);
        operationPriority.put(")", 1);
        operationPriority.put("*", 2);
        operationPriority.put("/", 2);
        operationPriority.put("%", 2);
        operationPriority.put("^", 3);
        operationPriority.put("R", 3);
        operationPriority.put("!", 4);
    }

    /*
    Metoda do konwersji wyrazenia na ONP

    @param startFunction - wyrazenie przed konwersja
    @return zwraca wyrażenie ktore zostalo poddane transformacji na ONP
     */
    public String conversionToONP(String startFunction) {
        this.startFunction=startFunction;
        Double a = 0.0;
        Double b = 0.0;
        String newChar;
        for(int i=0; i<startFunction.length(); i++){
            newChar ="" + startFunction.charAt(i);
            String nextChar="";
            if(i < startFunction.length()-1){
                nextChar ="" + startFunction.charAt(i+1);
            }
            if (isDigit(i) || newChar.equals(".")){
                result+=newChar;
                if (!(isDigit(i+1) || nextChar.equals("."))) {
                    result += " ";
                }
            }
            else if(newChar.equals("R")){
                result+="R" + nextChar + " ";
                i++;
            }
            else if (startFunction.charAt(i)=='('){
                try {
                    stack.push(newChar);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    Calculator.getTextField().setText("ERROR!!!");
                }
            }
            else if (startFunction.charAt(i)==')'){
                while (!stack.isEmpty() && !stack.onTop().equals("(")){
                    result += stack.pop() + " ";
                }
                try {
                    stack.pop();
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                    Calculator.getTextField().setText("ERROR!!!");
                }
            }
            else {
                if(stack.isEmpty() || operationPriority.get(newChar) > operationPriority.get(stack.onTop())) {
                    try {
                        stack.push(newChar);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        Calculator.getTextField().setText("ERROR!!!");
                    }
                }
                else{
                    while(!stack.isEmpty() && operationPriority.get(stack.onTop()) >= operationPriority.get(newChar)){
                        result += stack.pop() + " ";
                    }
                    try {
                        stack.push(newChar);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        Calculator.getTextField().setText("ERROR!!!");
                    }
                }
            }
        }
        while (!stack.isEmpty()){
            result += stack.pop();
        }
        return result;
    }

    public boolean isDigit(int position){
        if(startFunction.charAt(position) >= '0' && startFunction.charAt(position) <= '9'){
            return true;
        }
        return false;
    }
}
