package UIComponents;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class GSearchField extends JTextField implements FocusListener{
    final private Color backgroundColor = new Color(0x262626);
    final private Color textColor = new Color(0xA6A3A1);
    final private Icon searchIcon = new ImageIcon("assets/images/carSearch.png");

    public GSearchField() {
        this.setOpaque(false);
        this.setBorder(new EmptyBorder(10, 158, 10, 10));
        this.setSelectionColor(new Color(0xA62631));
        this.setForeground(textColor);
        this.setFont(new Font("Segoe UI", Font.PLAIN, 32));
        this.setEnabled(false);
        this.setCaretColor(Color.WHITE);

        this.addFocusListener(this);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                requestFocusInWindow(true);
                setEnabled(true);
                repaint();
            }
        });

    }

    @Override
    protected void paintComponent(Graphics graphics) {
//        System.out.println("Component " + this.hasFocus());
        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        graphics2D.setColor(backgroundColor);
        graphics2D.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), this.getHeight(), this.getHeight());

        graphics2D.drawImage(((ImageIcon) searchIcon).getImage(), 31, 10, 74, 40, null);

        if (this.getText().equals("") && !this.hasFocus()) {
//            System.out.println("Gebchalew");
            graphics2D.setColor(textColor);
            graphics2D.drawString("Search Plate Number", getInsets().left, graphics.getFontMetrics()
                    .getMaxAscent() + getInsets().top);
        }
        super.paintComponent(graphics);
        graphics2D.dispose();
    }

    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {
        System.out.println("Lost");
        repaint();
    }
}