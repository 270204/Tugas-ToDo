package ap.mobile.to_do;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class ActivityAdapter extends ArrayAdapter<Activity> {

    public ActivityAdapter(Context context, ArrayList<Activity> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Activity item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
        tv.setText(item.getId() + ". " + item.getToDo() + " dimulai " + item.getTime());

        return convertView;
    }
} 