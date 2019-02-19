package com.charoniv.jin.charon.notice;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class NoticeDatabase {

    private SQLiteDatabase sqLiteDatabase;
    private static final String TAG = "NoticeDatabase";

    public NoticeDatabase(Context context) {
        sqLiteDatabase = context.openOrCreateDatabase("notice_database.db", MODE_PRIVATE, null );
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS notices(_id INTEGER PRIMARY KEY, info TEXT, date TEXT)");
    }

    public List<Notice> getNotices(){
        List<Notice> notices = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM notices ORDER BY _id DESC", null);
        if(cursor.moveToFirst()){
            do {
                notices.add(new Notice(
                                cursor.getInt(0),
                                cursor.getString(1),
                                cursor.getString(2)
                        )
                );
                Log.d(TAG, "getNotices: " + notices);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return notices;
    }

    public void updateNotice(List<Notice> notices){
        sqLiteDatabase.execSQL("DELETE FROM notices");
        for (Notice notice: notices) {
            SQLiteStatement sql = sqLiteDatabase.compileStatement("INSERT INTO notices VALUES(?,?,?)");
            sql.bindString(1, ""+notice.get_id());
            sql.bindString(2, notice.getInfo());
            sql.bindString(3, notice.getDate());
            sql.execute();
        }
    }

}
