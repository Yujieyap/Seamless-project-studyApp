package com.example.android.project;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;

import com.example.android.project.models.Score;
import com.example.android.project.utilities.AppUtilities;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TeacherGrades extends AppCompatActivity implements OnChartValueSelectedListener {
    private PieChart mChart;

    private ArrayList<Entry> yVals1 = new ArrayList<Entry>();
    private ArrayList<String> xVals = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_grades);

        mChart = (PieChart) findViewById(R.id.chart);
        mChart.setUsePercentValues(true);
        mChart.setDescription("");
        mChart.setExtraOffsets(5, 10, 5, 5);

        mChart.setDragDecelerationFrictionCoef(0.95f);

        mChart.setExtraOffsets(5.f, 5.f, 5.f, 5.f);

        mChart.setCenterText(generateCenterSpannableText());

        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColor(Color.WHITE);

        mChart.setTransparentCircleColor(Color.WHITE);
        mChart.setTransparentCircleAlpha(110);

        mChart.setHoleRadius(58f);
        mChart.setTransparentCircleRadius(61f);

        mChart.setDrawCenterText(true);

        mChart.setRotationAngle(0);
        mChart.setRotationEnabled(true);
        mChart.setHighlightPerTapEnabled(true);

        mChart.setOnChartValueSelectedListener(this);

        setData2();

        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);

        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);
    }

    private void setData2() {

        List<Integer> points = new ArrayList<>();
        List<Integer> pointList = new ArrayList<>();
        List<String> type = new ArrayList<>();

        for (Score s : AppUtilities.getScoreList()) {
            points.add(s.getPoints());
            pointList.add(s.getPoints());
            type.add(s.getPoints() + " point");
        }

        Set<Integer> hsPoints = new HashSet<>();
        Set<String> hsType = new HashSet<>();

        hsPoints.addAll(points);
        hsType.addAll(type);

        points.clear();
        type.clear();

        points.addAll(hsPoints);
        type.addAll(hsType);

        Collections.sort(points);
        Collections.sort(type);
        int i = 0;
        for (Integer keyPoint : points) {
            yVals1.add(new Entry((float) Collections.frequency(pointList, keyPoint), i));
            i++;
        }

        for (String keyType : type) {
            xVals.add(new String(keyType));
        }

        PieDataSet dataSet = new PieDataSet(yVals1, AppUtilities.getChapter() + " score");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);

        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        mChart.setData(data);
        mChart.highlightValues(null);
        mChart.invalidate();
    }

    private SpannableString generateCenterSpannableText() {

        SpannableString s = new SpannableString(AppUtilities.getSubject() + "\n" + AppUtilities.getChapter());
        s.setSpan(new RelativeSizeSpan(1.7f), 0, AppUtilities.getSubject().length(), 0);
        s.setSpan(new RelativeSizeSpan(.8f), AppUtilities.getSubject().length(), s.length() - AppUtilities.getChapter().length(), 0);
        s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - AppUtilities.getChapter().length(), s.length(), 0);
        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - AppUtilities.getChapter().length(), s.length(), 0);
        return s;
    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
        if (e == null)
            return;
        Log.i("VAL SELECTED",
                "Value: " + e.getVal() + ", xIndex: " + e.getXIndex()
                        + ", DataSet index: " + dataSetIndex);
    }

    @Override
    public void onNothingSelected() {

    }
}


