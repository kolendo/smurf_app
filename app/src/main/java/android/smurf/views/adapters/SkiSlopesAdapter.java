package android.smurf.views.adapters;

import android.smurf.R;
import android.smurf.SmurfApplication;
import android.smurf.models.SkiSlope;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import okhttp3.HttpUrl;

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
        checkListInitialized();

        final SkiSlope item = items.get(position);

        holder.skiSlope = item;

        holder.nameTextView.setText(item.getName());
        holder.descriptionTextView.setText(item.getDescription());
        holder.temperatureTextView.setText(String.format(SmurfApplication.getContext()
                .getString(R.string.main_temperature_format), item.getTemperature()));

        holder.imageImageView.setImageResource(R.drawable.ski_slope);
        if (!TextUtils.isEmpty(item.getImage())) {
//            HttpUrl.Builder builder = UrlUtils.createUrlBuilder(false, "shop", "get-photo", item.getId() + "");
//            if (builder != null) {
//                String imageUrl = builder.build().toString();
//                ImageLoader.getInstance().displayImage(imageUrl, holder.imageImageView);
//            }
        }
    }

    @Override
    public int getItemCount() {
        checkListInitialized();
        return items == null ? 0 : items.size();
    }

    public void setItems(List<SkiSlope> items) {
        checkListInitialized();
        this.items.addAll(items);
    }

    public void setItemsSortedList(SortedList<SkiSlope> itemsList) {
        items = itemsList;
        notifyDataSetChanged();
    }

    public interface OnItemClickedListener {
        void onItemClicked(SkiSlope item, int position);
    }

    private void checkListInitialized() {
        if(items == null) {
            throw new IllegalStateException("Sorted list not initialized! Use method 'initSortedList(SortedList)' before showing " +
                    "recyclerView.");
        }
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
