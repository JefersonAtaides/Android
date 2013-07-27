package br.com.caelum.alunos;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import br.com.caelum.alunos.fragment.DetalhesProvaFragment;
import br.com.caelum.alunos.fragment.ListaProvasFragment;
import br.com.caelum.alunos.modelo.Prova;

public class ProvasActivity extends FragmentActivity {
	public boolean isLandscape(){
		Integer orientation = getResources().getConfiguration().orientation;
		
		if(orientation == Configuration.ORIENTATION_LANDSCAPE){
			return true;
		}
		
		return false;
	}
	
	public boolean isTablet(){
		return true;
		//return getResources().getBoolean(R.bool.isTablet);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.provas);
		
		if(savedInstanceState == null){
			FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
			
			if(isTablet() && isLandscape()){
				transaction
					.replace(R.id.provas_lista, new ListaProvasFragment(),
							ListaProvasFragment.class.getCanonicalName())
					.replace(R.id.provas_detalhe, new DetalhesProvaFragment(),
							DetalhesProvaFragment.class.getCanonicalName());
			} else {
				transaction
					.replace(R.id.provas_lista, new ListaProvasFragment(),
							ListaProvasFragment.class.getCanonicalName());
			}
			
			transaction.commit();
		}
	}
	
	public void selecionaProva(Prova prova){
		Bundle argumentos = new Bundle();
		argumentos.putSerializable("prova", prova);
		
		DetalhesProvaFragment detalhesProva = new DetalhesProvaFragment();
		detalhesProva.setArguments(argumentos);
		
		FragmentTransaction transaction = 
				getSupportFragmentManager().beginTransaction();
		

		if(isTablet() && isLandscape()){
			transaction.replace(R.id.provas_detalhe, detalhesProva, 
					DetalhesProvaFragment.class.getCanonicalName());
		} else {
			transaction.replace(R.id.provas_lista, detalhesProva, 
					DetalhesProvaFragment.class.getCanonicalName());
			
			transaction.addToBackStack(null);
		}
		
		transaction.commit();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_provas, menu);
		return true;
	}

}
