package br.com.caelum.alunos;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_listagem, menu);
        return true;
    }
    
}
