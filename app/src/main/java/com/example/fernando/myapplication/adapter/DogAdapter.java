package com.example.fernando.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fernando.myapplication.R;
import com.example.fernando.myapplication.domain.dto.DogDTO;

import java.util.List;

/**
 * Created by Fernando on 12/11/2017.
 */
public class DogAdapter extends ArrayAdapter<DogDTO> implements View.OnClickListener{

    private List<DogDTO> extrato;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        ImageView imgUser;
        TextView DogName;
        TextView DataNasc;

    }

    public DogAdapter(List<DogDTO> data, Context context) {
        super(context, android.R.layout.simple_list_item_1, data);
        this.extrato = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        DogDTO dataModel = (DogDTO)object;

        //chamar nova activity passando o historico selecionado (dataModel)

    }

    private int lastPosition = -1;



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        DogDTO dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.adapter_dog, parent, false);
            viewHolder.imgUser = (ImageView) convertView.findViewById(R.id.imUser);
            viewHolder.DogName = (TextView) convertView.findViewById(R.id.etvdogName);
            viewHolder.DataNasc = (TextView) convertView.findViewById(R.id.etvdtnasc);
            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        lastPosition = position;

        viewHolder.DogName.setText(dataModel.getNome());
        viewHolder.DataNasc.setText(dataModel.getDt_nasc());

        return convertView;
    }









}