package sg.edu.np.mad.p05;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ListUserAdapter extends RecyclerView.Adapter<ListUserViewHolder>
{
    ArrayList<User> data;
    public ListUserAdapter(ArrayList<User> data)
    {
        this.data = data;
    }

    public int getItemViewType(int position)
    {
        int viewType;
        if(data.get(position).name.endsWith("7"))
        {
            viewType = 1;
        }
        else{
            viewType = 0;
        }
        return viewType;
    }

    @NonNull
    @Override
    public ListUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View item = null;
        if(viewType == 1)
        {
            item = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.activity_list_viewholder_2,parent,false);
        }
        else
        {
            item = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.activity_list_viewholder,parent,false);
        }
        return new ListUserViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ListUserViewHolder holder, int position)
    {
        String username = data.get(position).name;
        String description = data.get(position).description;
        holder.usrName.setText(username);
        holder.usrDesc.setText(description);
        holder.usrPic.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Context context = holder.usrPic.getContext();
                AlertDialog alert = ListActivity.createDialogAlert(holder.getAdapterPosition(), context);
                alert.show();
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return data.size();
    }
}
