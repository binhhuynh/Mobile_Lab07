package com.iuh.mobile_lab07;

import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private List<Location> locations;
    private LocationDao locationDao;
    private Listener listener;

    public interface Listener {
        void onEdit(int p);
        void onDelete(int p);
    }


    public CustomAdapter(List<Location> locations, LocationDao locationDao) {
        this.locations = locations;
        this.locationDao = locationDao;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(cv, new Listener() {

            @Override
            public void onEdit(int p) {
                Toast toast = Toast.makeText(parent.getContext(), "1", Toast.LENGTH_SHORT);
                toast.show();
            }

            @Override
            public void onDelete(int p) {
                TextView location = cv.findViewById(R.id.tvLocation);
                locationDao.delete(String.valueOf(location.getText()));
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        TextView tvID = (TextView) cardView.findViewById(R.id.tvID);
        TextView tvLocation = (TextView) cardView.findViewById(R.id.tvLocation);
        tvID.setText(String.valueOf(locations.get(position).getId()));
        tvLocation.setText(String.valueOf(locations.get(position).getName()));
    }
    @Override
    public int getItemCount() {
        return locations.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private Listener listener;
        private CardView cardView;
        private ImageButton btnEdit;
        private ImageButton btnDelete;
        private EditText name;

        public ViewHolder(CardView v, Listener listener) {
            super(v);
            cardView = v;

            btnEdit = cardView.findViewById(R.id.ibUpdate);
            btnDelete = cardView.findViewById(R.id.ibDelete);

            this.listener = listener;

            btnEdit.setOnClickListener(this);
            btnDelete.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ibUpdate:
                    listener.onEdit(this.getLayoutPosition());
                    break;
                case R.id.ibDelete:
                    listener.onDelete(this.getLayoutPosition());
                    break;
                default:
                    break;
            }
        }
    }
}