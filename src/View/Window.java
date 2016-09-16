package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Window extends JFrame {

    int x;
    int y;
    JPanel panel;
    ButtonGroup difGroup;
    JRadioButtonMenuItem eaRBi;
    JRadioButtonMenuItem meRBi;
    JRadioButtonMenuItem haRBi;
    Object[] options = {"Да", "Нет"};
    String selectedRB;
    int difMode;

    public Window() {
        choseDifficult();
    }

    public int getDifMode() {
        return difMode;
    }

    public void setDifMode(int difMode) {
        this.difMode = difMode;
    }

    public String getSelectedRB() {
        return selectedRB;
    }

    public void setSelectedRB(String selectedRB) {
        this.selectedRB = selectedRB;
    }

    public void setEaRBi(JRadioButtonMenuItem eaRBi) {
        this.eaRBi = eaRBi;
        eaRBi.setSelected(true);

    }

    public void setMeRBi(JRadioButtonMenuItem meRBi) {
        this.meRBi = meRBi;
        meRBi.setSelected(true);

    }

    public void setHaRBi(JRadioButtonMenuItem haRBi) {
        this.haRBi = haRBi;
        haRBi.setSelected(true);

    }

    public void button(JRadioButtonMenuItem button, String label) {
        System.out.println(getSelectedRB());
        if (label.equals(getSelectedRB())) {
            JOptionPane.showMessageDialog(null, "Вы итак уже выбрали " + label,
                    "Warning", JOptionPane.WARNING_MESSAGE);

        } else {
            int n = JOptionPane.showOptionDialog(null,
                    "Вы хотите начать игру в " + label + "?",
                    "Изменим сложность?",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,     //do not use a custom Icon
                    options,  //the titles of buttons
                    options[0]);
            if (n == 0) {

                switch (label) {
                    case "Easy":
                        panel.setVisible(false);
                        dispose();
                        easyGame();
                        setEaRBi(eaRBi);
                        break;
                    case "Medium":
                        panel.setVisible(false);
                        dispose();
                        mediumGame();
                        setMeRBi(meRBi);
                        break;
                    case "Hard":
                        panel.setVisible(false);
                        dispose();
                        hardGame();
                        setHaRBi(haRBi);
                        break;
                    default:
                        System.out.println("Неверная кнопка");
                }
            } else {
                switch (getDifMode()) {
                    case 1:
                        eaRBi.setSelected(true);
                        break;
                    case 2:
                        meRBi.setSelected(true);
                        break;
                    case 3:
                        haRBi.setSelected(true);
                        break;
                    default:
                        System.out.println("Неверный уровень сложности");
                }


            }

        }
    }


    public void easyGame() {

        //     setVisible(true);

        setTitle("Крестики нолики - Easy Mode");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 300);
        x = 3;
        y = 3;
        createMenuBar(x, y);
        setSelectedRB(eaRBi.getText());
        setDifMode(1);
    }

    public void mediumGame() {

        setTitle("Крестики нолики - Medium Mode");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(400, 400);
        x = 4;
        y = 4;
        createMenuBar(x, y);
        setSelectedRB(meRBi.getText());
        setDifMode(2);
    }

    public void hardGame() {
        setTitle("Крестики нолики - Hard Mode");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(500, 500);
        x = 5;
        y = 5;
        createMenuBar(x, y);
        setSelectedRB(haRBi.getText());
        setDifMode(3);

    }


    private void choseDifficult() {
        setVisible(true);
        setTitle("Крестики Нолики");
        setLocationRelativeTo(null);
        setSize(300, 300);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        JPanel panelDif = new JPanel();

        panelDif.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panelDif.setLayout(new GridLayout(3, 1, 5, 5));

        JButton eaBtn = new JButton("Easy Mode");
        eaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelDif.setVisible(false);
                dispose();
                easyGame();
                setEaRBi(eaRBi);

            }

        });

        JButton medBtn = new JButton("Medium Mode");
        medBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelDif.setVisible(false);
                dispose();
                mediumGame();
                setMeRBi(meRBi);
            }
        });

        JButton haBtn = new JButton("Hard Mode");
        haBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelDif.setVisible(false);
                dispose();
                hardGame();
                setHaRBi(haRBi);
            }
        });

        panelDif.add(eaBtn);
        panelDif.add(medBtn);
        panelDif.add(haBtn);
        add(panelDif);

    }

    private void createMenuBar(int x, int y) {
        setVisible(true);
        JMenuBar menuBar = new JMenuBar();

        panel = new JPanel();

        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.setLayout(new GridLayout(x, y, 5, 5));
        for (int i = 0; i < x * y; i++) {
            panel.add(new JButton());
        }
        add(panel);


        ImageIcon iconNew = new ImageIcon("D:\\Java\\TestSwingSecondLesson\\resources\\new.png");
        ImageIcon iconLoad = new ImageIcon("D:\\Java\\TestSwingSecondLesson\\resources\\open.png");
        ImageIcon iconSave = new ImageIcon("D:\\Java\\TestSwingSecondLesson\\resources\\save.png");
        ImageIcon iconExit = new ImageIcon("D:\\Java\\TestSwingSecondLesson\\resources\\exit.png");

        JMenuItem fileMenu = new JMenu("File");
        JMenuItem optMenu = new JMenu("Option");

        JMenuItem newGame = new JMenuItem("New", iconNew);
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Да начнется новая игра!",
                        "Warning", JOptionPane.WARNING_MESSAGE);

                panel.setVisible(false);
                dispose();
                choseDifficult();

            }
        });

        JMenuItem loadGame = new JMenuItem("Load", iconLoad);
        JMenuItem saveGame = new JMenuItem("Save", iconSave);
        JMenuItem exitGame = new JMenuItem("Exit", iconExit);

        exitGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        fileMenu.add(newGame);
        fileMenu.add(loadGame);
        fileMenu.add(saveGame);
        fileMenu.add(exitGame);


        eaRBi = new JRadioButtonMenuItem("Easy");

        eaRBi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button(eaRBi, eaRBi.getText());
            }
        });
        meRBi = new JRadioButtonMenuItem("Medium");
        meRBi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button(meRBi, meRBi.getText());
            }
        });
        haRBi = new JRadioButtonMenuItem("Hard");

        haRBi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button(haRBi, haRBi.getText());
            }
        });


        difGroup = new ButtonGroup();
        difGroup.add(eaRBi);
        difGroup.add(meRBi);
        difGroup.add(haRBi);

        optMenu.add(eaRBi);
        optMenu.add(meRBi);
        optMenu.add(haRBi);

        menuBar.add(fileMenu);
        menuBar.add(optMenu);

        setJMenuBar(menuBar);
    }


}
