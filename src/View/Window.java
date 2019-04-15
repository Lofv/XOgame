package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Window extends JFrame {

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

    private void choseDifficult() {
        setVisible(true);
        setTitle("Крестики Нолики");
        setLocationRelativeTo(null);
        setSize(300, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panelDif = new JPanel();

        panelDif.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panelDif.setLayout(new GridLayout(3, 1, 5, 5));

        JButton eaBtn = new JButton("Easy Mode");
        eaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelDif.setVisible(false);
                dispose();
                easyMode();
            }
        });

        JButton medBtn = new JButton("Medium Mode");
        medBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelDif.setVisible(false);
                dispose();
                mediumMode();
            }
        });

        JButton haBtn = new JButton("Hard Mode");
        haBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelDif.setVisible(false);
                dispose();
                hardMode();
            }
        });

        panelDif.add(eaBtn);
        panelDif.add(medBtn);
        panelDif.add(haBtn);
        add(panelDif);
    }

    private void createBattleFields(int x, int y) {
        panel = new JPanel();

        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.setLayout(new GridLayout(x, y, 5, 5));
        for (int i = 0; i < x * y; i++) {
            panel.add(new JButton());
        }
        add(panel);
    }

    private void onRadioButtonPressed(JRadioButtonMenuItem button) {
        String label = button.getText();
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
                        easyMode();
                        setEaRBi(eaRBi);
                        break;
                    case "Medium":
                        panel.setVisible(false);
                        dispose();
                        setMeRBi(meRBi);
                        mediumMode();
                        break;
                    case "Hard":
                        panel.setVisible(false);
                        dispose();
                        hardMode();
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

    private void complexity(String name, int x, int y, int difMode, JRadioButtonMenuItem radioButton) {
        setTitle("Крестики нолики - " + name);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(x * 100, y * 100);
        createMenuBar(x, y);
        setSelectedRB(radioButton.getText());
        setDifMode(difMode);
    }

    private void easyMode() {
        eaRBi = new JRadioButtonMenuItem();
        complexity("Easy Mode", 3, 3, 1, eaRBi);
    }

    private void mediumMode() {
        meRBi = new JRadioButtonMenuItem();
        complexity("Medium Mode", 4, 4, 2, meRBi);
    }

    private void hardMode() {
        haRBi = new JRadioButtonMenuItem();
        complexity("Hard Mode", 5, 5, 3, haRBi);
    }


    private void createMenuBar(int x, int y) {
        setVisible(true);
        JMenuBar menuBar = new JMenuBar();

        ImageIcon iconNew = new ImageIcon("resources/new.png");
        ImageIcon iconLoad = new ImageIcon("resources/open.png");
        ImageIcon iconSave = new ImageIcon("resources/save.png");
        ImageIcon iconExit = new ImageIcon("resources/exit.png");

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
        meRBi = new JRadioButtonMenuItem("Medium");
        haRBi = new JRadioButtonMenuItem("Hard");

        eaRBi.addActionListener(event -> onRadioButtonPressed(eaRBi));
        meRBi.addActionListener(event -> onRadioButtonPressed(meRBi));
        haRBi.addActionListener(event -> onRadioButtonPressed(haRBi));

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


}
