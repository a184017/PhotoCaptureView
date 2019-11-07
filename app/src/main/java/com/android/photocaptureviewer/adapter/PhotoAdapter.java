package com.android.photocaptureviewer.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.photocaptureviewer.R;
import com.android.photocaptureviewer.activity.ImagePreviewActivity;
import com.android.photocaptureviewer.data.ImageFolder;
import com.android.photocaptureviewer.data.ImageItem;
import com.android.photocaptureviewer.utils.ImageTimeUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {
    private List<ImageItem> mImageItems;
    private Activity mActivity;

    public PhotoAdapter(Activity activity) {
        this.mActivity = activity;
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.photo_item, parent, false);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        final ImageItem imageItem = mImageItems.get(position);
        holder.date.setText(ImageTimeUtils.timeStamp2Date(imageItem.time * 1000, null));
        holder.name.setText(imageItem.name);
//        Bitmap bitmap = ImageUtils.getImageBitmap(activity, imageItem.path);
//        Bitmap thumbnailBitmap = ThumbnailUtils.extractThumbnail(bitmap, 160, 160);
//        if (bitmap != thumbnailBitmap && (!bitmap.isRecycled())) {
//            bitmap.recycle();
//        }
//        holder.thumbnail.setImageBitmap(thumbnailBitmap);
        Picasso.with(mActivity).load("file://"+imageItem.path).into(holder.thumbnail);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, ImagePreviewActivity.class);
                intent.putExtra(ImagePreviewActivity.IMAGE_PATH, imageItem.path);
                mActivity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mImageItems == null) {
            return 0;
        } else {
            return mImageItems.size();
        }
    }

    public void setData(List<ImageFolder> imageSetList) {
        mImageItems = imageSetList.get(0).imageItems;
    }

    static class PhotoViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView date;
        public ImageView thumbnail;

        public PhotoViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            date = view.findViewById(R.id.date);
            thumbnail = view.findViewById(R.id.thumbnail);
        }
    }
}
