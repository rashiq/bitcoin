package com.bitcoin.presentation.view.chart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.util.AttributeSet;
import android.view.View;
import com.bitcoin.presentation.model.BitcoinPriceViewModel;
import java.util.ArrayList;
import java.util.List;

public class LineChart extends View {
  public static final float BORDER = 60;
  public static final float HORIZONTAL_START = BORDER * 2;
  private List<BitcoinPriceViewModel> values;
  private Paint paint;
  private ChartScale scale;
  private float totalHeight;
  private float totalWidth;
  private float maxValue;
  private float minValue;
  private float heightDiff;
  private float graphHeight;
  private float graphWidth;
  private float min;
  private float max;
  private float interval;
  private int legendLineColor;
  private int legendTextColor;
  private int chartColor;
  private float legendStrokeWidth;
  private float chartStrokeWidth;
  private float legendTextSize;

  public LineChart(Context context) {
    super(context);
    init();
  }

  public LineChart(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public LineChart(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init() {
    paint = new Paint();
    values = new ArrayList<>();
    legendLineColor = Color.DKGRAY;
    legendTextColor = Color.WHITE;
    chartColor = Color.CYAN;
    legendStrokeWidth = 2;
    chartStrokeWidth = 6;
    legendTextSize = 36f;
  }

  public void setValues(List<BitcoinPriceViewModel> values) {
    this.values = values;
    this.maxValue = getMaxValue();
    this.minValue = getMinValue();
    this.heightDiff = maxValue - minValue;
    this.scale = new ChartScale(minValue, maxValue);
    this.min = scale.getCalculatedMin();
    this.max = scale.getCalculatedMax();
    this.interval = scale.getInterval();
    invalidate();
  }

  public void setLegendLineColor(int color) {
    this.legendLineColor = color;
  }

  public void setLegendTextColor(int color) {
    this.legendTextColor = color;
  }

  public void setChartColor(int color) {
    this.chartColor = color;
  }

  public void setLegendStrokeWidth(float width) {
    this.legendStrokeWidth = width;
  }

  public void setChartStrokeWidth(float width) {
    this.chartStrokeWidth = width;
  }

  public void setLegendTextSize(float size) {
    this.legendTextSize = size;
  }

  private float getMaxValue() {
    float maxHeight = 0f;
    for (BitcoinPriceViewModel value : values) {
      if (value.getY() > maxHeight) maxHeight = (float) value.getY();
    }
    return maxHeight;
  }

  private float getMinValue() {
    float minHeight = values.size() > 1 ? (float) values.get(0).getY() : 0f;
    for (BitcoinPriceViewModel value : values) {
      if (value.getY() < minHeight) minHeight = (float) value.getY();
    }
    return minHeight;
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    int width = MeasureSpec.getSize(widthMeasureSpec);
    int height = MeasureSpec.getSize(heightMeasureSpec);
    this.totalHeight = height;
    this.totalWidth = width - 1;
    this.graphHeight = height - (2 * BORDER);
    this.graphWidth = width - (2 * BORDER);
    this.setMeasuredDimension(width, height);
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
  }

  @Override protected void onDraw(Canvas canvas) {
    if (isInEditMode() || values.isEmpty()) return;
    drawYLegend(canvas);
    drawXLegend(canvas);
    drawChartLine(canvas);
  }

  private void drawYLegend(Canvas canvas) {
    paint.setStrokeWidth(1);
    paint.setTextAlign(Align.LEFT);
    paint.setTextSize(legendTextSize);
    for (int i = (int) (min + interval); i <= (int) (max - interval - 1); i += interval) {
      paint.setColor(legendLineColor);
      float value = i - minValue;
      float ratio = value / heightDiff;
      float y = (-graphHeight * ratio) + graphHeight;
      canvas.drawLine(HORIZONTAL_START, y, totalWidth, y, paint);
      paint.setColor(legendTextColor);
      canvas.drawText(String.valueOf(i), 0, y, paint);
    }
  }

  private void drawXLegend(Canvas canvas) {
    final long start = values.get(0).getX();
    final long end = values.get(values.size() - 1).getX();
    final List<String> months = MonthUtils.getMonthSpan(start, end);
    int interval = 2;
    paint.setStrokeWidth(legendStrokeWidth);

    for (int i = 0; i < months.size(); i += interval) {
      float columnWidth = graphWidth / months.size();
      float x = columnWidth * i + HORIZONTAL_START;
      paint.setColor(legendLineColor);
      canvas.drawLine(x, totalHeight - BORDER, x, BORDER, paint);
      paint.setColor(legendTextColor);
      canvas.drawText(months.get(i), x, totalHeight, paint);
    }
  }

  private void drawChartLine(Canvas canvas) {
    paint.setColor(chartColor);
    paint.setStrokeCap(Paint.Cap.ROUND);
    paint.setStrokeWidth(chartStrokeWidth);

    float columnWidth = graphWidth / (values.size() - 1);
    float lastHeight = 0;

    for (int i = 0; i < values.size(); i++) {
      float value = (float) (values.get(i).getY() - minValue);
      float ratio = value / heightDiff;
      float y = graphHeight * ratio;
      float startX = ((i - 1) * columnWidth) + (HORIZONTAL_START + 1);
      float startY = (BORDER - lastHeight) + graphHeight;
      float endX = (i * columnWidth) + (HORIZONTAL_START + 1);
      float endY = (BORDER - y) + graphHeight;

      canvas.drawLine(startX, startY, endX, endY, paint);
      lastHeight = y;
    }
  }
}