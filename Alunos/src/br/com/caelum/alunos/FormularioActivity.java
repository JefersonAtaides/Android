package br.com.caelum.alunos;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
//import br.com.caelum.alunos.dao.AlunoDAO;
import br.com.caelum.alunos.modelo.Aluno;

public class FormularioActivity extends Activity {
	private FormularioHelper helper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.formulario);
		
		
        /* Listener do Botao */
        helper = new FormularioHelper(this);
        Button botao = (Button) findViewById(R.id.botao);
        botao.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		Aluno aluno = helper.pegaAlunoDoFormulario();
        		
        		/* Antigo Toast */
        		Toast.makeText(
        				FormularioActivity.this
        				, "Objeto aluno criado: "+aluno.getNome()
        				, Toast.LENGTH_SHORT
        		).show();
        		
        		/* Persistindo no SQLite 
        		AlunoDAO dao = new AlunoDAO(FormularioActivity.this);
        		dao.insere(aluno);
        		dao.close();*/
        		
        		//finish();
        		
        	}
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.formulario, menu);
		return true;
	}

}
