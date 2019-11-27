package com.example.checknavview.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.checknavview.R;

public class GridAdapter extends BaseAdapter {

    Context context;
    private final String[] values;
    private final int[] images;
    View view;
    LayoutInflater layoutInflater;

    public GridAdapter(Context context, String[] values, int[] images) {
        this.context = context;
        this.values = values;
        this.images = images;
    }

    @Override
    public int getCount() {
        return values.length;
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
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            view = new View(context);
            view = layoutInflater.inflate(R.layout.single_item, null);
            //Change this to image button later (both here and in single_item.xml)
            ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
            TextView textView = (TextView) view.findViewById(R.id.textview);

            imageView.setImageResource(images[position]);
            textView.setText(values[position]);

//            Holder holder=new Holder();
//            View rowView;
//
//            rowView = inflater.inflate(R.layout.sample_gridlayout, null);
//            holder.os_text =(TextView) rowView.findViewById(R.id.os_texts);
//            holder.os_img =(ImageView) rowView.findViewById(R.id.os_images);
//
//            holder.os_text.setText(result[position]);
//            holder.os_img.setImageResource(imageId[position]);
//
//            rowView.setOnClickListener(new OnClickListener() {
//
//                @Override
//                public void onClick(View v) {
//                    // TODO Auto-generated method stub
//                    Toast.makeText(context, "You Clicked "+result[position], Toast.LENGTH_SHORT).show();
//                }
//            });
//
//            return rowView;
        }
        return view;
    }
}
