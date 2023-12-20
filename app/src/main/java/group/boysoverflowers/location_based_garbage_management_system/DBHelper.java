package group.boysoverflowers.location_based_garbage_management_system;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import group.boysoverflowers.location_based_garbage_management_system.models.Bin;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "Login.db";

    public DBHelper(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("CREATE TABLE users(username TEXT primary key, password TEXT, email TEXT)");
        MyDB.execSQL("CREATE TABLE bins(id INTEGER PRIMARY KEY AUTOINCREMENT, city TEXT, barangay TEXT, street TEXT," +
                "schedule TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
    }

    public Boolean checkUsername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkUsernamePassword(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[]{username, password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    /**
     * This code is used to create data in the bins table
     */
    public Boolean insertDataToBins(Bin bin) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("city", bin.getCity());
        contentValues.put("barangay", bin.getBarangay());
        contentValues.put("street", bin.getStreet());
        contentValues.put("schedule", bin.getSchedule());

        long result = MyDB.insert("bins", null, contentValues);

        if (result == -1) return false;
        else
            return true;
    }

    /**
     * This code will check the data if it is already existed in bins table
     */
    public boolean checkBinIfExists(Bin bin) {
        SQLiteDatabase MyDB = this.getWritableDatabase();

        Cursor cursor = MyDB.rawQuery("SELECT * FROM bins where city = ? " +
                        "AND barangay = ? AND street = ? AND schedule = ?",
                new String[]{bin.getCity(), bin.getBarangay(), bin.getStreet(), bin.getSchedule()});

        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This code is used to create data in the users table
     */
    public Boolean insertData(String username, String password, String email) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("email", email);
        long result = MyDB.insert("users", null, contentValues);
        if (result == -1) return false;
        else
            return true;
    }

    /**
     * This code used to get all information in bins table
     */
    public ArrayList<Bin> getAllBins() {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM bins", null);

        ArrayList<Bin> bins = new ArrayList<>();

        while (cursor.moveToNext()) {
            Bin bin = new Bin();
            bin.setId(cursor.getInt(0));
            bin.setCity(cursor.getString(1));
            bin.setBarangay((cursor.getString(2)));
            bin.setStreet(cursor.getString(3));
            bin.setSchedule(cursor.getString(4));

            bins.add(bin);
        }

        return bins;
    }

    /**
     * This code use to delete bin entry to the database using it's id
     */
    public boolean deleteBinById(String id) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM bins WHERE id = ?", new String[]{id});

        if (cursor.getCount() > 0) {
            int result = MyDB.delete("bins", "id = ?", new String[]{id});

            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public boolean updateBin(Bin bin) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM bins WHERE id = ?", new String[]{Integer.toString(bin.getId())});

        ContentValues contentValues = new ContentValues();

        contentValues.put("city", bin.getCity());
        contentValues.put("barangay", bin.getBarangay());
        contentValues.put("street", bin.getStreet());
        contentValues.put("schedule", bin.getSchedule());

        if (cursor.getCount() > 0) {
            int result = MyDB.update("bins", contentValues, "id = ?",
                    new String[]{Integer.toString(bin.getId())});

            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
}
