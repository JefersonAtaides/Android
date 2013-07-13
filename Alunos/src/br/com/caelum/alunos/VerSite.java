package br.com.caelum.alunos;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.webkit.WebView;
import br.com.caelum.alunos.modelo.Aluno;

public class VerSite extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ver_site);
		
		String URL = (String) getIntent().getSerializableExtra("URL");
		WebView view = (WebView) findViewById(R.id.vwweb);
		view.loadUrl(URL);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_ver_site, menu);
		return true;
	}

}
