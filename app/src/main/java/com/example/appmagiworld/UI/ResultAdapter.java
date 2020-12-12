
package com.example.appmagiworld.UI;


import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.appmagiworld.Models.ResultGame;
import com.example.appmagiworld.R;

import java.util.List;

public class ResultAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<ResultGame> mListResult;

    public class ResultViewHolder extends RecyclerView.ViewHolder {
        TextView result;
        ImageView imageView;

        public ResultViewHolder(View itemView) {
            super(itemView);
            result = itemView.findViewById(R.id.result);
            imageView = itemView.findViewById(R.id.imagePlayerAttack);

        }
    }

    public class SectionViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public SectionViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
        }
    }

    public ResultAdapter(List<ResultGame> listResult) {
        mListResult = listResult;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View v = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);

            v.findViewById(android.R.id.text1).setBackgroundColor(Color.LTGRAY);
            return new SectionViewHolder(v);

        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_result, parent, false);
            ResultViewHolder resultViewHolder = new ResultViewHolder(view);
            return resultViewHolder;

        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ResultGame item = mListResult.get(position);

        if (item.isRow()) {
            ResultViewHolder h1 = (ResultViewHolder) holder;
            ResultGame result = mListResult.get(position);
            h1.imageView.setImageResource(result.imageId);
            h1.result.setText(result.getResult());
        } else {
            SectionViewHolder h = (SectionViewHolder) holder;
            h.textView.setText(item.getSection(position));
        }

    }

    @Override
    public int getItemCount() {
        return mListResult.size();
    }

    @Override
    public int getItemViewType(int position) {
        super.getItemViewType(position);
        ResultGame item = mListResult.get(position);
        if (!item.isRow()) {
            return 0;
        } else {

            return 1;
        }
    }
}