package com.example.android.project.utilities;

import android.widget.Button;

import com.example.android.project.R;
import com.example.android.project.models.QuizQuestion;
import com.example.android.project.models.Score;
import com.firebase.client.utilities.Utilities;


import java.util.ArrayList;

/**
 * Created by user on 4/25/2016.
 */
public class AppUtilities {

    public static ArrayList<QuizQuestion> questionList = new ArrayList<>();
    public static boolean loaded = true;
    public static ArrayList<Score> scoreLists = new ArrayList<>();
    public static ArrayList<Score> highscore = new ArrayList<>();
    public static ArrayList<QuizQuestion> key = new ArrayList<>();
    public static Score myScore = new Score();
    private static ArrayList<Score> scoreList = new ArrayList<>();


    private static String chapterSelected;

    public static String getChapterSelected() {
        return chapterSelected;
    }

    public static void setChapterSelected(String chapterSelected) {
        AppUtilities.chapterSelected = chapterSelected;
    }

    public static ArrayList<QuizQuestion> getQuestionList() {
        return questionList;
    }

    public static void setQuestionList(ArrayList<QuizQuestion> questionList) {
        AppUtilities.questionList = questionList;
    }


    public static ArrayList<Score> getScoreLists() {
        return scoreLists;
    }

    public static void setScoreLists(ArrayList<Score> scoreLists) {
        AppUtilities.scoreLists = scoreLists;
    }


    public static ArrayList<Score> getHighScore() {
        return highscore;
    }

    public static void setHighscore(ArrayList<Score> highscore) {
        AppUtilities.highscore = highscore;
    }

    public static Score getMyScore() {
        return myScore;
    }

    public static void setMyScore(Score myScore) {
        AppUtilities.myScore = myScore;
    }

    public static ArrayList<QuizQuestion> getKey() {
        return key;
    }

    public static void setKey(ArrayList<QuizQuestion> key) {
        AppUtilities.key = key;
    }


    private static String subject;
    private static String chapter;

    public static ArrayList<Score> getScoreList() {
        return scoreList;
    }

    public static void setScoreList(ArrayList<Score> scoreList) {
        AppUtilities.scoreList = scoreList;
    }

    public static String getSubject() {
        return subject;
    }

    public static void setSubject(String subject) {
        AppUtilities.subject = subject;
    }

    public static String getChapter() {
        return chapter;
    }

    public static void setChapter(String chapter) {
        AppUtilities.chapter = chapter;
    }
}
