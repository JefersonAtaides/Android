package br.com.caelum.fragment;



import java.util.Arrays;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import br.com.caelum.alunos.ProvasActivity;
import br.com.caelum.alunos.R;
import br.com.caelum.alunos.modelo.Prova;

public class ListaProvasFragment extends Fragment {
	private ListView listViewProvas;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View layoutProvas = inflater.inflate(R.layout.prova_lista, container, false);
		
		listViewProvas = (ListView) layoutProvas.findViewById(R.id_provas_lista.list_view);
		
		Prova prova1 = new Prova("20/03/2012", "Matemática");
		prova1.setTopicos(Arrays.asList(
			"Algebra Linear", "Integral", "Diferencial"));
		
		Prova prova2 = new Prova("25/03/2012", "Língua Portuguesa");
		prova2.setTopicos(Arrays.asList(
			"Complemento Nominal", "Orações Subordinadas"));
		
		List<Prova> provas = Arrays.asList(prova1, prova2);
		
		this.listViewProvas = (ListView) layoutProvas.findViewById(R.id_provas_lista.list_view);
		
		this.listViewProvas.setAdapter(new ArrayAdapter<Prova>(
				getActivity(), android.R.layout.simple_list_item_1, provas));
		
		this.listViewProvas.setOnItemClickListener(new OnItemClickListener(){
				@Override
				public void onItemClick(AdapterView<?> adapter, View view, int posicao, long id){
					Prova selecionada = (Prova) adapter.getItemAtPosition(posicao);
						
					//Toast.makeText(getActivity(), "Prova selecionada:" + selecionada,
					//		Toast.LENGTH_LONG).show();
					
					ProvasActivity calendarioProvas = (ProvasActivity) getActivity();
					calendarioProvas.selecionaProva(selecionada);
						
				}	
		});
		
		return layoutProvas;
	}
	

	
}
