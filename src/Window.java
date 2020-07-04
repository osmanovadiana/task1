import javax.swing.*;
import java.awt.*;

public class Window {

    private JPanel panel1;
    private JButton conservationBut;
    private JTextField prevBase;
    private JTextField lastBase;
    private JTextField result;
    private JTextField number;
    private JTextField prevSymbolsAfter36;
    private JTextField lastSymbolsAfter36;

    private Window() {
        conservationBut.addActionListener(actionEvent -> {
            if (!prevSymbolsAfter36.getText().equals("") && lastSymbolsAfter36.getText().equals("")) {
                try {
                    CalculusSystem cs = new CalculusSystem(number.getText(), Integer.parseInt(prevBase.getText()),
                            prevSymbolsAfter36.getText());
                    result.setText(cs.conversation(Integer.parseInt(lastBase.getText())));
                } catch (CalculusSystemBaseException | CalculusSystemNumberException | CalculusSystemSymbolsAfter36Exception e) {
                    e.printStackTrace();
                }
            }
            else if (prevSymbolsAfter36.getText().equals("") && !lastSymbolsAfter36.getText().equals("")) {
                try {
                    CalculusSystem cs = new CalculusSystem(number.getText(), Integer.parseInt(prevBase.getText()));
                    result.setText(cs.conversation(Integer.parseInt(lastBase.getText()), lastSymbolsAfter36.getText()));
                } catch (CalculusSystemBaseException | CalculusSystemNumberException | CalculusSystemSymbolsAfter36Exception e) {
                    e.printStackTrace();
                }
            }
            else if (!prevSymbolsAfter36.getText().equals("") && !lastSymbolsAfter36.getText().equals("")) {
                try {
                    CalculusSystem cs = new CalculusSystem(number.getText(), Integer.parseInt(prevBase.getText()),
                            prevSymbolsAfter36.getText());
                    result.setText(cs.conversation(Integer.parseInt(lastBase.getText()), lastSymbolsAfter36.getText()));
                } catch (CalculusSystemBaseException | CalculusSystemNumberException | CalculusSystemSymbolsAfter36Exception e) {
                    e.printStackTrace();
                }
            }
            else {
                try {
                    CalculusSystem cs = new CalculusSystem(number.getText(), Integer.parseInt(prevBase.getText()));
                    result.setText(cs.conversation(Integer.parseInt(lastBase.getText())));
                } catch (CalculusSystemBaseException | CalculusSystemNumberException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void main (String[] args){
        JFrame frame = new JFrame("Calculus system");
        frame.setContentPane(new Window().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit tkt = Toolkit.getDefaultToolkit();
        Dimension dim = tkt.getScreenSize();
        frame.setBounds(dim.width / 2 - 250, dim.height / 2 - 150, 500, 300);
        frame.setVisible(true);
    }
}
