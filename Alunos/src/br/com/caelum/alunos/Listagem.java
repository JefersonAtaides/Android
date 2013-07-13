package br.com.caelum.alunos;

import java.util.List;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.telephony.SmsManager;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import br.com.caelum.alunos.dao.AlunoDAO;
import br.com.caelum.alunos.modelo.Aluno;

public class Listagem extends Activity {
	private ListView listaAlunos;
	private Aluno alunoSelecionado;
	
	private void carregaLista(){
        /* Lista de Alunos Dinâmica */
        AlunoDAO dao = new AlunoDAO(this);
        List<Aluno> alunos = dao.getLista();
        dao.close();
        
        /* Adaptador de Array para View */
        ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this,
        		android.R.layout.simple_list_item_1,
        		alunos
        );
        
        listaAlunos.setAdapter(adapter);
	}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem);
        
        /* Configurar View para utilizar Adapter acima */
        listaAlunos = (ListView) findViewById(R.id.lista_alunos);
        this.carregaLista();
        
        /* Registrando para Menu de Contexto */
        registerForContextMenu(listaAlunos);
        
        /* Toast */
        listaAlunos.setOnItemClickListener(new OnItemClickListener(){
        	@Override
        	public void onItemClick(AdapterView<?> adapter, View view, int posicao, long id){
        		Intent edicao = new Intent(Listagem.this, FormularioActivity.class);
        		edicao.putExtra("alunoSelecionado", (Aluno) listaAlunos.getItemAtPosition(posicao));
        		startActivity(edicao);
        		//Toast.makeText(Listagem.this, "Posição:" + posicao, Toast.LENGTH_SHORT).show();
        	}
        });
        listaAlunos.setOnItemLongClickListener(new OnItemLongClickListener() {
        		@Override
        		public boolean onItemLongClick(AdapterView<?> adapter, View view,
        				int posicao, long id) {

            		alunoSelecionado = (Aluno) adapter.getItemAtPosition(posicao);
        			return false;
        		}
		});

    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	this.carregaLista();
    }
    
    // Menu de Contexto
    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenuInfo menuInfo) {
    	// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contexto, menu);
    }
    
    @Override
    public boolean onContextItemSelected(MenuItem item){
    	switch (item.getItemId()){
    	case R.id.ligar:
    		Intent intentLigar = new Intent(Intent.ACTION_CALL);
    		intentLigar.setData(Uri.parse("tel:"+alunoSelecionado.getTelefone()));
    		startActivity(intentLigar);
    		
    		return false;
    	case R.id.sms:
    		// Opcao 1 - Intent
    		Intent intentSMS = new Intent(Intent.ACTION_VIEW);
    		intentSMS.setData(Uri.parse("sms:"+alunoSelecionado.getTelefone()));
    		intentSMS.putExtra("sms_body","Mensagem");
    		startActivity(intentSMS);
    		
    		// Opcao 2 - SMS Manager
    		//SmsManager smsManager = SmsManager.getDefault();
    		//PendingIntent sentIntent = PendingIntent.getActivity(this, 0, null, 0);
    		//
    		//if(PhoneNumberUtils.isWellFormedSmsAddress(alunoSelecionado.getTelefone())){
    		//	smsManager.sendTextMessage(alunoSelecionado.getTelefone(), null, "Sua nota é "+alunoSelecionado.getNota(), sentIntent, null);
    		//	Toast.makeText(Listagem.this, "SMS Enviado", Toast.LENGTH_SHORT).show();
    		//} else {
    		//	Toast.makeText(Listagem.this, "Falha ao enviar SMS", Toast.LENGTH_SHORT).show();
    		//}
    		
    		return false;
    	case R.id.site:
    		Intent intentSite = new Intent(Intent.ACTION_VIEW);
    		intentSite.setData(Uri.parse("http://"+alunoSelecionado.getSite()));
    		startActivity(intentSite);
    		
    		return false;
    	case R.id.excluir:
    		AlunoDAO dao = new AlunoDAO(Listagem.this);
    		dao.excluir(alunoSelecionado);
    		dao.close();
    		this.carregaLista();
    		Toast.makeText(Listagem.this, "Aluno excluído", Toast.LENGTH_SHORT).show();
    		
    		return false;
    	default:
    		return super.onOptionsItemSelected(item);
    	}
    }
    
    // Menu Principal
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
