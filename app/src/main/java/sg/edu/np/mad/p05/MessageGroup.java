package sg.edu.np.mad.p05;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MessageGroup extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_group);

        Button btn_group1, btn_group2;

        btn_group1 = findViewById(R.id.button_group1);
        btn_group2 = findViewById(R.id.button_group2);

        btn_group1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                replaceFragment(new Frame_Group1());
            }
        });

        btn_group2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                replaceFragment(new Frame_Group2());
            }
        });
    }

    public void replaceFragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction(); // Create the transaction
        fragmentTransaction.replace(R.id.frameLayout,fragment); // Replace the content of the container
        fragmentTransaction.commit(); //Commit the changes
    }
}