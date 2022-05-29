package sg.edu.np.mad.p05;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ListUserViewHolder extends RecyclerView.ViewHolder
{
    TextView usrName;
    TextView usrDesc;
    ImageView usrPic;

    public ListUserViewHolder(View item)
    {
        super(item);
        usrName = item.findViewById(R.id.username);
        usrDesc = item.findViewById(R.id.userdescription);
        usrPic = item.findViewById(R.id.userpic);
    }
}
