package android.smurf.views.adapters;

import android.smurf.R;
import android.smurf.SmurfApplication;
import android.smurf.models.SkiSlope;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author Wojtek Kolendo
 */

public class SkiSlopesAdapter extends RecyclerView.Adapter<SkiSlopesAdapter.ViewHolder> {

    private SortedList<SkiSlope> items;
    private OnItemClickedListener listener;

    public SkiSlopesAdapter(OnItemClickedListener listener) {
        this.listener = listener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ski_slope, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final SkiSlope item = items.get(position);

        holder.skiSlope = item;

        holder.nameTextView.setText(item.getName());
        holder.descriptionTextView.setText(item.getDescription());
        holder.temperatureTextView.setText(String.format(SmurfApplication.getContext()
                .getString(R.string.main_temperature_format), item.getTemperature()));

        holder.imageImageView.setImageResource(R.drawable.ski_slope);
//        if (!TextUtils.isEmpty(item.getImageUrl())) {
//            DisplayImageOptions options = DotHotApplication.getAuthDisplayImageOptionsBuilder()
//                    .cacheInMemory(true)
//                    .cacheOnDisk(true).build();
//            ImageLoader.getInstance().displayImage(item.getImageUrl(), holder.imageImageView, options);
//        }
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }


    public void setItemsList(SortedList<SkiSlope> itemsList) {
        items = itemsList;
        notifyDataSetChanged();
    }

    public interface OnItemClickedListener {
        void onItemClicked(SkiSlope item, int position);
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageImageView;
        private TextView nameTextView;
        private TextView descriptionTextView;
        private TextView temperatureTextView;

        private SkiSlope skiSlope;

        ViewHolder(View view) {
            super(view);
            imageImageView = (ImageView) view.findViewById(R.id.image);
            nameTextView = (TextView) view.findViewById(R.id.name);
            descriptionTextView = (TextView) view.findViewById(R.id.description);
            temperatureTextView = (TextView) view.findViewById(R.id.temperature);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClicked(skiSlope, getAdapterPosition());
                    }
                }
            });
        }

    }


}
