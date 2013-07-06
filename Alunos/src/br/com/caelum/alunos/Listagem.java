package br.com.caelum.alunos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Listagem extends Activity {
	private ListView listaAlunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem);
        
        /* Lista de Alunos Estática */
        String[] alunos = {"Anderson", "Felipe", "Guilherme"};
        
        /* Layout padrão do Android */
        int layout = android.R.layout.simple_list_item_1;
        
        /* Adaptador de Array para View */
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
        		layout,
        		alunos
        );
        
        /* Configurar View para utilizar Adapter acima */
        listaAlunos = (ListView) findViewById(R.id.lista_alunos);
        listaAlunos.setAdapter(adapter);
        
        /* Toast */
        listaAlunos.setOnItemClickListener(new OnItemClickListener(){
        	@Override
        	public void onItemClick(AdapterView<?> adapter, View view, int posicao, long id){
        		Toast.makeText(Listagem.this, "Posição:" + posicao, Toast.LENGTH_SHORT).show();
        	}
        });
        listaAlunos.setOnItemLongClickListener(new OnItemLongClickListener() {
        		@Override
        		public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
        				int arg2, long arg3) {

        			return false;
        		}
		});

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
    	switch (item.getItemId()){
    	case R.id.menu_novo:
    		Intent intent = new Intent(Listagem.this,
    				FormularioActivity.class);
    		
    		startActivity(intent);
    		return false;
    	default:
    		return super.onOptionsItemSelected(item);
    	}
    }
    
}
