package com.example.listapps2;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AppAdapter extends RecyclerView.Adapter<AppAdapter.AppViewHolder> {

    private Context context;
    private List<ApplicationInfo> appList;
    private int selectedPosition = RecyclerView.NO_POSITION;

    public AppAdapter(Context context, List<ApplicationInfo> appList) {
        this.context = context;
        this.appList = appList;
    }

    @NonNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_app, parent, false);
        return new AppViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppViewHolder holder, int position) {
        ApplicationInfo appInfo = appList.get(position);
        PackageManager packageManager = context.getPackageManager();
        Drawable appIcon = appInfo.loadIcon(packageManager);
        String appName = appInfo.loadLabel(packageManager).toString();

        holder.appIconImageView.setImageDrawable(appIcon);
        holder.appNameTextView.setText(appName);

        holder.radioButton.setChecked(position == selectedPosition);
        holder.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition = holder.getAdapterPosition();
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return appList.size();
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public class AppViewHolder extends RecyclerView.ViewHolder {

        ImageView appIconImageView;
        TextView appNameTextView;
        RadioButton radioButton;

        public AppViewHolder(@NonNull View itemView) {
            super(itemView);
            appIconImageView = itemView.findViewById(R.id.appIconImageView);
            appNameTextView = itemView.findViewById(R.id.appNameTextView);
            radioButton = itemView.findViewById(R.id.radioButton);
        }
    }
}

