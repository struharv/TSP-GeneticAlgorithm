package gui;

import javax.swing.SpinnerNumberModel;


public class SpinnerDouble extends SpinnerNumberModel {

    public final double TICK = 0.01;
    double value;

    public SpinnerDouble(double value) {
        this.value = value;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public void setValue(Object value) {
        double val = (Double) value;
        if (val > 1.0) {
            this.value = 1.0;
        } else {
            if (val < 0) {
                this.value = 0;
            } else {
                this.value = val;
            }
        }
        fireStateChanged();
    }

    @Override
    public Object getNextValue() {
        double val = value + TICK;
        if (val < 1.0) {
            value = val;
        } else {
            value = 1.0;
        }
        return value;
    }

    @Override
    public Object getPreviousValue() {
        double val = value - TICK;
        if (val > 0) {
            value = val;
        } else {
            value = 0;
        }
        return value;
    }
}
