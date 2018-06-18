package valencia.oscar.w6d1_as2;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteDatabase.loadLibs(this);
        insertSthToDb();
    }

    private void insertSthToDb() {
        SQLiteDatabase db = FeedReaderDbHelper.getInstance(this).getWritableDatabase("somePass");

        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_ENTRY_ID, 1);
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, "Easter Bunny has escaped!");
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE, "A thrilling story which proves how fragile our hearts are...");

        db.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values);

        Cursor cursor = db.rawQuery("SELECT * FROM '" + FeedReaderContract.FeedEntry.TABLE_NAME + "';", null);
        Log.d(MainActivity.class.getSimpleName(), "Rows count: " + cursor.getCount());
        cursor.close();
        db.close();

        // this will throw net.sqlcipher.database.SQLiteException: file is encrypted or is not a database: create locale table failed
        //db = FeedReaderDbHelper.getInstance(this).getWritableDatabase("");
    }
}
