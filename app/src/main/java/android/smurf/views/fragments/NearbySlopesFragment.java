package android.smurf.views.fragments;

import android.os.Bundle;
import android.smurf.R;
import android.smurf.models.SkiSlope;
import android.smurf.viewmodels.NearbySlopesViewModel;
import android.smurf.views.NearbySlopesView;
import android.smurf.views.adapters.SkiSlopesAdapter;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.util.SortedList;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import eu.inloop.viewmodel.base.ViewModelBaseFragment;

/**
 * @author Wojtek Kolendo
 */

public class NearbySlopesFragment extends ViewModelBaseFragment<NearbySlopesView, NearbySlopesViewModel> implements NearbySlopesView, SkiSlopesAdapter.OnItemClickedListener {

    private SkiSlopesAdapter skiSlopesAdapter;
    private RecyclerView recyclerView;
    private View emptyView;
    private SwipeRefreshLayout swipeRefreshView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        skiSlopesAdapter = new SkiSlopesAdapter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nearby_slopes, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setModelView(this);

        recyclerView = (RecyclerView) view.findViewById(R.id.list_recyclerView);
        emptyView = view.findViewById(R.id.no_items_view);
        recyclerView.setAdapter(skiSlopesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        getViewModel().setupSlopes();
    }

    public static NearbySlopesFragment newInstance() {
        return new NearbySlopesFragment();
    }

    @Override
    public void onItemClicked(SkiSlope item, int position) {
        // TODO: 16/04/2017
    }

    /**
     * Checks whether slopes list is empty and sets an image
     */
    private void validateEmptyViewImage() {
        if (skiSlopesAdapter.getItemCount() <= 0) {
            emptyView.setVisibility(View.VISIBLE);
        } else {
            emptyView.setVisibility(View.GONE);
        }
    }

    @Override
    public RecyclerView.Adapter getAdapter() {
        return skiSlopesAdapter;
    }

    @Override
    public void openSkiSlopeDetailsActivity(SkiSlope skiSlope) {
        // TODO: 16/04/2017
    }

    @Override
    public void setSkiSlopesList(List<SkiSlope> skiSlopesList) {
        skiSlopesAdapter.setItems(skiSlopesList);
        validateEmptyViewImage();
    }

    @Override
    public void setSkiSlopesSortedList(SortedList<SkiSlope> skiSlopesList) {
        skiSlopesAdapter.setItemsSortedList(skiSlopesList);
        validateEmptyViewImage();
    }

    @Override
    public void itemInserted(int position, int count) {

    }

    @Override
    public void itemRemoved(int position, int count) {

    }

    @Override
    public void setRecyclerViewRefreshing(boolean enable) {
        swipeRefreshView.setRefreshing(enable);
    }

    @Override
    public boolean checkPermission() {
        return false;
    }
}
