/*
package com.example.yukunlin.physiotherapydevice.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.yukunlin.physiotherapydevice.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

*/
/**
 * Created by yukunlin on 2017/1/16.
 *//*


public class ImageViewAdapter extends BaseAdapter {
    private Context context;

    private int[] images = null;

    public int[] getImages() {
        return images;
    }

    public void setImages(int[] images) {
        this.images = images;
    }

    public ImageViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int i) {
        return images[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_guide, viewGroup, false);
            holder = new ImageViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ImageViewHolder) view.getTag();
        }
//        Picasso.with(context).load(images[i]).into(holder.imageView);
        Glide.with(context).load(images[i]).crossFade().into(holder.imageView);
//        holder.imageView.setImageResource(images[i]);
        return view;
    }

    class ImageViewHolder {
        @InjectView(R.id.imageView)
        ImageView imageView;

        public ImageViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
*/
