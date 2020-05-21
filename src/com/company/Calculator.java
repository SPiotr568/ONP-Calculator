/*
Project calculator
Created by Piotr Sularz
Date: 18.05.2020
*/

package com.company;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.EmptyStackException;
import java.util.EventListener;
import java.util.Iterator;
import java.util.LinkedList;

public class Calculator extends JFrame implements Serializable {
    private JButton b0,b1,b2,b3,b4,b5,b6,b7,b8,b9,bResult,bAddition,bSubtraction,bDivision,bMultiplication,bReset,bDot,
                    bModulo,bFactorial,bSqrt,bAttraction,bDelete,bMemory,bBracketLeft,bBracketRight;
    private JFrame window;
    private static JTextField text;
    private final int numberOfButton=17;
    private static final int windowWidth = 500;
    private static final int widowHeight = 400;
    private static int locationWidth = 0;
    private static int locationHeight = 0;
    private static int buttonWidth = 300;
    private static int buttonHeight = 200;

    /*

        +---------------------+   y:
        |+-------------------+|
        ||       rownanie    ||
        |+-------------------+|
        |   C   <-  M   *   ^ |    1
        |   7   8   9   /   R |    2
        |   4   5   6   -   % |    3
        |   1   2   3   +   ! |    4
        |   0   .   =   (   ) |    5
        +---------------------+
      x:    1   2   3   4   5

        C - reset
        <- - kasowanie jednego znaku
        % - dzielenie modulo
        R - pierwiastkowanie (root extraction)
            zapis:
            aRb pierwiastek stopnia b z a
        ^ - potegowanie
        / - dzielenie
        M - memory (odczytanie ostatniego oblicznonego wyrazenia)
        po obliczeniu wyniku zostaje on (wyrazenie + wyrazenie ONP + wynik) zapisany do pliku za pomoca serializacja

    */

