package sg.edu.np.mad.p05;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity
{
    public static String correctPassword;
    public static String correctUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText username = findViewById(R.id.usernameBox);
        EditText password = findViewById(R.id.passwordBox);

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://madpractical---tay-xin-y-ed7d1-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference("User");

        myRef.child("mad").child("username").addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                String value = dataSnapshot.getValue(String.class);
                correctUsername = value;
            }

            @Override
            public void onCancelled(DatabaseError error)
            {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        myRef.child("mad").child("password").addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                String value = dataSnapshot.getValue(String.class);
                correctPassword = value;
            }

            @Override
            public void onCancelled(DatabaseError error)
            {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        Button login_Btn = findViewById(R.id.login_button);
        login_Btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if((correctPassword.equals(password.getText().toString())) && (correctUsername.equals(username.getText().toString())))
                {
                    Intent i = new Intent(LoginActivity.this,ListActivity.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(LoginActivity.this,"Incorrect username or password",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}