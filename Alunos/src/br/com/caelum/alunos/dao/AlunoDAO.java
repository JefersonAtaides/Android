package br.com.caelum.alunos.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import br.com.caelum.alunos.modelo.Aluno;

public class AlunoDAO extends SQLiteOpenHelper {
	private static final int VERSAO = 1;
	private static final String TABELA = "Alunos";
	private static final String DATABASE = "CadastroCaelum";
	
	public AlunoDAO(Context context){
		super(context, DATABASE, null, VERSAO);
	}
	
	public void insere(Aluno aluno){
		ContentValues values = new ContentValues();
		values.put("nome", aluno.getNome());
		values.put("telefone",aluno.getTelefone());
		values.put("endereco", aluno.getEndereco());
		values.put("site",aluno.getSite());
		values.put("nota", aluno.getNota());
		values.put("foto", aluno.getFoto());
		
		getWritableDatabase().insert(TABELA, null, values);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String dd1 = "CREATE TABLE " + TABELA +
				" (id INTEGER PRIMARY KEY," +
				"nome TEXT UNIQUE NOT NULL," +
				"telefone TEXT," +
				"endereco TEXT," +
				"site TEXT," +
				"nota REAL," +
				"foto TEXT);";
		
		db.execSQL(dd1);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String sql = "DROP TABLE IF EXISTS " + TABELA;
		db.execSQL(sql);
		onCreate(db);
	}
}
