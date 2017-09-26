package bw.com.yuekaolx;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 索园 on 2017/8/23.
 */
public class SQlite extends SQLiteOpenHelper {

    public SQlite(Context context) {
        super(context, "news.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE news(id integer primary key autoincrement," +
                "item_id varchar(20),title varchar(100)," +
                "source varchar(100),article_url varchar(100))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
