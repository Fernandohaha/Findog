package com.example.fernando.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fernando.myapplication.R;
import com.example.fernando.myapplication.domain.dto.VacinaDTO;

import java.util.List;

/**
 * Created by Fernando on 13/11/2017.
 */

public class VacinaAdapter extends ArrayAdapter<VacinaDTO> implements View.OnClickListener {

    private List<VacinaDTO> extrato;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView Data;
        TextView Reforco;
        TextView Nome;

    }

    public VacinaAdapter(List<VacinaDTO> data, Context context) {
        super(context, android.R.layout.simple_list_item_1, data);
        this.extrato = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        VacinaDTO dataModel = (VacinaDTO)object;

        //chamar nova activity passando o historico selecionado (dataModel)

    }


    private int lastPosition = -1;



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        VacinaDTO dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        VacinaAdapter.ViewHolder viewHolder; // view lookup cache stored in tag
        final View result;

        if (convertView == null) {

            viewHolder = new VacinaAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.adapter_vacina, parent, false);
            viewHolder.Data = (TextView) convertView.findViewById(R.id.etDataVacina);
            viewHolder.Reforco = (TextView) convertView.findViewById(R.id.etReforcoVacina);
            viewHolder.Nome = (TextView) convertView.findViewById(R.id.etNomeVacina);
            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        lastPosition = position;

        viewHolder.Data.setText(dataModel.getData());
        viewHolder.Reforco.setText(dataModel.getReforco());
        viewHolder.Nome.setText(dataModel.getNome_Vacina());

        return convertView;
    }
}
