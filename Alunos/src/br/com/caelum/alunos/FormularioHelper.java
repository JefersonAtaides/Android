package br.com.caelum.alunos;

import android.widget.EditText;
import android.widget.SeekBar;
import br.com.caelum.alunos.modelo.Aluno;

public class FormularioHelper {
	private EditText nome;
	private EditText telefone;
	private EditText endereco;
	private EditText site;
	private EditText foto;
	private SeekBar nota;
	
	public FormularioHelper(FormularioActivity activity){
		nome = (EditText) activity.findViewById(R.id.nome);
		telefone = (EditText) activity.findViewById(R.id.telefone);
		endereco = (EditText) activity.findViewById(R.id.endereco);
		site = (EditText) activity.findViewById(R.id.site);
		foto = (EditText) activity.findViewById(R.id.foto);
		nota = (SeekBar) activity.findViewById(R.id.nota);
	}
	
	public Aluno pegaAlunoDoFormulario(){
		Aluno aluno = new Aluno();
		aluno.setNome(nome.getEditableText().toString());
		aluno.setTelefone(telefone.getEditableText().toString());
		aluno.setEndereco(endereco.getEditableText().toString());
		aluno.setSite(site.getEditableText().toString());
		aluno.setFoto(foto.getEditableText().toString());
		aluno.setNota(Double.valueOf(nota.getProgress()));
		
		return aluno;
	}
}
