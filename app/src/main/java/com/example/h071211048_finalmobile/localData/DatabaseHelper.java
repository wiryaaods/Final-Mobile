package com.example.h071211048_finalmobile.localData;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.h071211048_finalmobile.model.MovieResult;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Favorite.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "FavoriteItem";
    private static final String ID ="id";
    private static final String TITLE ="title";
    private static final String POSTER ="poster";
    private static final String BACKDROP ="backdrop";
    private static final String DATE ="date";
    private static final String RATING ="rating";
    private static final String OVERVIEW ="OVERVIEW";


    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                TITLE + " TEXT, " +
                POSTER + " TEXT, " +
                BACKDROP+ " TEXT, " +
                DATE + " TEXT, " +
                RATING + " TEXT, " +
                 OVERVIEW + " TEXT)";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void AddData(String title, String poster, String backdrop, String date, String rating, String overview){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TITLE, title);
        cv.put(POSTER, poster);
        cv.put(BACKDROP, backdrop);
        cv.put(DATE, date);
        cv.put(RATING, rating);
        cv.put(OVERVIEW, overview);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1){
            Toast.makeText(context, title + " Failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, title + " Added Successfull", Toast.LENGTH_SHORT).show();
        }
    }

    public List<MovieResult> getAllFavorite() {
        List<MovieResult> movieResults = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null && cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(ID);
            int titleIndex = cursor.getColumnIndex(TITLE);
            int posterIndex = cursor.getColumnIndex(POSTER);
            int backdropIndex = cursor.getColumnIndex(BACKDROP);
            int dateIndex = cursor.getColumnIndex(DATE);
            int ratingIndex = cursor.getColumnIndex(RATING);
            int overviewIndex = cursor.getColumnIndex(OVERVIEW);

            do {
                int id = cursor.getInt(idIndex);
                String title = cursor.getString(titleIndex);
                String poster = cursor.getString(posterIndex);
                String backdrop = cursor.getString(backdropIndex);
                String date = cursor.getString(dateIndex);
                String rating = cursor.getString(ratingIndex);
                String overview = cursor.getString(overviewIndex);

                MovieResult movieResult = new MovieResult();
                movieResult.setId(id);
                movieResult.setTitle(title);
                movieResult.setPosterPath(poster);
                movieResult.setBackdropPath(backdrop);
                movieResult.setReleaseDate(date);
                if (!rating.isEmpty()) {
                    movieResult.setVoteAverage(Double.parseDouble(rating));
                }
                movieResult.setOverview(overview);

                movieResults.add(movieResult);
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }

        return movieResults;
    }
}

