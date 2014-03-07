package com.thelikes.thegot2run;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class TransferActivity extends Activity {

	int scoreJuego;
	EditText campoNombre;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transfer);
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
			Puntuacion puntaje = new Puntuacion(campoNombre.getText().toString(),String.valueOf(scoreJuego));
			Intent intent =  new Intent(this, ScoresActivity.class);
			intent.putExtra("nuevoPuntaje", puntaje);
			startActivity(intent);

		}
	
		
		public void obtenerScore(){
			
			Intent intent = getIntent();
			
			if(intent.getExtras()!=null){
				
				scoreJuego = intent.getIntExtra("scoreJuego", 0);
				
			}else{
				
			}
		}	

}
