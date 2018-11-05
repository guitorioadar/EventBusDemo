package com.vaidoos.eventbusdemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.vaidoos.eventbusdemo.events.CategoryIdEvent;
import com.vaidoos.eventbusdemo.interfaces.OnDataPassListener;
import com.vaidoos.eventbusdemo.model.AllCategoryModel;
import com.vaidoos.eventbusdemo.R;

import org.greenrobot.eventbus.EventBus;

public class MainCategoryAdapter extends RecyclerView.Adapter<MainCategoryAdapter.AllProductAdapterViewHolder> {

    private static final String TAG = "MainCategoryAdapter";

    private Context context;
    private AllCategoryModel[] allCategoryModels;
    //private List<AllCategoryModel> allCategoryModels;

    int selected_position = 0; // You have to set this globally in the Adapter class

    private OnDataPassListener onDataPassListener;

    int row_index = 0;


    public MainCategoryAdapter(Context context, AllCategoryModel[] allCategoryModels) {
        //public AllCategoryAdapter(Context context, List<AllCategoryModel> allCategoryModels) {
        this.context = context;
        this.allCategoryModels = allCategoryModels;
    }

    @NonNull
    @Override
    public AllProductAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_all_category_items, parent, false);
        return new AllProductAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllProductAdapterViewHolder holder, int position) {

        //homeActivity = (HomeActivity) context;

        final AllCategoryModel allCategoryModel = allCategoryModels[position];
        //AllCategoryModel allCategoryModel = allCategoryModels.get(position);

        holder.textViewProductName.setText(allCategoryModel.getPcCategoryName());
        holder.textViewProductAvailableCount.setVisibility(View.GONE);
        holder.textViewProductPreviousPrice.setVisibility(View.GONE);
        holder.textViewProductCategory.setVisibility(View.GONE);
        holder.textViewProductDiscountPercentage.setVisibility(View.GONE);
        holder.textViewProductPrice.setVisibility(View.GONE);
        holder.textViewProductReviewUsers.setVisibility(View.GONE);
        holder.textViewProductRating.setVisibility(View.GONE);


        Glide.with(context)
                .load(allCategoryModel.getPcPICLocate())
                .into(holder.imageViewProductThumb);


        //holder.setItemCLickListener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, allCategoryModel.getPcCategoryID(), Toast.LENGTH_SHORT).show();
                onDataPassListener.onDataPass(allCategoryModel.getPcCategoryID());
            }
        });


    }

    @Override
    public int getItemCount() {
        //return allCategoryModels.size();
        return allCategoryModels.length;
    }

    public class AllProductAdapterViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageViewProductThumb;
        private TextView textViewNew, textViewProductName, textViewProductCategory, textViewProductPrice, textViewProductPreviousPrice, textViewProductDiscountPercentage, textViewProductRating, textViewProductReviewUsers, textViewProductAvailableCount;


        public AllProductAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewNew = itemView.findViewById(R.id.textViewNew);
            imageViewProductThumb = itemView.findViewById(R.id.imageViewProductThumb);
            textViewProductName = itemView.findViewById(R.id.textViewProductName);
            textViewProductCategory = itemView.findViewById(R.id.textViewProductCategory);
            textViewProductPrice = itemView.findViewById(R.id.textViewProductPrice);
            textViewProductPreviousPrice = itemView.findViewById(R.id.textViewProductPreviousPrice);
            textViewProductDiscountPercentage = itemView.findViewById(R.id.textViewProductDiscountPercentage);
            textViewProductRating = itemView.findViewById(R.id.textViewProductRating);
            textViewProductReviewUsers = itemView.findViewById(R.id.textViewProductReviewUsers);
            textViewProductAvailableCount = itemView.findViewById(R.id.textViewProductAvailableCount);

        }
    }

    public void setOnDataPassListener(OnDataPassListener onDataPassListener){
        this.onDataPassListener = onDataPassListener;
    }

}

