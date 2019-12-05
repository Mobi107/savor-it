package com.example.savor_it.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.savor_it.R;
import com.example.savor_it.model.Recipe;

import java.util.List;

public class GridAdapter extends BaseAdapter {

    Context context;
    View view;
    LayoutInflater layoutInflater;
    private List<Recipe> recipeList;

    public GridAdapter(Context context, List<Recipe> recipeList) {
        this.context = context;
//        this.values = values;
//        this.images = images;
        this.recipeList = recipeList;
    }

    @Override
    public int getCount() {
        return recipeList.size();
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
        Recipe currRecipe = this.recipeList.get(position);
        if (convertView == null) {
            view = new View(context);
            view = layoutInflater.inflate(R.layout.single_item, null);
            //Change this to image button later (both here and in single_item.xml)
            ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
            TextView textView = (TextView) view.findViewById(R.id.textview);
            textView.setText(currRecipe.getTitle());
            ImageView recipeOwnerImageView = view.findViewById(R.id.recipeOwnerImageView);
            recipeOwnerImageView.setImageResource(R.drawable.ic_perm_identity);
            TextView ownerTextView = view.findViewById(R.id.recipeOwnerNameTextView);
            ownerTextView.setText(currRecipe.getOwnerName());
            imageView.setImageURI(currRecipe.getPhoto());
            LinearLayout recipeGridItemLayout = view.findViewById(R.id.recipeGridItemLayout);
            recipeGridItemLayout.setTag(currRecipe);
//            imageView.setImageDrawable(this.context.getDrawable());

//            imageView.setImageDrawable(this.context.getDrawable(images[position]));
//            imageView.setImageResource(images[position]);
//            textView.setText(values[position]);

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
