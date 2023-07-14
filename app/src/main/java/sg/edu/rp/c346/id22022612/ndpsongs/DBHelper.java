package sg.edu.rp.c346.id22022612.ndpsongs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "NDPSongs.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_SONGS = "Songs";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_SINGER = "singer";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_RATING = "rating";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE " + TABLE_SONGS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT,"
                + COLUMN_SINGER + " TEXT,"
                + COLUMN_YEAR + " INTEGER,"
                + COLUMN_RATING + " INTEGER)";
        db.execSQL(createTableSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTableSql = "DROP TABLE IF EXISTS " + TABLE_SONGS;
        db.execSQL(dropTableSql);
        onCreate(db);
    }

    public long insertSong(String title, String singer, int year, int rating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_SINGER, singer);
        values.put(COLUMN_YEAR, year);
        values.put(COLUMN_RATING, rating);
        long result = db.insert(TABLE_SONGS, null, values);
        db.close();
        return result;
    }

    public ArrayList<Songs> getSongs() {
        ArrayList<Songs> songsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_SONGS;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                String title = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE));
                String singer = cursor.getString(cursor.getColumnIndex(COLUMN_SINGER));
                int year = cursor.getInt(cursor.getColumnIndex(COLUMN_YEAR));
                int rating = cursor.getInt(cursor.getColumnIndex(COLUMN_RATING));
                Songs song = new Songs(id, title, singer, year, rating);
                songsList.add(song);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songsList;
    }

    public boolean updateSong(Songs song) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, song.getTitle());
        values.put(COLUMN_SINGER, song.getSinger());
        values.put(COLUMN_YEAR, song.getYear());
        values.put(COLUMN_RATING, song.getRating());
        int rowsAffected = db.update(TABLE_SONGS, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(song.getId())});
        db.close();
        return rowsAffected > 0;
    }

    public boolean deleteSong(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsAffected = db.delete(TABLE_SONGS, COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
        return rowsAffected > 0;
    }
}
