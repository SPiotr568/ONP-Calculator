package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class ButtonAction extends JButton implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        Calculator.getTextField();
        switch (s){
            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
                if(checktIfCorrect(s)){
                    Calculator.getTextField().repaint();
                    Calculator.getTextField().setBackground(Color.white);
                    Calculator.getTextField().setText(Calculator.getTextField().getText() + s);
                }
                else {
                    Calculator.getTextField().repaint();
                    Calculator.getTextField().setBackground(Color.red);
                }
                break;
            case "C":
                Calculator.getTextField().setText("");
                Calculator.getTextField().repaint();
                Calculator.getTextField().setBackground(Color.white);
                Calculator.getTextField().setFont(new Font("Arial", Font.PLAIN, 35));
                break;
            case "<-":
                String newText = Calculator.getTextField().getText();
                if(newText.length()!=0){
                    newText=newText.substring(0, newText.length()-1);
                    Calculator.getTextField().setText(newText);
                }
                break;
            case "M":
                try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("calcStorage.bin"))) {
                    String mem=(String) input.readObject();
                    Calculator.getTextField().setText(mem);
                    Calculator.getTextField().repaint();
                    Calculator.getTextField().setBackground(Color.darkGray);
                    Calculator.getTextField().setFont(new Font("Arial", Font.PLAIN, 20));
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
                break;
            case "*":
                if(checktIfCorrect(s)){
                    Calculator.getTextField().repaint();
                    Calculator.getTextField().setBackground(Color.white);
                    Calculator.getTextField().setText(Calculator.getTextField().getText() + s);
                }
                else {
                    Calculator.getTextField().repaint();
                    Calculator.getTextField().setBackground(Color.red);
                }
                break;
            case "^":
                if(checktIfCorrect(s)){
                    Calculator.getTextField().repaint();
                    Calculator.getTextField().setBackground(Color.white);
                    Calculator.getTextField().setText(Calculator.getTextField().getText() + s);
                }
                else {
                    Calculator.getTextField().repaint();
                    Calculator.getTextField().setBackground(Color.red);
                }
                break;
            case "/":
                if(checktIfCorrect(s)){
                    Calculator.getTextField().repaint();
                    Calculator.getTextField().setBackground(Color.white);
                    Calculator.getTextField().setText(Calculator.getTextField().getText() + s);
                }
                else {
                    Calculator.getTextField().repaint();
                    Calculator.getTextField().setBackground(Color.red);
                }
                break;
            case "R":
                if(checktIfCorrect(s)){
                    Calculator.getTextField().repaint();
                    Calculator.getTextField().setBackground(Color.white);
                    Calculator.getTextField().setText(Calculator.getTextField().getText() + s);
                }
                else {
                    Calculator.getTextField().repaint();
                    Calculator.getTextField().setBackground(Color.red);
                }
                break;
            case "-":
                if(checktIfCorrect(s)){
                    Calculator.getTextField().repaint();
                    Calculator.getTextField().setBackground(Color.white);
                    Calculator.getTextField().setText(Calculator.getTextField().getText() + s);
                }
                else {
                    Calculator.getTextField().repaint();
                    Calculator.getTextField().setBackground(Color.red);
                }
                break;
            case "%":
                if(checktIfCorrect(s)){
                    Calculator.getTextField().repaint();
                    Calculator.getTextField().setBackground(Color.white);
                    Calculator.getTextField().setText(Calculator.getTextField().getText() + s);
                }
                else {
                    Calculator.getTextField().repaint();
                    Calculator.getTextField().setBackground(Color.red);
                }
                break;
            case "+":
                if(checktIfCorrect(s)){
                    Calculator.getTextField().repaint();
                    Calculator.getTextField().setBackground(Color.white);
                    Calculator.getTextField().setText(Calculator.getTextField().getText() + s);
                }
                else {
                    Calculator.getTextField().repaint();
                    Calculator.getTextField().setBackground(Color.red);
                }
                break;
            case "!":
                if(checktIfCorrect(s)){
                    Calculator.getTextField().repaint();
                    Calculator.getTextField().setBackground(Color.white);
                    Calculator.getTextField().setText(Calculator.getTextField().getText() + s);
                }
                else {
                    Calculator.getTextField().repaint();
                    Calculator.getTextField().setBackground(Color.red);
                }
                break;
            case ".":
                if(checktIfCorrect(s)){
                    Calculator.getTextField().repaint();
                    Calculator.getTextField().setBackground(Color.white);
                    Calculator.getTextField().setText(Calculator.getTextField().getText() + s);
                }
                else {
                    Calculator.getTextField().repaint();
                    Calculator.getTextField().setBackground(Color.red);
                }
                break;
            case "=":
                if(checktIfCorrect(s)) {
                    Calculator.getTextField().repaint();
                    Calculator.getTextField().setBackground(Color.white);
                    Calculator.getTextField().setText(Calculator.getTextField().getText() + s);
                }
                Calculator.calculateEquation();
                //serializacja,zapisuje do pliku calcStorage.bin ostatni wynik
                try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("calcStorage.bin"))) {
                    output.writeObject(Calculator.getTextField().getText()+ "\n");
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                break;
            case "(":
                if(checktIfCorrect(s)){
                    Calculator.getTextField().repaint();
                    Calculator.getTextField().setBackground(Color.white);
                    Calculator.getTextField().setText(Calculator.getTextField().getText() + s);
                }
                else {
                    Calculator.getTextField().repaint();
                    Calculator.getTextField().setBackground(Color.red);
                }
                break;
            case ")":
                if(checktIfCorrect(s)){
                    Calculator.getTextField().repaint();
                    Calculator.getTextField().setBackground(Color.white);
                    Calculator.getTextField().setText(Calculator.getTextField().getText() + s);
                }
                else {
                    Calculator.getTextField().repaint();
                    Calculator.getTextField().setBackground(Color.red);
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + s);
        }
    }

    public boolean checktIfCorrect(String toAdd){
        char [] digit = {'0','1','2','3','4','5','6','7','8','9'};
        char [] sign = {'*','^','/','-','%','+','!'};
        int nLeftBrucket = 0;
        int nRightBrucket = 0;
        char newChar=toAdd.charAt(0);
        String str = Calculator.getTextField().getText();
        if(str.length()==0){
            for (char d:digit){
                if (newChar==d || newChar=='('){
                    return true;
                }

            }
        }
        else {
            char lastChar = str.charAt(str.length()-1);
            for (char d:digit) {
                for (char s:sign) {
                    if(lastChar==d && newChar==s){
                        return true;
                    }
                    else if(lastChar==d && newChar=='.'){
                        return true;
                    }
                    else if(lastChar=='!' && newChar=='='){
                        return true;
                    }
                    else if(lastChar=='.' && newChar==d){
                        return true;
                    }
                    else if(lastChar=='!' && newChar==s){
                       return true;
                    }
                    else if(lastChar==s && newChar==d){
                        return true;
                    }
                    else if(lastChar==s &&  newChar=='='){
                        return false;
                    }
                    else if((lastChar==d || lastChar == ')') &&  newChar=='='){
                        return true;
                    }
                    else if(lastChar=='R' || newChar ==d){
                        return true;
                    }
                    else if(lastChar==d || newChar =='R'){
                        return true;
                    }
                    else if((lastChar==d || lastChar==')') && newChar==')'){
                        for(int i=0;i<str.length()-1;i++){
                            if(str.charAt(i)=='('){
                                nLeftBrucket++;
                            }
                            else if(str.charAt(i)==')'){
                                nRightBrucket++;
                            }
                        }
                        System.out.println(nLeftBrucket+":"+nRightBrucket);
                        if(nLeftBrucket>nRightBrucket) {
                            return true;
                        }
                    }
                    else if(lastChar==s && newChar=='('){
                        return true;
                    }
                    else if(lastChar=='(' && newChar==d){
                        if(newChar=='!'){
                            return false;
                        }
                        else {
                            return true;
                        }
                    }
                    else if(lastChar==')' && newChar==s){
                        return true;
                    }
                }
                for (char d2:digit){
                    if(lastChar==d && newChar==d2){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}