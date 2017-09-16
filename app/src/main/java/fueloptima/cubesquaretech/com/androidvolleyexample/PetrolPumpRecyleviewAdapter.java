package fueloptima.cubesquaretech.com.androidvolleyexample;


import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anand Gaur on 21-08-2017.
 */

public class PetrolPumpRecyleviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int PETROL_PUMP_VIEW_TYPE = 0;

    private final Context context;

    private final List<PumpDetail> mRecyclerViewItems;


    public PetrolPumpRecyleviewAdapter( Context context, List<PumpDetail> recyclerViewItems) {
        this.context = context;
        this.mRecyclerViewItems = recyclerViewItems;
    }

//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        return null;
//    }
//
//    @Override
//    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return 0;
//    }

    public class PetrolPumpDetailViewHolder extends RecyclerView.ViewHolder {
        private TextView petrol_pump_name;
        private TextView petrol_pump_address;
        private TextView petrol_pump_distance;
        private TextView petrol_pump_prtrolprice;
        private TextView petrol_pump_dieselprice;
        PetrolPumpDetailViewHolder(View view) {
            super(view);
            petrol_pump_name = (TextView) view.findViewById(R.id.petrol_pump_name);
            petrol_pump_address = (TextView) view.findViewById(R.id.petrol_pump_address);
            petrol_pump_distance = (TextView) view.findViewById(R.id.petrol_pump_distance);
            petrol_pump_prtrolprice = (TextView) view.findViewById(R.id.petrol_pump_prtrolprice);
            petrol_pump_dieselprice = (TextView) view.findViewById(R.id.petrol_pump_dieselprice);
        }
    }

            @Override
            public int getItemCount() {
           return mRecyclerViewItems.size();
            }

            @Override
            public RecyclerView.ViewHolder onCreateViewHolder (ViewGroup viewGroup,int viewType){

                        View pumpDetailLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                                R.layout.petrolpump_recyleview_layout, viewGroup, false);
                        return new PetrolPumpDetailViewHolder(pumpDetailLayoutView);
            }

            @Override
            public void onBindViewHolder (RecyclerView.ViewHolder holder,int position)
            {
                        PetrolPumpDetailViewHolder pumpDetailViewHolder = (PetrolPumpDetailViewHolder) holder;
                        PumpDetail pumpDetail =  mRecyclerViewItems.get(position);
                        pumpDetailViewHolder.petrol_pump_name.setText(pumpDetail.getName());
                        pumpDetailViewHolder.petrol_pump_address.setText(pumpDetail.getAddress());
                        pumpDetailViewHolder.petrol_pump_distance.setText(pumpDetail.getDistance());
                        pumpDetailViewHolder.petrol_pump_prtrolprice.setText(pumpDetail.getPetrolprice());
                        pumpDetailViewHolder.petrol_pump_dieselprice.setText(pumpDetail.getDieselprice());
            }
}