package br.com.caelum.alunos;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import br.com.caelum.alunos.dao.AlunoDAO;
import br.com.caelum.alunos.modelo.Aluno;

public class FormularioActivity extends Activity {
	private FormularioHelper helper;
	private String localArquivoFoto;
	private static final int TIRA_FOTO = 123;

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
        
        // Listener da Imagem
        ImageView foto = helper.getBotaoImagem();
        foto.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		localArquivoFoto = Environment.getExternalStorageDirectory() + 
        				"/" + System.currentTimeMillis() + ".jpg";
        		
        		File arquivo = new File(localArquivoFoto);
        		Uri localFoto = Uri.fromFile(arquivo);
        		
        		Intent irParaCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        		irParaCamera.putExtra(MediaStore.EXTRA_OUTPUT, localFoto);
        		startActivityForResult(irParaCamera, TIRA_FOTO);
        	}
        });
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == TIRA_FOTO){
			if(resultCode == Activity.RESULT_OK){
				helper.carregaImagem(this.localArquivoFoto);
			} else {
				this.localArquivoFoto = null;
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.formulario, menu);
		return true;
	}

}
