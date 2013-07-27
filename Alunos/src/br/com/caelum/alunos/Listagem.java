package br.com.caelum.alunos;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;
import br.com.caelum.alunos.adapter.ListaAlunosAdapter;
import br.com.caelum.alunos.async.UploadContactsToServer;
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
        
        /* Adaptador de Array para View x
        ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this,
        		android.R.layout.simple_list_item_1,
        		alunos
        ); */

        ListaAlunosAdapter adapter = new ListaAlunosAdapter(
        		this,
        		alunos);
        
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
    		String siteAluno = "";
    		if(alunoSelecionado.getSite().startsWith("http://")){
    			siteAluno = alunoSelecionado.getSite();
    		} else {
    			siteAluno = "http://" + alunoSelecionado.getSite();
    		}
    		
    		
    		// Opcao 1 - Action View
    		//Intent intentSite = new Intent(Intent.ACTION_VIEW);
    		//intentSite.setData(Uri.parse("http://"+alunoSelecionado.getSite()));
    		//startActivity(intentSite);
    		
    		// Opcao 2 - Web View
    		Intent verSite = new Intent(Listagem.this, VerSite.class);
    		verSite.putExtra("URL", siteAluno);
    		startActivity(verSite);
    		
    		return false;
    	case R.id.mapa:
    		Intent intentMapa = new Intent(Intent.ACTION_VIEW);
    		intentMapa.setData(Uri.parse("geo:0,0?z=14&q="+alunoSelecionado.getEndereco()));
    		startActivity(intentMapa);
    		
    		return false;
    	case R.id.email:
    		Intent intentEmail = new Intent(Intent.ACTION_SEND);
    		/* Email */
    		//intentEmail.setType("message/rfc822");
    		//intentEmail.putExtra(Intent.EXTRA_EMAIL, new String[]{ "caelum@caelum.com.br" });
    		
    		/* Share */
    		intentEmail.setType("text/plain");
    		
    		intentEmail.putExtra(Intent.EXTRA_SUBJECT, "Assunto do Email");
    		intentEmail.putExtra(Intent.EXTRA_TEXT, "Lorem Ipsum.");
    		startActivity(Intent.createChooser(intentEmail, "Escolha como preferir"));
    		
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
    	case R.id.menu_mapa:
    		return false;
    	case R.id.menu_enviar_alunos:
    		// New Task on background
			new UploadContactsToServer(this).execute();
    		return false;
    	case R.id.menu_receber_provas:
    		Intent provas = new Intent(this, ProvasActivity.class);
    		startActivity(provas);
    		return false;
    	case R.id.menu_preferencias:
    	default:
    		return super.onOptionsItemSelected(item);
    	}
    }
    
}
