package com.example.connexeter.ui.UserInputOLS;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.connexeter.R;

import java.util.List;

public class inputClassAdapter extends RecyclerView.Adapter<inputClassAdapter.inputClassViewHolder>{

    private Context mCtx;
    private List<inputClass> productList;

    public inputClassAdapter(Context mCtx, List<inputClass> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @NonNull
    @Override
    public inputClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.user_input_class, null);
        return new inputClassViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull inputClassViewHolder holder, int position) {
        inputClass product = productList.get(position);
        holder.formatLetter.setText(product.getFormatLetter());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class inputClassViewHolder extends RecyclerView.ViewHolder {
        TextView formatLetter;

        public inputClassViewHolder(@NonNull View itemView) {
            super(itemView);

            formatLetter = itemView.findViewById(R.id.formatLetter);
        }
    }
}
