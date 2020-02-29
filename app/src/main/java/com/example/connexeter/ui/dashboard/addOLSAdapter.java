package com.example.connexeter.ui.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.connexeter.R;

import java.util.List;

public class addOLSAdapter extends RecyclerView.Adapter<addOLSAdapter.ProductViewHolder> {

    private Context mCtx;
    private List<addOLS> productList;

    public addOLSAdapter(Context mCtx, List<addOLS> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.add_class, parent, false);
        ProductViewHolder holder = new ProductViewHolder(view);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        addOLS product = productList.get(position);
        holder.format.setText(product.getFormat());
        holder.className.setText(product.getClassName());
        holder.classLevel.setText(product.getClassLevel());
        holder.roomNumber.setText(product.getRoomNumber());
        holder.teacherName.setText(product.getTeacherName());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView format, className, classLevel, roomNumber, teacherName;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            format = itemView.findViewById(R.id.format);
            className = itemView.findViewById(R.id.className);
            classLevel = itemView.findViewById(R.id.classLevel);
            roomNumber = itemView.findViewById(R.id.roomNumber);
            teacherName = itemView.findViewById(R.id.teacherName);
        }
    }
}
