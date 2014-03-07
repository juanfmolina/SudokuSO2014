package com.thelikes.thegot2run;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class ScoresActivity extends Activity {
	
	int scoreJuego;
	String nombreJugador;
	
	ListView listaScores;
	 List<Puntuacion> puntuaciones = new ArrayList<Puntuacion>();


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scores);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.scores, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		
		case R.id.play_game:
			Intent intent2 = new Intent(this, MainActivity.class);
			startActivity(intent2);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	
	protected void onStart() {
		super.onStart();
		obtenerComponentes();
		obtenerPuntaje();

		
		cargarLista();
	}
	
	private void obtenerComponentes() {

		listaScores = (ListView) findViewById(R.id.listaContenedora);
	}

	private void cargarLista() {
		Adapter_Score adapter_Estudiante = new Adapter_Score(getApplicationContext(),
				R.layout.item_score, puntuaciones);
		listaScores.setAdapter(adapter_Estudiante);

	}
	
public void obtenerPuntaje(){
	
		Intent intent = getIntent();
		
		if(intent.getExtras()!=null){
			
		Puntuacion puntuacion = (Puntuacion) intent.getSerializableExtra("nuevoPuntaje");
		puntuaciones.add(puntuacion);
		}else{
			
		}
	}


/*	 public class StartProcess extends AsyncTask<Main, Void, Main> {  
      @Override
        protected Main doInBackground(Main... params) { 
             return params[0]; 
       }
     protected void onPostExecute(Main params) { 
    // TODO Auto-generated method stub
        System.out.println("post"); 
         Intent loc = new Intent(params,ScoresActivity.class); 
          loc.putExtra("locationType",0); 
          loc.putExtra("startEndType",0); 
          startActivity(loc);
    }
    }*/
	
}
