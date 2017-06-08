package android.smurf.views.fragments;

import android.os.Bundle;
import android.smurf.R;
import android.smurf.viewmodels.SkiSlopeDetailsViewModel;
import android.smurf.views.SkiSlopeDetailsView;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import eu.inloop.viewmodel.base.ViewModelBaseFragment;

/**
 * @author Wojtek Kolendo
 */

public class SkiSlopeDetailsFragment extends ViewModelBaseFragment<SkiSlopeDetailsView, SkiSlopeDetailsViewModel> implements SkiSlopeDetailsView {


    public static final String SKI_SLOPE_ID_EXTRA = "ski_slope_id_extra";

    private ImageView slopeImageView;
    private TextView nameTextView;
    private TextView countryTextView;
    private TextView temperatureTextView;
    private ImageView temperatureImageView;
    private TextView descriptionTextView;

    public static SkiSlopeDetailsFragment newInstance(long skiSlopeId) {
        SkiSlopeDetailsFragment fragment = new SkiSlopeDetailsFragment();
        Bundle args = new Bundle();
        args.putLong(SKI_SLOPE_ID_EXTRA, skiSlopeId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Long slopeId = getArguments().getLong(SKI_SLOPE_ID_EXTRA);
        getViewModel().setup(slopeId);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ski_slope_details, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        slopeImageView = (ImageView) view.findViewById(R.id.image);
        nameTextView = (TextView) view.findViewById(R.id.name);
        countryTextView = (TextView) view.findViewById(R.id.country);
        temperatureImageView = (ImageView) view.findViewById(R.id.temperature_image);
        temperatureTextView = (TextView) view.findViewById(R.id.temperature);
        descriptionTextView = (TextView) view.findViewById(R.id.description);

        setModelView(this);
    }

    @Override
    public void setSlopeName(String name) {
        nameTextView.setText(name);
    }

    @Override
    public void setSlopeDescription(String description) {
        descriptionTextView.setText(description);
    }

    @Override
    public void setSlopeCountry(String country) {
        countryTextView.setText(country);
    }

    @Override
    public void setSlopeTemperature(float temperature) {
        temperatureTextView.setText(String.format(getContext().getString(R.string.main_temperature_format), temperature));
    }

    @Override
    public void setSlopeTemperatureImage(@DrawableRes int drawable) {
        temperatureImageView.setImageResource(drawable);
    }

    @Override
    public void setSlopeImage(@DrawableRes int drawable) {
        slopeImageView.setImageResource(drawable);
    }

    @Override
    public void setSlopeImage(String imageUrl) {
        ImageLoader.getInstance().displayImage(imageUrl, slopeImageView);
    }
}
