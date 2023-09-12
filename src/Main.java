import driver.driver;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    //<editor-fold defaultstate="collapsed" desc="Globals Variables">

    private JTextField txt_info;
    private JButton btn_4;
    private JButton btn_7;
    private JButton btn_2;
    private JButton btn_5;
    private JButton btn_8;
    private JButton btn_3;
    private JButton btn_6;
    private JButton btn_9;
    private JButton btn_plus;
    private JButton btn_minus;
    private JButton btn_div;
    private JPanel root;
    private JButton btn_0;
    private JButton btn_dot;
    private JButton btn_times;
    private JButton bnt_1;
    private JPanel panel_calculator;
    private JButton btn_sin;
    private JButton btn_cos;
    private JButton btn_pi;
    private JButton btn_sqrt;
    private JButton btn_x10;
    private JButton btn_cls;
    private JButton btn_equals;
    private JButton btn_log;
    private JButton btn_parent_right;
    private JButton btn_parent_left;
    private JLabel lb_result;
    private JLabel lb_result_info;
    private JPanel jpanel_result;
    private JButton btn_porcentaje;
    private JButton btn_fac;
    //</editor-fold>


    public Main() {
        setContentPane(root);
        setTitle("Practica II");
        setSize(700, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        txt_info.setEnabled(false);

        setVisible(true);

        // Funcion para traer el texto del boton y ponerla en el txt
        ActionListener buttonClickListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource(); // Obtener el botón que genero el evento
                String buttonText = button.getText(); // Obtener el texto del botón
                if (buttonText.equals("Sin") || buttonText.equals("Cos") || buttonText.equals("√")) {
                    txt_info.setText(txt_info.getText() + buttonText + "("); // Agregar el texto al campo txt_info
                } else if (buttonText.equals("x10")) {
                    txt_info.setText(txt_info.getText()+buttonText+"^");
                } else {
                    txt_info.setText(txt_info.getText() + buttonText); // Agregar el texto al campo txt_info
                }
            }
        };
        // Asignamos el mismo ActionListener a todos los botones numéricos
        btn_0.addActionListener(buttonClickListener);
        bnt_1.addActionListener(buttonClickListener);
        btn_2.addActionListener(buttonClickListener);
        btn_3.addActionListener(buttonClickListener);
        btn_4.addActionListener(buttonClickListener);
        btn_5.addActionListener(buttonClickListener);
        btn_6.addActionListener(buttonClickListener);
        btn_7.addActionListener(buttonClickListener);
        btn_8.addActionListener(buttonClickListener);
        btn_9.addActionListener(buttonClickListener);
        btn_dot.addActionListener(buttonClickListener);
        btn_pi.addActionListener(buttonClickListener);
        btn_plus.addActionListener(buttonClickListener);
        btn_minus.addActionListener(buttonClickListener);
        btn_div.addActionListener(buttonClickListener);
        btn_times.addActionListener(buttonClickListener);
        btn_sin.addActionListener(buttonClickListener);
        btn_cos.addActionListener(buttonClickListener);
        btn_sqrt.addActionListener(buttonClickListener);
        btn_fac.addActionListener(buttonClickListener);
        btn_porcentaje.addActionListener(buttonClickListener);
        btn_cls.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txt_info.setText("");
            }
        });
        btn_x10.addActionListener(buttonClickListener);
        btn_log.addActionListener(buttonClickListener);
        btn_parent_right.addActionListener(buttonClickListener);
        btn_parent_left.addActionListener(buttonClickListener);

        btn_equals.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lb_result.setText(String.valueOf(driver.calculate_expression(txt_info.getText())));
            }
        });


    }

    public static void main(String[] args) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc="Code Setting View">
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        Main m = new Main();

    }
}

