package br.com.caelum.alunos.util;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import br.com.caelum.alunos.fragment.MapaFragment;

import com.google.android.gms.maps.model.LatLng;

public class AtualizadorDeLocalizacao implements LocationListener {
	private MapaFragment mapa;
	private LocationManager manager;
	
	public AtualizadorDeLocalizacao(Context context, MapaFragment mapa){
		this.mapa = mapa;
		this.manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		
		int distanciaMinima = 20;
		int tempoMinimo = 2000;
		
		manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				tempoMinimo, distanciaMinima, this);
	}
	
	public void cancela(){
		manager.removeUpdates(this);
	}

	@Override
	public void onLocationChanged(Location l) {
		LatLng localAtual = new LatLng(l.getLatitude(), l.getLongitude());
		mapa.centralizaNo(localAtual);
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}
	
	
}
