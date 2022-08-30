package com.yan.recycleViewDemo.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.yan.recycleViewDemo.R;
import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private Activity context;
    private List<String> dataSource = new ArrayList<>();
    private ItemOnClickListener onClickListener;
    private ItemOnLongClickListener onLongClickListener;

    public CustomAdapter(Activity context){
        this.context = context;
    }

    public void setData(List<String> listTemp){
        dataSource = listTemp;
        notifyDataSetChanged();
    }

    /**
     * 指定位置添加行
     * @param position
     */
    public void addRow(int position){
        dataSource.add(position,"insert"+position);
        notifyItemInserted(position);

        if(position != getItemCount())
            notifyItemRangeChanged(position,getItemCount());
    }

    /**
     * 删除行
     * @param position
     */
    public void removeRow(int position){
        dataSource.remove(position);
        notifyItemRemoved(position);

        if(position != getItemCount())
            notifyItemRangeChanged(position,getItemCount());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position) {
        int i = position;
        String content = dataSource.get(position);
        holder.tvContent.setText(content);

        holder.tvContent.getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClick(i);
            }
        });

        holder.tvContent.getRootView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onLongClickListener.onLongClick(i);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvContent;
        public ViewHolder(View itemView) {
            super(itemView);

            tvContent = itemView.findViewById(R.id.tvContent);
        }
    }

    public interface ItemOnClickListener{
        void onClick(int position);
    }

    public void setItemOnClickListener(ItemOnClickListener l){
        this.onClickListener = l;
    }

    public interface ItemOnLongClickListener{
        void onLongClick(int position);
    }

    public void setItemOnLongCLickListener(ItemOnLongClickListener l){
        this.onLongClickListener = l;
    }
}
