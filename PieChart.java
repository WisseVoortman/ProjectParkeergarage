import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.JPanel;

class Slice {
    public final Color color;
    public final Double value;

    public Slice(Color _color, Double _value) {
        this.color = _color;
        this.value = _value;
    }
}

public class PieChart extends JPanel {

    enum Type {
        STANDARD, SIMPLE_INDICATOR, GRADED_INDICATOR, STANDARD_SLICES
    }

    private Type type = null; //the type of pie chart

    private ArrayList values;
    private ArrayList colors;

    private ArrayList<Slice> slices;

    private ArrayList gradingValues;
    private ArrayList gradingColors;

    double percent = 0; //percent is used for simple indicator and graded indicator

    public PieChart(int percent) {

        type = Type.SIMPLE_INDICATOR;
        this.percent = percent;
    }

    public PieChart(ArrayList values, ArrayList colors) {

        type = Type.STANDARD;

        this.values = values;
        this.colors = colors;
    }

    public PieChart(ArrayList slices) {

        type = Type.STANDARD_SLICES;

        this.slices = slices;
    }

    public PieChart(int percent, ArrayList gradingValues, ArrayList gradingColors) {
        type = Type.GRADED_INDICATOR;

        this.gradingValues = gradingValues;
        this.gradingColors = gradingColors;
        this.percent = percent;

    }

    public void setSlices(ArrayList slices) {

        type = Type.STANDARD_SLICES;

        this.slices = slices;
    }

    @Override
    protected void paintComponent(Graphics g) {

        int width = getSize().width;

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, // Anti-alias!
                RenderingHints.VALUE_ANTIALIAS_ON);

        if (type == Type.SIMPLE_INDICATOR) {

            //colours used for simple indicator
            Color backgroundColor = Color.WHITE;
            Color mainColor = Color.BLUE;

            g2d.setColor(backgroundColor);
            g2d.fillOval(0, 0, width, width);
            g2d.setColor(mainColor);
            Double angle = (percent / 100) * 360;
            g2d.fillArc(0, 0, width, width, -270, -angle.intValue());

        } else if (type == Type.STANDARD) {

            int lastPoint = -270;

            for (int i = 0; i < values.size(); i++) {
                g2d.setColor((Color)colors.get(i));

                Double val = (Double)values.get(i);
                Double angle = (val / 100) * 360;

                g2d.fillArc(0, 0, width, width, lastPoint, -angle.intValue());
                System.out.println("fill arc " + lastPoint + " "
                        + -angle.intValue());

                lastPoint = lastPoint + -angle.intValue();
            }
        }else if (type == Type.STANDARD_SLICES) {

            int lastPoint = -270;
            Double totalValue = 0d;
            //- Get total value
            for (int i = 0; i < slices.size(); i++) {
                totalValue += slices.get(i).value;
            }
            for (int i = 0; i < slices.size(); i++) {
                g2d.setColor( slices.get(i).color );

                Double val = slices.get(i).value;
                Double angle = ( val / totalValue ) * 360;
                g2d.fillArc(0, 0, width, width, lastPoint, -angle.intValue());
                System.out.println("fill arc " + lastPoint + " "
                        + -angle.intValue());

                lastPoint = lastPoint + -angle.intValue();
            }
        } else if (type == Type.GRADED_INDICATOR) {

            int lastPoint = -270;

            double gradingAccum = 0;

            for (int i = 0; i < gradingValues.size(); i++) {
                g2d.setColor((Color)gradingColors.get(i));
                Double val = (Double)gradingValues.get(i);
                gradingAccum = gradingAccum + val;
                Double angle = null;
                /**
                 * If the sum of the gradings is greater than the percent, then we want to recalculate
                 * * the last wedge, and break out of drawing.
                 * */
                if (gradingAccum > percent) {
                    System.out.println("gradingAccum > percent");

                    //get the previous accumulated segments. Segments minus last one
                    double gradingAccumMinusOneSegment = gradingAccum - val;

                    //make an adjusted calculation of the last wedge
                    angle = ((percent - gradingAccumMinusOneSegment) / 100) * 360;

                    g2d.fillArc(0, 0, width, width, lastPoint, -angle.intValue());

                    lastPoint = lastPoint + -angle.intValue();

                    break;

                }else {

                    System.out.println("normal");
                    angle = (val / 100) * 360;

                    g2d.fillArc(0, 0, width, width, lastPoint, -angle.intValue());

                    System.out.println("fill arc " + lastPoint + " "
                            + -angle.intValue());

                    lastPoint = lastPoint + -angle.intValue();
                }
            }
        }
    }
}