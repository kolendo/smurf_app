package android.smurf.viewmodels;

import android.smurf.models.SkiSlope;
import android.smurf.views.NearbySlopesView;
import android.support.annotation.NonNull;
import android.support.v7.util.SortedList;
import android.support.v7.widget.util.SortedListAdapterCallback;

import java.util.ArrayList;

import eu.inloop.viewmodel.AbstractViewModel;

/**
 * @author Wojtek Kolendo
 */

public class NearbySlopesViewModel extends AbstractViewModel<NearbySlopesView> {

    private ArrayList<SkiSlope> skiSlopes = new ArrayList<>();
    private SortedList<SkiSlope> nearbySkiSlopes;

    @Override
    public void onBindView(@NonNull NearbySlopesView view) {
        super.onBindView(view);
    }

    public void setupSlopes() {
        mockSlopes();
        initSortedList();
        getView().setSkiSlopesList(skiSlopes);
    }

    private void initSortedList() {
        if (getView() != null) {
            nearbySkiSlopes = new SortedList<>(SkiSlope.class, new SortedListAdapterCallback<SkiSlope>(getView().getAdapter()) {
                @Override
                public int compare(SkiSlope o1, SkiSlope o2) {
                    return o1.getName().compareTo(o2.getName());
                }

                @Override
                public boolean areContentsTheSame(SkiSlope oldItem, SkiSlope newItem) {
                    return oldItem != null && newItem != null;
                }

                @Override
                public boolean areItemsTheSame(SkiSlope item1, SkiSlope item2) {
                    return item1 != null && item2 != null && item1.getName().equals(item2.getName());
                }
            });
            getView().setSkiSlopesSortedList(nearbySkiSlopes);
        }
    }

    public void onSkiSlopeClicked(SkiSlope skiSlope) {
        if (getView() != null) {
            getView().openSkiSlopeDetailsActivity(skiSlope);
        }
    }

    // STOPSHIP: 16/04/2017 MOCK !!!!!!!!!!!!!!!!!!!!
    private void mockSlopes() {
        ArrayList<SkiSlope> skiSlopes = new ArrayList<>();

        SkiSlope skiSlope = new SkiSlope();
        skiSlope.setName("Cerna Hora");
        skiSlope.setDescription("fknfjkfnkjfbkjfbkjfbkjfbkjf");
        skiSlope.setTemperature(10);

        skiSlopes.add(skiSlope);

        this.skiSlopes.addAll(skiSlopes);
    }

}
