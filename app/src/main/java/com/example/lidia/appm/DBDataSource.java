package com.example.lidia.appm;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DBDataSource {
    private SQLiteDatabase database;
    private MySQLiteHelper databaseHelper;

    public DBDataSource(Context context){
        databaseHelper = new MySQLiteHelper(context);
    }

 public void open(){
        database = databaseHelper.getReadableDatabase();
    }

    public void close(){
        database.close();
    }

    public List<QUESTIONS> getAll(){
        List<QUESTIONS> questions = new ArrayList<>();

        String[] columnNames = MySQLiteHelper.FinalColum.questionss();
        // select person_id, last_name, first_name, age
        //from Person
        //order by last_name

        Cursor cursor = database.query(

                MySQLiteHelper.FINAL_TABLE,
                columnNames,
                null,null,null,null,
                MySQLiteHelper.FinalColum.question.toString() //ordena por name
        );
        cursor.moveToFirst();

        while (! cursor.isAfterLast()) {

            QUESTIONS q = cursorToPerson(cursor);

            questions.add(q);

            cursor.moveToNext();

        }
        cursor.close();

        return questions;
    }

    private QUESTIONS cursorToPerson(Cursor cursor){
        QUESTIONS p = new QUESTIONS();


        String question = cursor.getString(MySQLiteHelper.FinalColum.question.ordinal());
        p.setQuestion(question);

        String answer1 = cursor.getString(MySQLiteHelper.FinalColum.answer1.ordinal());
        p.setAnswer1(answer1);

        String mistake1 = cursor.getString(MySQLiteHelper.FinalColum.mistake1.ordinal());
        p.setMistake1(mistake1);

        String mistake2 = cursor.getString(MySQLiteHelper.FinalColum.mistake2.ordinal());
        p.setMistake2(mistake2);

        String solution = cursor.getString(MySQLiteHelper.FinalColum.solution.ordinal());
        p.setSolution(solution);

        String wrong_solution = cursor.getString(MySQLiteHelper.FinalColum.wrong_solution.ordinal());
        p.setWrong_Solution(wrong_solution);

        return p;
    }
}
