package com.example.connexeter.ui.dashboard.ChangeFormatValues;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.connexeter.R;

import java.util.List;

public class addFormatAdapter extends RecyclerView.Adapter<addFormatAdapter.ProductViewHolder> {

    private Context mCtx;
    private List<addFormat> addFormatList;

    public addFormatAdapter(Context mCtx, List<addFormat> addFormatList) {
        this.mCtx = mCtx;
        this.addFormatList = addFormatList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.edit_a_format,null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        addFormat format = addFormatList.get(position);

        holder.formatLetter.setText(format.getFormatLetter());
    }

    @Override
    public int getItemCount() {
        return addFormatList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder{

        TextView formatLetter;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            formatLetter = itemView.findViewById(R.id.AFormat);
        }
    }
}