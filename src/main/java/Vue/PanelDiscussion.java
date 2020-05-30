package Vue;

import Modele.Singletons;

import javax.swing.*;
import java.awt.*;

public class PanelDiscussion extends JPanel {
    JPanel panelNord;
    JPanel panelCentre;
    protected int strokeSize = 1;
    protected Color shadowColor = Color.black;
    protected boolean shady = true;
    protected boolean highQuality = true;
    protected Dimension arcs = new Dimension(20, 20);
    protected int shadowGap = 5;
    protected int shadowOffset = 4;
    protected int shadowAlpha = 150;



    public PanelDiscussion() {

        this.setBackground(new Color(255, 255, 255));
        this.setForeground(new Color(102, 102, 102));
        this.setPreferredSize(new Dimension(600,700));
        this.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        this.setAlignmentY(JComponent.CENTER_ALIGNMENT);
        this.setOpaque(false);
        panelNord = Singletons.getPanelNord();
        panelCentre = Singletons.getPanelCentre();
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setLayout(new BorderLayout());
        add(panelCentre, BorderLayout.CENTER);
        add(panelNord, BorderLayout.NORTH);
        setVisible(true);

        JScrollPane pane = new JScrollPane(panelCentre);
        pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        add(pane);
        pane.setViewportView(panelCentre);
        pane.revalidate();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        int shadowGap = this.shadowGap;
        Color shadowColorA = new Color(shadowColor.getRed(),
                shadowColor.getGreen(), shadowColor.getBlue(), shadowAlpha);
        Graphics2D graphics = (Graphics2D) g;

        if (highQuality) {
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
        }

        if (shady) {
            graphics.setColor(shadowColorA);
            graphics.fillRoundRect(
                    shadowOffset,// X position
                    shadowOffset,// Y position
                    width - strokeSize - shadowOffset, // width
                    height - strokeSize - shadowOffset, // height
                    arcs.width, arcs.height);// arc Dimension
        } else {
            shadowGap = 1;
        }

        graphics.setColor(getBackground());
        graphics.fillRoundRect(0, 0, width - shadowGap,
                height - shadowGap, arcs.width, arcs.height);
        graphics.setColor(getForeground());
        graphics.setStroke(new BasicStroke(strokeSize));
        graphics.drawRoundRect(0, 0, width - shadowGap,
                height - shadowGap, arcs.width, arcs.height);

        graphics.setStroke(new BasicStroke());
    }
}
