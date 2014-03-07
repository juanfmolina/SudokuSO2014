package com.thelikes.thegot2run;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;


public class Adapter_Score extends ArrayAdapter<Puntuacion>{

	Context context;
	int layoutResourceId;
	List<Puntuacion> puntuaciones;
	TextView textViewNombre;
	TextView textViewScore;
	LinearLayout layout;

	public Adapter_Score(Context context, int layoutResourceId, List<Puntuacion> objects) {
		super(context, layoutResourceId, objects);
		this.context = context;
		this.puntuaciones = objects;
		this.layoutResourceId = layoutResourceId;

	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(layoutResourceId, parent, false);

		textViewNombre = (TextView) rowView.findViewById(R.id.elemento_nombre);
		textViewScore = (TextView) rowView.findViewById(R.id.elemento_score);

		textViewNombre.setText(puntuaciones.get(position).getNombre());
		textViewScore.setText(puntuaciones.get(position).getScore());

		return rowView;
	}
	
}
