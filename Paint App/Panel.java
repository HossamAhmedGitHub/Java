package javadrawingapp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.*;

public class Panel extends JPanel {

    private ArrayList<String> actionsMemory = new ArrayList();
    final Color transParentColor = new Color(255, 255, 255, 0);
    private String status;
    private Color strColor = Color.BLACK; //default color
    private Point startPoint; // Starting point 
    private Point endPoint; // Ending point 
    private ArrayList<Shapes> lines = new ArrayList<>();
    private ArrayList<Shapes> ovals = new ArrayList();
    private ArrayList<Shapes> rectangles = new ArrayList();
    private ArrayList<handFreePoint> freeHand = new ArrayList();
    private ArrayList<handFreePoint> earse = new ArrayList();
    private JCheckBox fillCheckBox;

    Panel() {
        setFocusable(true); // Enable keyboard focus when clicking on the app
        JButton lineButton = new JButton("Line");
        JButton ovalButton = new JButton("Oval");
        JButton rectangleButton = new JButton("Rectangle");
        JButton freeButton = new JButton("FreeHand");
        JButton redButton = new JButton("Red");
        JButton blueButton = new JButton("Blue");
        JButton GreenButton = new JButton("Green");
        JButton earseButton = new JButton("Earse");
        JButton clearButton = new JButton("Clear");
        JButton backButton = new JButton("Back");
        fillCheckBox = new JCheckBox("Fill");
        add(fillCheckBox);
        add(lineButton);
        add(ovalButton);
        add(rectangleButton);
        add(freeButton);
        add(earseButton);
        add(redButton);
        add(blueButton);
        add(GreenButton);
        add(clearButton);
        add(backButton);
        lineButton.addActionListener(new buttonListener());
        ovalButton.addActionListener(new buttonListener());
        rectangleButton.addActionListener(new buttonListener());
        freeButton.addActionListener(new buttonListener());
        redButton.addActionListener(new buttonListener());
        blueButton.addActionListener(new buttonListener());
        GreenButton.addActionListener(new buttonListener());
        earseButton.addActionListener(new buttonListener());
        clearButton.addActionListener(new buttonListener());
        fillCheckBox.addActionListener(new buttonListener());
        backButton.addActionListener(new buttonListener());
        addMouseListener(new mouseListner());
        addMouseMotionListener(new mouseMotion());
    }//end constructor

    class mouseListner implements MouseListener {

        @Override
        public void mousePressed(MouseEvent e) {
            if (status == "Line") {
                startPoint = e.getPoint(); // Store starting point
            } else if (status == "Oval") {
                startPoint = e.getPoint(); // Store starting point
            } else if (status == "Rectangle") {
                startPoint = e.getPoint(); // Store starting point
            } else if (status == "FreeHand") {
                startPoint = e.getPoint(); // Store starting point
                freeHand.add(new handFreePoint(startPoint, new Color(strColor.getRed(), strColor.getGreen(), strColor.getBlue())));
                repaint();
            } else if (status == "Earse") {
                startPoint = e.getPoint(); // Store starting point
                earse.add(new handFreePoint(startPoint, new Color(strColor.getRed(), strColor.getGreen(), strColor.getBlue())));
                repaint();
            }

        }//end method mousePressed()

        @Override
        public void mouseReleased(MouseEvent e) {
            if (status == "Line") {
                endPoint = e.getPoint(); // Store ending point
                lines.add(new Shapes(startPoint, endPoint, new Color(strColor.getRed(), strColor.getGreen(), strColor.getBlue()))); // add the instance of Line class to the arraylist  
                actionsMemory.add("Line");
                repaint();
            } else if (status == "Oval") {
                endPoint = e.getPoint(); // Store ending point
                ovals.add(new Shapes(startPoint, endPoint, fillCheckBox.isSelected(), new Color(strColor.getRed(), strColor.getGreen(), strColor.getBlue())));
                actionsMemory.add("Oval");
                repaint();
            } else if (status == "Rectangle") {
                endPoint = e.getPoint(); // Store ending point
                rectangles.add(new Shapes(startPoint, endPoint, fillCheckBox.isSelected(), new Color(strColor.getRed(), strColor.getGreen(), strColor.getBlue())));
                actionsMemory.add("Rectangle");
                repaint();

            } else if (status == "FreeHand") {
                endPoint.x = -1;
                endPoint.y = -1;
                freeHand.add(new handFreePoint(endPoint, new Color(strColor.getRed(), strColor.getGreen(), strColor.getBlue())));
                actionsMemory.add("FreeHand");
                repaint();
            } else if (status == "Earse") {
                endPoint.x = -1;
                endPoint.y = -1;
                earse.add(new handFreePoint(endPoint, new Color(strColor.getRed(), strColor.getGreen(), strColor.getBlue())));
                actionsMemory.add("Earse");
                repaint();
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }//end mouseListner class

    class mouseMotion implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent e) {
            if (status == "Line") {
                endPoint = e.getPoint(); // Update ending point while dragging
                repaint();
            } else if (status == "Oval") {
                endPoint = e.getPoint();

                repaint();
            } else if (status == "Rectangle") {
                endPoint = e.getPoint();
                repaint();
            } else if (status == "FreeHand") {
                endPoint = e.getPoint();
                freeHand.add(new handFreePoint(endPoint, new Color(strColor.getRed(), strColor.getGreen(), strColor.getBlue())));
                repaint();
            } else if (status == "Earse") {
                endPoint = e.getPoint();
                earse.add(new handFreePoint(endPoint, new Color(strColor.getRed(), strColor.getGreen(), strColor.getBlue())));
                repaint();
            }

        }

