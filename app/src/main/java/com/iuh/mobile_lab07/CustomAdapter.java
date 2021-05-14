package com.iuh.mobile_lab07;

import android.app.Activity;
import android.app.Dialog;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.iuh.mobile_lab07.dao.LocationDao;
import com.iuh.mobile_lab07.db.TravelDatabase;
import com.iuh.mobile_lab07.entity.Location;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{
    private List<Location> locationList;
    private Activity context;
    private TravelDatabase database;

    public CustomAdapter(Activity context, List<Location> locationList) {
        this.context = context;
        this.locationList = locationList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {
        Location location = locationList.get(position);

        database = TravelDatabase.getInstance(context);

        holder.textViewID.setText(String.valueOf(location.getId()));
        holder.textViewName.setText(location.getName());

        holder.ibEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Location l = locationList.get(holder.getAdapterPosition());

                int ID = l.getId();

                String name = l.getName();

                Dialog dialog = new Dialog(context);

                dialog.setContentView(R.layout.dialog_update);

                int width = WindowManager.LayoutParams.MATCH_PARENT;

                int height = WindowManager.LayoutParams.WRAP_CONTENT;

                dialog.getWindow().setLayout(width, height);

                dialog.show();

                EditText editText = dialog.findViewById(R.id.edit_text);
                Button ibUpdate = dialog.findViewById(R.id.btn_update);

                editText.setText(name);

                ibUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();

                        String uText = editText.getText().toString().trim();

                        database.locationDao().update(ID, uText);

                        locationList.clear();
                        locationList.addAll(database.locationDao().getAll());
                        notifyDataSetChanged();
                    }
                });

                holder.ibDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Location d = locationList.get(holder.getAdapterPosition());

                        database.locationDao().delete(d);

                        int position = holder.getAdapterPosition();
                        locationList.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, locationList.size());
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return locationList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewID, textViewName;
        ImageButton ibEdit, ibDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewID = itemView.findViewById(R.id.tvID);
            textViewName = itemView.findViewById(R.id.tvLocation);
            ibEdit = itemView.findViewById(R.id.ibUpdate);
            ibDelete = itemView.findViewById(R.id.ibDelete);
        }
    }
}