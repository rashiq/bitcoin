package com.bitcoin.presentation.view.chart;

public class ChartScale {

  private int maxTicks = 10;
  private float interval;
  private float range;
  private float min;
  private float max;

  public ChartScale(float min, float max) {
    this.min = min;
    this.max = max;
    calculate();
  }

  private void calculate() {
    this.range = evenNumber(max - min);
    this.interval = evenNumber(range / (maxTicks - 1));
    this.min = (float) (Math.floor(min / interval) * interval);
    this.max = (float) (Math.ceil(max / interval) * interval);
  }

  private float evenNumber(double range) {
    // exponent of range
    double exponent;
    // fractional part of range
    double fraction;
    // rounded fraction
    double niceFraction;

    exponent = Math.floor(Math.log10(range));
    fraction = range / Math.pow(10, exponent);

    if (fraction < 1.5) {
      niceFraction = 1;
    } else if (fraction < 3) {
      niceFraction = 2;
    } else if (fraction < 7) {
      niceFraction = 5;
    } else {
      niceFraction = 10;
    }

    return (float) (niceFraction * Math.pow(10, exponent));
  }

  public float getInterval() {
    return interval;
  }

  public float getCalculatedMin() {
    return min;
  }

  public float getCalculatedMax() {
    return max;
  }
}