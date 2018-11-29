package com.example.mvvm.view.project;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mvvm.R;
import com.example.mvvm.model.project.ProjectItem;

import java.util.List;


/**
 * 时间：2018/11/28 15:39
 * 描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 *
 * @author WangYoule
 * @qq 270628297
 */
public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder> {
    private List<ProjectItem> data;

    public ProjectAdapter() {
    }

    @NonNull
    @Override
    public ProjectAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.f_item_project, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectAdapter.ViewHolder holder, int position) {
        ProjectItem item = data.get(position);
        holder.tvName.setText(item.getFull_name());
        holder.tvStar.setText(item.getStargazers_count() + " star");
    }

    public void setData(List<ProjectItem> data) {
        if (null != data)
            this.data = data;
        else
            this.data.clear();

        notifyDataSetChanged();
    }

    public void addData(List<ProjectItem> data) {
        if (null == data || data.size() == 0)
            return;

        int index = getItemCount();
        this.data.addAll(data);
        notifyItemRangeInserted(index, data.size());
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvStar;

        ViewHolder(View view) {
            super(view);
            this.tvName = view.findViewById(R.id.tv_name);
            this.tvStar = view.findViewById(R.id.tv_star);
        }
    }
}
