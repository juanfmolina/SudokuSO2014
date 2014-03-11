package com.thelikes.thegot2run;


import java.io.Serializable;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class TransferActivity extends Activity {

	int scoreJuego;
	EditText campoNombre;
	String fecha;
	Activity actividad;
	Intent intento =  new Intent(this, ScoresActivity.class);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		actividad=this;
		Parse.initialize(this, "YqFw4gRsRwBlvL9UeN2hPkTZ0V1MHid1ptSVJbHY", "L85qVmSj5aQAQeyVKXI4CRHMUfPbhPcJe1x1v1pu");
		setContentView(R.layout.activity_transfer);		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.transfer, menu);
		return true;
	}
	public static boolean inspectConection(Context ctx) {
		boolean bConectado = false;
		// se obtiene el servicio de conectividad el dispositivo
		ConnectivityManager connec = (ConnectivityManager) ctx
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		// se anade el estado de las conecciones del servicio de conectividad
		NetworkInfo[] redes = connec.getAllNetworkInfo();
		for (int i = 0; i < 2; i++) {

			if (redes[i].getState() == NetworkInfo.State.CONNECTED) {
				bConectado = true;
			}
		}
		return bConectado;
	}
	
	protected void onStart() {
		super.onStart();
		obtenerComponentes();
		obtenerScore();

	}
	
	public void obtenerComponentes(){
		
		campoNombre = (EditText) findViewById(R.id.campo_nombre);
		
		
	}
	

		public void onClick(View view){
			
			if (inspectConection(actividad)) {
				
				Puntuacion puntaje = new Puntuacion(campoNombre.getText().toString(),scoreJuego,fecha);				
				ParseObject ObjetoParse = new ParseObject("Score");
				ObjetoParse.put("Name", campoNombre.getText().toString());
				ObjetoParse.put("score", scoreJuego);
				ObjetoParse.put("fecha", fecha);
				ObjetoParse.saveInBackground();
				showAlertMessage(
						"Se ha ingresado Correctamente el puntaje",
						"puntaje Ingresado");
				Obtener o = new Obtener();
				o.execute();				
			}else {
				showAlertMessage("Se Necesita Conexion a Internet Para Realizar Esta Accion", "Sin Conexion a Internet");
			}
			
		}
	
		public void showAlertMessage(String mensaje, String titulo) {
			AlertDialog.Builder builder = new AlertDialog.Builder(actividad);
			builder.setTitle(titulo);
			builder.setIcon(R.drawable.ic_launcher);
			builder.setMessage(mensaje).setPositiveButton("Aceptar",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {

						}
					});

			AlertDialog dialog = builder.show();
			TextView messageText = (TextView) dialog
					.findViewById(android.R.id.message);
			messageText.setGravity(Gravity.CENTER);
			dialog.show();
		}
		
		public void obtenerScore(){
			
			Intent intent = getIntent();
			
			if(intent.getExtras()!=null){
				fecha = intent.getStringExtra("fechaJuego");
				scoreJuego = intent.getIntExtra("scoreJuego", 0);
				
			}else{
				
			}
		}	
		
		public void enviarResultados(List<Puntuacion> puntajes){
			Bundle b= new Bundle();
			b.putSerializable("puntajes",(Serializable) puntajes);
			intento.putExtra("bundle", b);
			startActivity(intento);
		}
		
		
		
		private class Obtener extends AsyncTask<String, Integer, Boolean> {

			List<ParseObject> ob;
			ProgressDialog dialog;
			boolean vacio;
			List<Puntuacion>puntajes;

			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				dialog = new ProgressDialog(actividad);
				// dialog.setTitle("Recuperando Clientes");
				dialog.setMessage("Por Favor Espere Mientras se Recuperan Las puntuaciones");
				dialog.show();
				/*
				 * / // Create a progressdialog mProgressDialog = new
				 * ProgressDialog(MainActivity.this); // Set progressdialog title
				 * mProgressDialog.setTitle("Parse.com Custom ListView Tutorial");
				 * // Set progressdialog message
				 * mProgressDialog.setMessage("Loading...");
				 * mProgressDialog.setIndeterminate(false); // Show progressdialog
				 * mProgressDialog.show(); /
				 */
			}

			@Override
			protected Boolean doInBackground(String... params) {
				
				boolean result = true;
				ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");
				query.orderByAscending("score");
				try {
					ob = query.find();
					if (ob.size() == 0) {
						vacio = true;
						return result;
					}
					
					int i=0;
					for (ParseObject score : ob) {
						// Locate images in flag column
						String nombre= score.getString("Name");
						int puntaje= score.getInt("score");
						String fecha=score.getString("fecha");
						Puntuacion punto = new Puntuacion(nombre, puntaje, fecha);
						puntajes.add(punto);
						i++;
						if (i>11) {
							break;
						}
					}
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					result = false;
				}

				return result;
			}

			@Override
			protected void onPostExecute(Boolean result) {

				if (result && vacio != true) {
					enviarResultados(puntajes);
				} else if (result && vacio == true) {
					showAlertMessage("Lo sentimos no hay Scores registrados",
							"No hay clientes");
				} else {
					showAlertMessage(
							"Hubo un Error Al tratar de Recuperar los Datos",
							"Error de Recuperacion de Datos");
				}
				dialog.dismiss();
			}
		}

}
