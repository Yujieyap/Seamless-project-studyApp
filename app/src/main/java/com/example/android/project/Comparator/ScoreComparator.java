package com.example.android.project.Comparator;

import com.example.android.project.models.Score;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by fypjadmin on 5/24/2016.
 */
public class ScoreComparator implements Comparator<Score> {

    @Override
    public int compare(Score a, Score b) {

        // Get the score and create the Integer Object for comparing
        Integer points1 = new Integer(a.getPoints());
        Integer points2 = new Integer(b.getPoints());

        // Compare the score
        int scoreResult = points1.compareTo(points2);

        // Date time format *Please change to your own format*
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a");

        if (scoreResult == 0) {

            // Score are equal, sort by date

            Date date1 = null, date2 = null;
            try {

                date1 = sdf.parse(a.getDate());
                date2 = sdf.parse(b.getDate());

            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return date1.compareTo(date2);

        } else {

            return scoreResult;
        }
    }
}
