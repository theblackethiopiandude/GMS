package UIComponents;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollBar;

public class ScrollBarCustom extends JScrollBar {

    public ScrollBarCustom(Color backgroundColor, Color foregroundColor) {
        setUI(new ModernScrollBarUI());
        setPreferredSize(new Dimension(8, 8));
        setForeground(foregroundColor);
        setBackground(backgroundColor);
    }
}