        @Override
        public void mouseMoved(MouseEvent e) {
        }  //unused overrided method
    }//end class mouseMotion

    class buttonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            switch (e.getActionCommand()) {
                case "Back":
                    try { // begin try
                        String action = actionsMemory.get(actionsMemory.size() - 1);
                        if (action == "Line") {
                            lines.remove(lines.size() - 1);
                        } else if (action == "Oval") {
                            ovals.remove(ovals.size() - 1);
                        } else if (action == "Rectangle") {
                            rectangles.remove(rectangles.size() - 1);
                        } else if (action == "FreeHand") {

                            freeHand.remove(freeHand.size() - 1);
                            while ((freeHand.get(freeHand.size() - 1).x != -1) || (freeHand.get(freeHand.size() - 1).y != -1)) {
                                freeHand.remove(freeHand.size() - 1);
                                if (freeHand.size() == 0) {
                                    break;
                                }
                            }//end while loop              
                        }//end else if (action == "FreeHand")
                        else if (action == "Earse") {
                            System.out.println("Inside earser");
                            earse.remove(earse.size() - 1);
                            while ((earse.get(earse.size() - 1).x != -1) || (earse.get(earse.size() - 1).y != -1)) {
                                earse.remove(earse.size() - 1);
                                if (earse.size() == 0) {
                                    break;
                                }
                            }//end while loop              
                        }//end else if (action == "earse")

                        actionsMemory.remove(actionsMemory.size() - 1);
                    }// end try 
                    catch (IndexOutOfBoundsException handler) {
                        //do nothing
                    }
                    startPoint = null;
                    endPoint = null;
                    repaint();
                    break;
                case "Line":
                    status = "Line";
                    startPoint = null;
                    endPoint = null;
                    break;
                case "Oval":
                    startPoint = null;
                    endPoint = null;
                    status = "Oval";

                    break;
                case "Rectangle":
                    startPoint = null;
                    endPoint = null;
                    status = "Rectangle";
                    break;
                case "FreeHand":
                    startPoint = null;
                    endPoint = null;
                    status = "FreeHand";
                    break;
                case "Earse":
                    startPoint = null;
                    endPoint = null;
                    status = "Earse";
                    //strColor=Color.WHITE;
                    break;
                case "Clear":
                    status = "Clear ";
                    lines.clear();
                    ovals.clear();
                    rectangles.clear();
                    freeHand.clear();
                    earse.clear();
                    repaint();
                    break;
                case "Red":
                    strColor = Color.RED;
                    break;
                case "Blue":
                    strColor = Color.BLUE;
                    break;
                case "Green":
                    strColor = Color.GREEN;
                    break;
                default:
                    break;
            }

