package ru.geeakbrains.lesson8;

import javafx.scene.layout.Border;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

//Это программка помогает посчитать сколько нужно набирать петель, если вяжешь шапку спицами:))))
//Логика такая:
// - вводим параметры (обхват головы и кол-во петель в образце)
// - далее выбираем каким узором вяжем шапку
// - программа выдает готовое число, при этом из полученного значения отнимает 10% (так как шапка в процессе носки немного растянется)
// затем проверяет в зависимости от узора, чтобы число делилось на 2 или 4 и при необходимости округляет в большую сторону.

// я столкнулась с такой проблемой, хотела чтобы JTextField был, как placeholder,
// когда устанавливаем курсор текст с описанием какие данные вводить пропадает и вводятся параметры.
// И вроде сделала, но на первую строку встает каретка и становится не видно какой там текст и соответственно что вводить.
// Поэтому я просто добавила еще одну строчку пустую (хоть это ужасно и неправильно)

public class HatSize {


    final static int SampleSize = 10;

    public static JTextField HeadSize;
    public static JTextField CountSample;
    public static JTextField CountHat;

    public static JRadioButton OneXOne;
    public static JRadioButton TwoXTwo;
    public static JRadioButton StSt;

    public static JButton Result;

    public static void main(String[] args) {
        JFrame HatSizeWindow = new JFrame("Сколько петель надо на шапку?");
        HatSizeWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        HatSizeWindow.setSize(400, 400);
        HatSizeWindow.setBackground(Color.gray);
        setNorth(HatSizeWindow);
        setCenter(HatSizeWindow);
        setSouth(HatSizeWindow);
        HatSizeWindow.setVisible(true);
        HatSizeWindow.pack();


    }

    public static void setNorth(JFrame fr) {
        JPanel NorthPanel = new JPanel();
        NorthPanel.setLayout(new GridLayout(4, 1));
        HeadSize = new JTextField("Введите обхват головы, в см");
        CountSample = new JTextField("Введите количество петель в образце 10х10 см");
        CountHat = new JTextField("Тут появится сколько петель нужно будет набрать!");
        CountHat.setBackground(Color.black);
        CountHat.setEnabled(false);
        JTextField a = new JTextField();
        a.setVisible(true);
        NorthPanel.add(a);
        NorthPanel.add(HeadSize);
        NorthPanel.add(CountSample);
        NorthPanel.add(CountHat);

        HeadSize.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                HeadSize.setText(null);
            }

            @Override
            public void focusLost(FocusEvent e) {

                if (HeadSize.getText().length() == 0)
                    HeadSize.setText("Введите обхват головы, в см");

            }
        });
        CountSample.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                CountSample.setText(null);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (CountSample.getText().length() == 0)
                    CountSample.setText("Введите количество петель в образце 10х10 см");

            }
        });


        fr.add(NorthPanel, BorderLayout.NORTH);

    }

    public static void setCenter(JFrame fr) {
        JPanel CenterPanel = new JPanel();
        CenterPanel.setLayout(new GridLayout(1, 3));
        OneXOne = new JRadioButton("Узор Резинка 1 на 1");
        TwoXTwo = new JRadioButton("Узор Резинка 2 на 2");
        StSt = new JRadioButton(" Узор Лицеваая гладь");
        ButtonGroup rb = new ButtonGroup();
        rb.add(OneXOne);
        rb.add(TwoXTwo);
        rb.add(StSt);
        CenterPanel.add(OneXOne);
        CenterPanel.add(TwoXTwo);
        CenterPanel.add(StSt);

        fr.add(CenterPanel, BorderLayout.CENTER);

    }

    public static void setSouth(JFrame fr) {
        JPanel SouthPanel = new JPanel();
        SouthPanel.setLayout(new FlowLayout());
        fr.add(SouthPanel, BorderLayout.SOUTH);
        Result = new JButton("Рассчитать!");
        Result.setBackground(Color.lightGray);
        Result.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x, y, z;
                x = Integer.parseInt(HeadSize.getText());
                y = Integer.parseInt(CountSample.getText());
                z = x * y / SampleSize;
                z = z - (z / 100 * 10);


                if (OneXOne.isSelected()) {
                    while (z % 2 != 0) {
                        z++;
                    }
                    CountHat.setText(String.valueOf(z));
                }
                if (TwoXTwo.isSelected()) {
                    while (z % 4 != 0) {
                        z++;
                    }
                    CountHat.setText(String.valueOf(z));
                }
                if (StSt.isSelected()) {
                    CountHat.setText(String.valueOf(z));
                }
            }
        });
        SouthPanel.add(Result);


    }


}
