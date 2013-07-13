package br.com.caelum.alunos;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;
import br.com.caelum.alunos.dao.AlunoDAO;
import br.com.caelum.alunos.modelo.Aluno;

public class FormularioActivity extends Activity {
	private FormularioHelper helper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.formulario);
		
        helper = new FormularioHelper(this);
        Button botao = (Button) findViewById(R.id.botao);
        
		/* Aluno que vem da Intent */
		Aluno aluno = (Aluno) getIntent().getSerializableExtra("alunoSelecionado");
		if(aluno == null){
			aluno = new Aluno();
		} else {
			helper.colocaNoFormulario(aluno);
			botao.setText("Alterar");
		}
        
		/* Listener do Botao */
        botao.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		Aluno aluno = helper.pegaAlunoDoFormulario();
        		
        		/* Persistindo no SQLite */
    			AlunoDAO dao = new AlunoDAO(FormularioActivity.this);
        		if(aluno.getId() == null){
            		Toast.makeText(
            				FormularioActivity.this
            				, "Objeto aluno criado: "+aluno.getNome()
            				, Toast.LENGTH_SHORT
            		).show();
            		
        			dao.insere(aluno);
        		} else {
            		Toast.makeText(
            				FormularioActivity.this
            				, "Objeto aluno alterado: "+aluno.getNome()
            				, Toast.LENGTH_SHORT
            		).show();
            		
        			dao.alterar(aluno);
        		}
        		dao.close();
        		finish();
        		
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
