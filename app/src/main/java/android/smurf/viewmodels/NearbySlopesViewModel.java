package android.smurf.viewmodels;

import android.smurf.models.SkiSlope;
import android.smurf.repositories.rest.GetNearbySlopesRequest;
import android.smurf.views.NearbySlopesView;
import android.support.annotation.NonNull;
import android.support.v7.util.SortedList;
import android.support.v7.widget.util.SortedListAdapterCallback;

import java.util.ArrayList;
import java.util.List;

import eu.inloop.viewmodel.AbstractViewModel;
import software.rsquared.androidlogger.Logger;
import software.rsquared.restapi.RestApi;
import software.rsquared.restapi.exceptions.RequestException;
import software.rsquared.restapi.listeners.RequestListener;

/**
 * @author Wojtek Kolendo
 */

public class NearbySlopesViewModel extends AbstractViewModel<NearbySlopesView> {

    private ArrayList<SkiSlope> skiSlopes = new ArrayList<>();
    private SortedList<SkiSlope> nearbySkiSlopes;

    public void setupSlopes() {
        if (getView() != null) {
            initSortedList();
        }
    }

    @Override
    public void onBindView(@NonNull NearbySlopesView view) {
        super.onBindView(view);
        downloadSlopes();
    }

    public void onResume() {
        downloadSlopes();
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

    private void downloadSlopes() {
        RestApi.execute(new GetNearbySlopesRequest(), new RequestListener<List<SkiSlope>>() {
            @Override
            public void onSuccess(List<SkiSlope> result) {
                if (getView() != null && result != null) {
                    skiSlopes.addAll(result);

                    getView().setSkiSlopesList(skiSlopes);
//                    getView().notifyAdapter();
                    Logger.error(result.size());
                }
            }

            @Override
            public void onFailed(RequestException e) {
                Logger.error(e);
            }
        });
    }


    // STOPSHIP: 16/04/2017 MOCK !!!!!!!!!!!!!!!!!!!!
    private void mockSlopes() {
        ArrayList<SkiSlope> skiSlopes = new ArrayList<>();

        SkiSlope skiSlope = new SkiSlope();
        skiSlope.setName("Černá hora");
        skiSlope.setDescription("Největší lyžařský resort v Česku, kde můžete lyžovat na 41 km sjezdovek a lyžařských cest na 1 skipas.");
        skiSlope.setTemperature(-10);
        skiSlopes.add(skiSlope);

        skiSlope = new SkiSlope();
        skiSlope.setName("Zieleniec");
        skiSlope.setDescription("Zieleniec Ski Arena to jeden z najlepszych ośrodków narciarskich w Polsce, 24 wyciągi, 5 kanap, 22 km tras i śnieg przez 150 dni w roku czekają na narciarzy.");
        skiSlope.setTemperature(5);
        skiSlopes.add(skiSlope);

        skiSlope = new SkiSlope();
        skiSlope.setName("Špindlerův mlýn");
        skiSlope.setDescription("Největší lyžařský resort v Česku, kde můžete lyžovat na 41 km sjezdovek a lyžařských cest na 1 skipas.");
        skiSlope.setTemperature(666);
        skiSlopes.add(skiSlope);

        this.skiSlopes.addAll(skiSlopes);
    }


}
