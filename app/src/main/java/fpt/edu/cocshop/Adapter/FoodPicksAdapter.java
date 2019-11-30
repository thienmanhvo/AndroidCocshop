package fpt.edu.cocshop.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import fpt.edu.cocshop.Model.Brand;
import fpt.edu.cocshop.R;

public class FoodPicksAdapter extends RecyclerView.Adapter<FoodPicksAdapter.ViewHolder> {

    public interface OnFoodPicksClickListener {
        void onClick(Brand brand);
    }

    private Context mContext;
    private List<Brand> mBrandList;
    private OnFoodPicksClickListener mOnFoodPicksClickListener;

    public void setmOnFoodPicksClickListener(OnFoodPicksClickListener mOnFoodPicksClickListener) {
        this.mOnFoodPicksClickListener = mOnFoodPicksClickListener;
    }

    public FoodPicksAdapter(Context mContext, List<Brand> mBrandList) {
        this.mContext = mContext;
        this.mBrandList = mBrandList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_brand, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.mTxtName.setText(mBrandList.get(position).getName());
        holder.mTxtLocation.setText(mBrandList.get(position).getLocation().toString());
        Picasso.get()
                .load(mBrandList.get(position).getPicturePath())
                .error(R.drawable.ic_launcher_background)
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.mImgDescription, new Callback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError(Exception e) {
                        Log.e("PICASSO", e.getMessage());
                    }
                });
        holder.mImgDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnFoodPicksClickListener.onClick(mBrandList.get(position));
            }
        });
        holder.mRbRating.setRating(mBrandList.get(position).getRating());
    }

    @Override
    public int getItemCount() {
        return mBrandList != null ? mBrandList.size() : 0;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImgDescription;
        private TextView mTxtName, mTxtLocation;
        private TextView mTxtPromo;
        private RatingBar mRbRating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImgDescription = itemView.findViewById(R.id.img_description);
            mTxtName = itemView.findViewById(R.id.txt_name_brand);
            mTxtLocation = itemView.findViewById(R.id.txt_location);
            mTxtPromo = itemView.findViewById(R.id.txt_promo);
            mRbRating = itemView.findViewById(R.id.rb_rating);
        }
    }
}
