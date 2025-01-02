import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.border.Border;

public class Frame extends JFrame implements MouseListener {
    ArrayList<JLabel> labels;
    String symbol;

    Frame() {

        labels = new ArrayList<JLabel>();
        for (int i = 1; i < 10; i++) {
            JLabel label = new JLabel();
            label.setFont(new Font("Arial", Font.BOLD, 20));

            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.CENTER);

            Border blackBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
            label.setBorder(blackBorder);

            this.add(label);
            labels.add(label);
            label.addMouseListener(this);
        }

        symbol = "X";
        this.setDefaultCloseOperation(Frame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setLayout(new GridLayout(3, 3));
        this.setVisible(true);
    }

    private boolean isWinner() {

        int[][] positions = {
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 8},
                {0, 3, 6},
                {1, 4, 7},
                {2, 5, 8},
                {0, 4, 8},
                {2, 4, 6}
        };

        for (int[] row : positions) {
            if (!labels.get(row[0]).getText().isEmpty() && labels.get(row[0]).getText().equals(labels.get(row[1]).getText()) && labels.get(row[1]).getText().equals(labels.get(row[2]).getText())) {
                labels.get(row[0]).setForeground(Color.GREEN);
                labels.get(row[1]).setForeground(Color.GREEN);
                labels.get(row[2]).setForeground(Color.GREEN);
                return true;
            }
        }

        return false;
    }

    private void resetGame() {
        for (JLabel label : labels) {
            label.setText("");
            label.setForeground(Color.BLACK);
        }
        symbol = "X";
    }

    private boolean noWinner(){
        for(JLabel label : labels){
            if(label.getText().isEmpty()){
                return false;
            }
        }
        return true;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (JLabel label : labels) {
            if (e.getSource() == label) {
                if (!label.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "position taken");
                    break;
                }
                label.setText(symbol);
                if (isWinner()) {
                    JOptionPane.showMessageDialog(null, symbol + " is won");
                    resetGame();
                    break;
                }

                if(noWinner()){
                    JOptionPane.showMessageDialog(null,  " all positions taken and no winner, game will reset");
                    resetGame();
                    break;
                }
                symbol = symbol.equals("X") ? "O" : "X";
                break;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
