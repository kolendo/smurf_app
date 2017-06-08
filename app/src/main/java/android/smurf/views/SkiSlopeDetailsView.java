package android.smurf.views;

import android.graphics.drawable.Drawable;
import android.smurf.models.SkiSlope;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringDef;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import eu.inloop.viewmodel.IView;

/**
 * @author Wojtek Kolendo
 */

public interface SkiSlopeDetailsView extends IView {

    void setSlopeName(String name);

    void setSlopeDescription(String description);

    void setSlopeCountry(String country);

    void setSlopeTemperature(float temperature);

    void setSlopeTemperatureImage(@DrawableRes int drawable);

    void setSlopeImage(@DrawableRes int drawable);

    void setSlopeImage(String imageUrl);


}
