package br.com.caelum.alunos.async;

import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import br.com.caelum.alunos.converter.AlunoConverter;
import br.com.caelum.alunos.dao.AlunoDAO;
import br.com.caelum.alunos.modelo.Aluno;
import br.com.caelum.alunos.support.WebClient;

public class UploadContactsToServer extends AsyncTask<Object,Object,String> {
	private final Context contexto;
	private ProgressDialog progress;
	
	public UploadContactsToServer(Context contexto){
		this.contexto = contexto;
	}
	
	@Override
	public void onPreExecute(){
		// 1) Colocar mensagem de Progresso
		this.progress = new ProgressDialog(contexto);
		this.progress.setMessage("Carregando");
		this.progress.show();
	}
	
	@Override
	protected String doInBackground(Object... arg0) {
		// 1) Obter Alunos
		AlunoDAO dao = new AlunoDAO(contexto);
		List<Aluno> lista = dao.getLista();

		// 2) Converter para JSON
		AlunoConverter ac = new AlunoConverter();
		String jsonDeResposta = ac.toJSON(lista);

		// 3) Enviar para o Web Server
		WebClient wc = new WebClient("http://tinyurl.com/jywe3m");
		String resposta = wc.post(jsonDeResposta);

		// 4) Retornar resposta
		return resposta;
	}
	
	@Override
	public void onPostExecute(String result){
		// 1) Remover Progress
		this.progress.dismiss();
		
		// 2) Mostrar Toast
		Toast.makeText(contexto,
				result, Toast.LENGTH_LONG)
				.show();
	}
	
}
