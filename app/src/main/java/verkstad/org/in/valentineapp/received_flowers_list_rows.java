package verkstad.org.in.valentineapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by anu on 1/16/2016.
 */
public class received_flowers_list_rows extends BaseAdapter {
    Context context;
    String[] name_array;
    int[] image_array;
    String[] message_array;
    TextView name,message;
    ImageView profile_pic;
    LayoutInflater layoutInflater;
    public received_flowers_list_rows(Activity context,int[] image_array,String[] name_array,String[] message_array){
    this.context = context;
    this.image_array=image_array;
    this.name_array=name_array;
    this.message_array=message_array;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

       // convertView=layoutInflater.inflate(R.layout.received_flowers_list_rows,null);
        View rowView = layoutInflater.inflate(R.layout.received_flowers_list_rows,null);
        name = (TextView) rowView.findViewById(R.id.name);
        message = (TextView) rowView.findViewById(R.id.message);
        profile_pic = (ImageView) rowView.findViewById(R.id.list_image);
        name.setText(name_array[position]);
        message.setText(message_array[position]);
        profile_pic.setImageResource(image_array[position]);

        return rowView;
    }
}
