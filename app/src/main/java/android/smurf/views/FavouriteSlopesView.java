package android.smurf.views;

import android.smurf.models.SkiSlope;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import eu.inloop.viewmodel.IView;

/**
 * @author Wojtek Kolendo
 */

public interface FavouriteSlopesView extends IView {

    RecyclerView.Adapter getAdapter();

    void openSkiSlopeDetailsActivity(SkiSlope skiSlope);

    void setSkiSlopesList(List<SkiSlope> skiSlopesList);

    void setSkiSlopesSortedList(SortedList<SkiSlope> skiSlopesList);

    void itemInserted(int position, int count);

    void itemRemoved(int position, int count);

    boolean checkPermission();
}
