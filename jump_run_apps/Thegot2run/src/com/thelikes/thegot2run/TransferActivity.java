package com.thelikes.thegot2run;


import com.parse.Parse;
import com.parse.ParseObject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class TransferActivity extends Activity {

	int scoreJuego;
	EditText campoNombre;
	String fecha;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Parse.initialize(this, "YqFw4gRsRwBlvL9UeN2hPkTZ0V1MHid1ptSVJbHY", "L85qVmSj5aQAQeyVKXI4CRHMUfPbhPcJe1x1v1pu");
		setContentView(R.layout.activity_transfer);
		Button aceptar = (Button) findViewById(R.id.botonAgregar);
		final Intent intent =  new Intent(this, ScoresActivity.class);
		aceptar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Puntuacion puntaje = new Puntuacion(campoNombre.getText().toString(),scoreJuego,fecha);				
				ParseObject ObjetoParse = new ParseObject("Score");
				ObjetoParse.put("Name", campoNombre.getText().toString());
				ObjetoParse.put("score", scoreJuego);
				ObjetoParse.saveInBackground();
				intent.putExtra("nuevoPuntaje", puntaje);
				startActivity(intent);
				
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.transfer, menu);
		return true;
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

		}
	
		
		public void obtenerScore(){
			
			Intent intent = getIntent();
			
			if(intent.getExtras()!=null){
				fecha = intent.getStringExtra("fechaJuego");
				scoreJuego = intent.getIntExtra("scoreJuego", 0);
				
			}else{
				
			}
		}	

}
