package br.com.caelum.alunos.support;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class WebClient {
	private final String url;
	
	public WebClient(String url){
		this.url = url;
	}
	
	public String post(String json){
		try{
			DefaultHttpClient httpClient = new DefaultHttpClient();
			//URI address = new URI("http", null, this.url, this.port, null, null, null);
			String address = this.url;
			HttpPost post = new HttpPost(address);
					
			post.setEntity(new StringEntity(json));
			post.setHeader("Accept", "application/json");
			post.setHeader("Content-type", "application/json");
			
			HttpResponse response = httpClient.execute(post);
			String jsonDeResposta = EntityUtils.toString(response.getEntity());
			
			return jsonDeResposta;
		} catch (Exception e){
			//return "Erro ao sincronizar: " + e.toString();
			throw new RuntimeException(e);
		}
	}
	
}
