package br.com.caelum.helloworld;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class HelloWorld extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("CICLO DE VIDA","onCreate");
        setContentView(R.layout.principal);
        
    	Button cadastrar = (Button) findViewById(R.id.botaoConfirmar);
    	cadastrar.setOnClickListener(new View.OnClickListener(){
    		public void onClick(View view){
    			// pega os dados da Activity
    			// (agora que temos acesso a todos os componentes, fica mais simples)
    			// grava os dados na base SQL
    		}
    	});
    	
    	AlertDialog.Builder alerta = new AlertDialog.Builder(HelloWorld.this);
    	alerta.setMessage("Olá amigo");
    	alerta.setNeutralButton("OK", null);
    	
    	AlertDialog dialogo = alerta.create();
    	dialogo.setTitle("Meu Diálogo");
    	dialogo.show();
    }
    
    @Override
    protected void onStart() {
    	super.onStart();
    	Log.i("CICLO DE VIDA","onStart");
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	Log.i("CICLO DE VIDA","onResume");
    }
    
    @Override
    protected void onRestart() {
    	super.onRestart();
    	Log.i("CICLO DE VIDA","onRestart");
    }
    
    @Override
    protected void onPause() {
    	super.onPause();
    	Log.i("CICLO DE VIDA","onPause");
    }
    
    @Override
    protected void onStop() {
    	super.onStop();
    	Log.i("CICLO DE VIDA","onStop");
    }
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	Log.i("CICLO DE VIDA","onDestroy");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }
    
}
