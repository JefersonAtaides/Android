package br.com.caelum.alunos.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;
import br.com.caelum.alunos.R;
import br.com.caelum.alunos.dao.AlunoDAO;

public class SMSReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = intent.getExtras();
		
		Object messages[] = (Object[]) bundle.get("pdus");
		
		// Obter Mensagem - Forma 1
		//SmsMessage smsMessage[] = new SmsMessage[messages.length];		
		//for(int n=0; n<messages.length; n++){
		//	smsMessage[n] = SmsMessage.createFromPdu((byte[]) messages[n]);
		//}
		// String sms = smsMessage[0].getMessageBody()
		
		// Obter Mensagem - Forma 2
		byte[] message = (byte[]) messages[0];
		SmsMessage sms = SmsMessage.createFromPdu(message);
		
		// Obter Telefone que enviou o SMS
		String telefoneQueEnviou = sms.getDisplayOriginatingAddress();
		
		// Verificando se foi de um Aluno
		// E exibindo um Toast caso seja
		AlunoDAO dao = new AlunoDAO (context);
		
		if(dao.isAluno(telefoneQueEnviou)){
			Toast.makeText(context, 
					"SMS recebido do aluno : " + telefoneQueEnviou, 
					Toast.LENGTH_SHORT).show();
			
			Toast.makeText(context, 
					sms.getMessageBody(), 
					Toast.LENGTH_SHORT).show();
			
			MediaPlayer mp = MediaPlayer.create(context, R.raw.sms);
			mp.start();
		}
		dao.close();
	}

}
