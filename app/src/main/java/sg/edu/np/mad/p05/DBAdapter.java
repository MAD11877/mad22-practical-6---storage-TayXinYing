package sg.edu.np.mad.p05;

import static sg.edu.np.mad.p05.ListActivity.userList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBAdapter extends SQLiteOpenHelper
{
    private String TableName = "User";
    private String ColumnName = "Name";
    private String ColumnDescription = "Description";
    private String ColumnID = "ID";
    private String ColumnFollowed = "Followed";

    public DBAdapter(Context context)
    {
        super(context, "userDB.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CreateTable = "CREATE TABLE "
                + TableName
                +"(" + ColumnName + " TEXT,"
                + ColumnDescription + " TEXT,"
                + ColumnID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ColumnFollowed + " INTEGER" + ")";

        db.execSQL(CreateTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS User");
        onCreate(db);
    }

    public void addUser (User user)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ColumnName,user.name);
        values.put(ColumnDescription, user.description);
        values.put(ColumnFollowed, user.followed);
        db.insert(TableName,null,values);
        db.close();
    }

    public ArrayList<User> getUsers()
    {
        ArrayList<User> userList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM User",null);

        while (cursor.moveToNext())
        {
            User user = new User();
            user.name = cursor.getString(0);
            user.description = cursor.getString(1);
            user.id = cursor.getInt(2);
            user.followed = follow_boolean(cursor.getInt(3));

            userList.add(user);
        }
        cursor.close();
        db.close();
        return userList;
    }

    public void updateUser(User user)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Integer Followed_Int = follow_int(user.followed);
        String query = "UPDATE User SET Followed = \"" + Followed_Int + "\" WHERE Name = \"" + user.name + "\"";
        db.execSQL(query);
        db.close();
    }

    public User getSpecificUser(Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM User WHERE ID = " + "\"" + id + "\"";
        Cursor cursor = db.rawQuery(query,null);

        User user = new User();

        //Return Boolean Value
        if (cursor.moveToFirst())
        {
            Boolean Followed_Bool = follow_boolean(cursor.getInt(3));
            user.name = cursor.getString(0);
            user.description = cursor.getString(1);
            user.id = cursor.getInt(2);
            user.followed = Followed_Bool;
        }
        db.close();
        return user;
    }

    //Method to convert followed variable from bool to int
    public int follow_int(Boolean b)
    {
        if (b == true)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }

    //Method to convert followed variable from int to bool
    public boolean follow_boolean(Integer i)
    {
        if (i == 1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
