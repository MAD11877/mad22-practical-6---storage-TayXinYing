package sg.edu.np.mad.p05;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity
{
    public static ArrayList<User> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // Create a list of 20 random users
        for (int i = 0; i < 20; i++)
        {
            Random random = new Random();
            User randomUser = new User("Name" + Math.abs(random.nextInt()), "Description " + Math.abs(random.nextInt()), i, random.nextBoolean());
            userList.add(randomUser);
        }

        DBAdapter dbAdapter = new DBAdapter(this);
        for (User user : userList)
        {
            dbAdapter.addUser(user);
        }

        // RecyclerView
        RecyclerView recView = findViewById(R.id.recyclerView);
        ListUserAdapter adapter = new ListUserAdapter(dbAdapter.getUsers());

        LinearLayoutManager layoutManager = new LinearLayoutManager(ListActivity.this);

        recView.setLayoutManager(layoutManager);
        recView.setItemAnimator(new DefaultItemAnimator());
        recView.setAdapter(adapter);
    }

    public static AlertDialog createDialogAlert(Integer position, Context context)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        // Setting the Title & Message
        builder.setTitle("Profile");
        builder.setMessage(userList.get(position).name);
        builder.setCancelable(true);

        builder.setPositiveButton("VIEW", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int i)
            {
                Intent activity = new Intent(context, MainActivity.class);
                activity.putExtra("userPosition", position);
                context.startActivity(activity);
            }
        });

        builder.setNegativeButton("CLOSE", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int id)
            {
                // User cancelled the dialog
            }
        });

        AlertDialog alert = builder.create();
        return alert;
    }
}