package android.smurf.viewmodels;

import android.smurf.R;
import android.smurf.models.SkiSlope;
import android.smurf.repositories.rest.GetSlopeDetailsRequest;
import android.smurf.views.NearbySlopesView;
import android.smurf.views.SkiSlopeDetailsView;
import android.support.annotation.NonNull;
import android.support.v7.util.SortedList;
import android.support.v7.widget.util.SortedListAdapterCallback;
import android.text.TextUtils;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.takisoft.fix.support.v7.preference.PreferenceFragmentCompatDividers;

import java.util.ArrayList;

import eu.inloop.viewmodel.AbstractViewModel;
import software.rsquared.androidlogger.Logger;
import software.rsquared.restapi.RestApi;
import software.rsquared.restapi.exceptions.RequestException;
import software.rsquared.restapi.listeners.RequestListener;

/**
 * @author Wojtek Kolendo
 */

public class SkiSlopeDetailsViewModel extends AbstractViewModel<SkiSlopeDetailsView> {

    private long slopeId;
    private SkiSlope skiSlope;

    @Override
    public void onBindView(@NonNull SkiSlopeDetailsView view) {
        super.onBindView(view);

//        mockSlope();
        downloadSkiSlope();
    }

    public void setup(long slopeId) {
        this.slopeId = slopeId;
    }

    private void initSlopeView() {
        if (getView() != null) {
            getView().setSlopeName(skiSlope.getName());
            getView().setSlopeCountry(skiSlope.getCountry());
            getView().setSlopeDescription(skiSlope.getDescription());
            getView().setSlopeTemperature(skiSlope.getTemperature());

            if (skiSlope.getTemperature() < 0f) {
                getView().setSlopeTemperatureImage(R.drawable.ic_snow_white_24dp);
            } else if (skiSlope.getTemperature() < 10f) {
                getView().setSlopeTemperatureImage(R.drawable.ic_cloud_white_24dp);
            } else {
                getView().setSlopeTemperatureImage(R.drawable.ic_wb_sunny_yellow_24dp);
            }

            getView().setSlopeImage(R.drawable.ski_slope);
            if (!TextUtils.isEmpty(skiSlope.getImage())) {
//            HttpUrl.Builder builder = UrlUtils.createUrlBuilder(false, "shop", "get-photo", item.getId() + "");
//            if (builder != null) {
//                String imageUrl = builder.build().toString();
//                getView().setSlopeName(imageUrl);
//            }
            }
        }
    }

    private void downloadSkiSlope() {
        RestApi.execute(new GetSlopeDetailsRequest(slopeId), new RequestListener<SkiSlope>() {
            @Override
            public void onSuccess(SkiSlope result) {
                if (result != null) {
                    skiSlope = result;
                    initSlopeView();
                }
            }

            @Override
            public void onFailed(RequestException e) {
                Logger.error(e);
            }
        });
    }

    // STOPSHIP: 07/06/2017
    private void mockSlope() {

        skiSlope = new SkiSlope();
        skiSlope.setName("Černá hora");
        skiSlope.setDescription("PEC is the largest ski resort in the Czech Republic offering 41 km of ski slopes with a single ski pass. The resort consists of five interconnected ski areas. Each area is specific  and offers slopes with different difficulty level. Apart from the longest ski slopes - up to three km long - you will find here also children-friendly zones with facilities for younger skiers or various ski attractions such as the Funline track with banked curves and a tunnel or a giant-slalom/downhill ski track with time measuring.\n" +
                "\n" +
                "As the only resort in the Czech Republic we offer a unique way of connecting the two largest areas Černá hora (Janské Lázně) and Pece pod Sněžkou on skis and via snowcats SkiTour. Only in SkiResort ČERNÁ HORA - PEC can you take a ride in a 8-seater cable car ČERNOHORSKÝ EXPRESS, enjoy the oldest sledge track in the country or try night skiing under floodlights in four out of five areas. ");
        skiSlope.setTemperature(-10);
        skiSlope.setCountry("Czech Republic");

        initSlopeView();
    }

}