            repaint();
        }

    }//end MyListener class

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        /* Draw current line */
        if ("Line".equals(status) && startPoint != null && endPoint != null) {
            g.setColor(strColor);
            g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y); // Draw the line
        } /* Draw current Oval */ else if ("Oval".equals(status) && startPoint != null && endPoint != null) { //begin Draw current Oval
            g.setColor(strColor);
            int x = Math.min(startPoint.x, endPoint.x);
            int y = Math.min(startPoint.y, endPoint.y);
            int width = Math.abs(endPoint.x - startPoint.x);
            int height = Math.abs(endPoint.y - startPoint.y);
            /* this condition is when clicing on checkbox , it reaches here*/
 /* if no changes happens in the points, so change nothing otherwise draw a new oval*/
            if (ovals.size() > 0) {
                Shapes tmp = ovals.get(ovals.size() - 1);
                if (tmp.start == startPoint && tmp.end == endPoint) {
                    //do nothing
                } else {
                    if (fillCheckBox.isSelected()) {
                        g.fillOval(x, y, width, height);
                    } else {
                        g.drawOval(x, y, width, height);
                    }
                }
            } else {
                if (fillCheckBox.isSelected()) {
                    g.fillOval(x, y, width, height);
                } else {
                    g.drawOval(x, y, width, height);
                }
            }

        }//end draw current oval
        /* Draw current Rectangle */ else if ("Rectangle".equals(status) && startPoint != null && endPoint != null) {
            g.setColor(strColor);
            int x = Math.min(startPoint.x, endPoint.x);
            int y = Math.min(startPoint.y, endPoint.y);
            int width = Math.abs(endPoint.x - startPoint.x);
            int height = Math.abs(endPoint.y - startPoint.y);

            /* this condition is when clicing on checkbox , it reaches here*/
 /* if no changes happens in the points, so change nothing otherwise draw a new rectangle*/
            if (rectangles.size() > 0) {
                Shapes tmp = rectangles.get(rectangles.size() - 1);
                if (tmp.start == startPoint && tmp.end == endPoint) {
                    //do nothing
                } else {
                    if (fillCheckBox.isSelected()) {
                        g.fillRect(x, y, width, height);
                    } else {
                        g.drawRect(x, y, width, height);
                    }
                }
            } else {
                if (fillCheckBox.isSelected()) {
                    g.fillRect(x, y, width, height);
                } else {
                    g.drawRect(x, y, width, height);
                }
            }

        }//end draw current oval

        /* Now print the stored paintings whihc were painted before*/
 /* print the lines */
        for (int i = 0; i < lines.size(); i++) {
            Shapes line = lines.get(i);
            g.setColor(line.color);
            g.drawLine(line.start.x, line.start.y, line.end.x, line.end.y);
        }
        /* print the ovals */
        for (int i = 0; i < ovals.size(); i++) {
            Shapes oval = ovals.get(i);

            g.setColor(oval.color);
            int x = Math.min(oval.start.x, oval.end.x);
            int y = Math.min(oval.start.y, oval.end.y);
            int width = Math.abs(oval.end.x - oval.start.x);
            int height = Math.abs(oval.end.y - oval.start.y);
            if (oval.fillStatus) {
                g.fillOval(x, y, width, height);
            } else {
                g.drawOval(x, y, width, height);
            }
        }
        /* print rectangle drawings*/
        for (int i = 0; i < rectangles.size(); i++) {
            Shapes rectangle = rectangles.get(i);

            g.setColor(rectangle.color);
            int x = Math.min(rectangle.start.x, rectangle.end.x);
            int y = Math.min(rectangle.start.y, rectangle.end.y);
            int width = Math.abs(rectangle.end.x - rectangle.start.x);
            int height = Math.abs(rectangle.end.y - rectangle.start.y);
            if (rectangle.fillStatus) {
                g.fillRect(x, y, width, height);
            } else {
                g.drawRect(x, y, width, height);
            }
        }
        
        /* print freehand dawings*/
        for (int i = 1; i < freeHand.size(); i++) {
            handFreePoint tmpPointAhead = freeHand.get(i);
            handFreePoint tmpPointBehind = freeHand.get(i - 1);
            if (tmpPointAhead.x == -1 || tmpPointAhead.y == -1 || tmpPointBehind.x == -1 || tmpPointBehind.y == -1) {
                continue;
            } else {
                g.setColor(tmpPointAhead.color);
            }
            g.drawLine(tmpPointAhead.x, tmpPointAhead.y, tmpPointBehind.x, tmpPointBehind.y);
        }

        /* earser section*/
        for (int i = 1; i < earse.size(); i++) {
            handFreePoint tmpPointAhead = earse.get(i);
            handFreePoint tmpPointBehind = earse.get(i - 1);
            if (tmpPointAhead.x == -1 || tmpPointAhead.y == -1 || tmpPointBehind.x == -1 || tmpPointBehind.y == -1) {
                continue;
            } else {
                g.setColor(Color.WHITE);
            }
            g.drawLine(tmpPointAhead.x, tmpPointAhead.y, tmpPointBehind.x, tmpPointBehind.y);
        }//end for loop
    }//end paintComponent methid

    private static class Points {

        Point start;
        Point end;

        Points(Point start, Point end) {
            this.start = start;
            this.end = end;
        }
    }//end class Points

    private static class handFreePoint extends Point {

        Color color;

        public handFreePoint(Point p, Color color) {
            super(p);
            this.color = color;
        }
    }//end class handFreePoint

    private static class Shapes extends Points {
        Color color;
        boolean fillStatus;
        public Shapes(Point start, Point end) {
            super(start, end);
        }
        public Shapes(Point start, Point end, Color color) {
            super(start, end);
            this.color = color;
        }
        public Shapes(Point start, Point end, boolean fillStatus, Color color) {
            super(start, end);
            this.fillStatus = fillStatus;
            this.color = color;
        }
    }//end fi
    
}//end Panel class