    public Calculator(){
        calculateScreensSize();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window = new JFrame("RPN CalculatorApp");
        window.setSize(windowWidth, widowHeight);
        window.setLocation(locationWidth, locationHeight);
        window.setMinimumSize(new Dimension(350,300));
        window.setBackground(Color.black);
        window.setLayout(new BorderLayout());

        text = new JTextField(30);
        text.setFont(new Font("Arial", Font.PLAIN, 35));
        text.setBackground(Color.white);
        text.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        text.setEditable(false);

        window.add(text,BorderLayout.PAGE_START);

        /*try {
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(5, 5,5,5));
        buttons.setBackground(Color.darkGray);
        buttons.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        Font buttonFont = new Font("Arial", Font.PLAIN, 30);

        bReset = new JButton("C");
        bReset.setFont(buttonFont);
        bReset.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttons.add(bReset);
        bReset.addActionListener(new ButtonAction());

        bDelete = new JButton("<-");
        bDelete.setFont(buttonFont);
        bDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttons.add(bDelete);
        bDelete.addActionListener(new ButtonAction());

        bMemory = new JButton("M");
        bMemory.setFont(buttonFont);
        bMemory.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttons.add(bMemory);
        bMemory.addActionListener(new ButtonAction());

        bMultiplication = new JButton("*");
        bMultiplication.setFont(buttonFont);
        bMultiplication.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttons.add(bMultiplication);
        bMultiplication.addActionListener(new ButtonAction());

        bAttraction = new JButton("^");
        bAttraction.setFont(buttonFont);
        bAttraction.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttons.add(bAttraction);
        bAttraction.addActionListener(new ButtonAction());

        b7 = new JButton("7");
        b7.setFont(buttonFont);
        b7.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttons.add(b7);
        b7.addActionListener(new ButtonAction());

        b8 = new JButton("8");
        b8.setFont(buttonFont);
        b8.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttons.add(b8);
        b8.addActionListener(new ButtonAction());

        b9 = new JButton("9");
        b9.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b9.setFont(buttonFont);
        buttons.add(b9);
        b9.addActionListener(new ButtonAction());

        bDivision = new JButton("/");
        bDivision.setFont(buttonFont);
        bDivision.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttons.add(bDivision);
        bDivision.addActionListener(new ButtonAction());

        bSqrt = new JButton("R");
        bSqrt.setFont(buttonFont);
        bSqrt.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttons.add(bSqrt);
        bSqrt.addActionListener(new ButtonAction());

        b4 = new JButton("4");
        b4.setFont(buttonFont);
        b4.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttons.add(b4);
        b4.addActionListener(new ButtonAction());

        b5 = new JButton("5");
        b5.setFont(buttonFont);
        b5.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttons.add(b5);
        b5.addActionListener(new ButtonAction());

        b6 = new JButton("6");
        b6.setFont(buttonFont);
        b6.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttons.add(b6);
        b6.addActionListener(new ButtonAction());

        bSubtraction = new JButton("-");
        bSubtraction.setFont(buttonFont);
        bSubtraction.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttons.add(bSubtraction);
        bSubtraction.addActionListener(new ButtonAction());

        bModulo=new JButton("%");
        bModulo.setFont(buttonFont);
        bModulo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttons.add(bModulo);
        bModulo.addActionListener(new ButtonAction());

        b1 = new JButton("1");
        b1.setFont(buttonFont);
        b1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttons.add(b1);
        b1.addActionListener(new ButtonAction());

        b2 = new JButton("2");
        b2.setFont(buttonFont);
        b2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttons.add(b2);
        b2.addActionListener(new ButtonAction());

        b3 = new JButton("3");
        b3.setFont(buttonFont);
        b3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttons.add(b3);
        b3.addActionListener(new ButtonAction());

        bAddition = new JButton("+");
        bAddition.setFont(buttonFont);
        bAddition.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttons.add(bAddition);
        bAddition.addActionListener(new ButtonAction());

        bFactorial=new JButton("!");
        bFactorial.setFont(buttonFont);
        bFactorial.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttons.add(bFactorial);
        bFactorial.addActionListener(new ButtonAction());

        b0 = new JButton("0");
        b0.setFont(buttonFont);
        b0.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttons.add(b0);
        b0.addActionListener(new ButtonAction());

        bDot = new JButton(".");
        bDot.setFont(buttonFont);
        bDot.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttons.add(bDot);
        bDot.addActionListener(new ButtonAction());

        bResult = new JButton("=");
        bResult.setFont(buttonFont);
        bReset.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttons.add(bResult);
        bResult.addActionListener(new ButtonAction());

        bBracketLeft = new JButton("(");
        bBracketLeft.setFont(buttonFont);
        bBracketLeft.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttons.add(bBracketLeft);
        bBracketLeft.addActionListener(new ButtonAction());

        bBracketRight = new JButton(")");
        bBracketRight.setFont(buttonFont);
        bBracketRight.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttons.add(bBracketRight);
        bBracketRight.addActionListener(new ButtonAction());


        window.add(buttons);
        window.setResizable(true);
        window.setVisible(true);
    }

    public void calculateScreensSize(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        locationWidth = screenSize.width/2 - windowWidth/2;
        locationHeight = screenSize.height/2 - widowHeight/2;
    }

    public static JTextField getTextField(){
        return text;
    }

    public static void calculateEquation(){
        //przesylanie wyrazenia do obliczenia i wyswietlenie wyniku
        ONP onp = new ONP();
        String rownanieOnp = null;
        String wynik = null;
        String rownanie=text.getText();
        try {
            rownanieOnp = onp.przeksztalcNaOnp(rownanie);
            System.out.println(rownanieOnp);
            wynik = onp.obliczOnp(rownanieOnp);
        } catch(IllegalArgumentException e) {
            //obsluga wyjatkow zwiazanych z wyjsciem poza zakres tablicy
            System.out.println(e.getMessage());
            System.exit(0);
        } catch(ArrayIndexOutOfBoundsException arre) {
            //obsluga wyjatkow zwiazanych z wyjsciem poza zakres tablicy
            System.out.println(arre.getMessage());
            System.exit(0);
        } catch(Exception e) {
            //obsluga wyjatkow innych
            System.out.println(e.getMessage());
            System.exit(0);
        }
        text.setFont(new Font("Arial", Font.PLAIN, 20));
        text.setText(text.getText() + rownanieOnp + wynik);
        text.repaint();
        text.setBackground(Color.green);
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();
    }
}
