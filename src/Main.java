import javax.swing.*;

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
    private JButton cosButton;
    private JButton πButton;
    private JButton btn_sqrt;
    private JButton btn_x10;
    private JButton btn_cls;
    private JButton btn_equals;
    private JButton btn_log;
    private JButton btn_parent_right;
    private JButton btn_parent_left;
    //</editor-fold>

    public Main(){
        setContentPane(root);
        setTitle("Practica II");
        setSize(400, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        txt_info.setEnabled(false);

        setVisible(true);
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

