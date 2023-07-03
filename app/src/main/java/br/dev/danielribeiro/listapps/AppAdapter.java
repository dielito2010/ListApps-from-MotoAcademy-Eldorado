package br.dev.danielribeiro.listapps;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AppAdapter extends RecyclerView.Adapter<AppAdapter.AppViewHolder> {

    private List<String> appNames;
    private int selectedPosition = RecyclerView.NO_POSITION;

    public AppAdapter(List<String> appNames) {
        this.appNames = appNames;
    }

    @NonNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_app, parent, false);
        final AppViewHolder viewHolder = new AppViewHolder(view);
        viewHolder.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    selectedPosition = position;
                    notifyDataSetChanged();
                }
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AppViewHolder holder, int position) {
        String appName = appNames.get(position);
        holder.radioButton.setText(appName);
        holder.radioButton.setChecked(position == selectedPosition);
    }

    @Override
    public int getItemCount() {
        return appNames.size();
    }

    public class AppViewHolder extends RecyclerView.ViewHolder {

        RadioButton radioButton;

        public AppViewHolder(@NonNull View itemView) {
            super(itemView);
            radioButton = itemView.findViewById(R.id.radioButton);
        }
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }
}
