package org.espiritolivre.revista.android;

import java.util.HashMap;
import java.util.Map;

import org.espiritolivre.revista.android.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ShowDetailsActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	 // TODO Auto-generated method stub
	 super.onCreate(savedInstanceState);
	 setContentView(R.layout.details);
	 
	 // Declarando os objetos da interface
	 TextView detailsTitle = (TextView)findViewById(R.id.detailstitle);
	 TextView detailsPubdate = (TextView)findViewById(R.id.detailspubdate);
	 TextView detailsDescription = (TextView)findViewById(R.id.detailsdescription);
	 TextView detailsLink = (TextView)findViewById(R.id.detailslink);

	 // Obtendo os par�metros
	 Bundle bundle = this.getIntent().getExtras();
	 
	 // Exibindo as informa��es
	 detailsTitle.setText(bundle.getString("keyTitle") + "\n");
	 detailsPubdate.setText("Publicado " + tratarData(bundle.getString("keyPubdate")) + "\n");
	 detailsDescription.setText(bundle.getString("keyDescription"));
	 detailsLink.setText("\nLeia mais em " + bundle.getString("keyLink"));
	    
	}
	
	String tratarData(String dataDoItem) {
		// Por padr�o, o feed do WordPress retorna a data do post na forma
		// Tue, 20 Dec 2011 11:30:12 +0000 , o   que � evidentemente pouco
		// amig�vel. Nesta fun��o, vamos tratar essa entrada e transform�-
		// la em algo como "Domingo, 20 de Dezembro de 2011 �s 08:30". N�o
		// podemos nos esquecer do fuso-hor�rio que, em nosso caso, � -3h!
		
		// Primeiro, vamos declarar um mapa que vai relacionar os dias  da
		// semana em Ingl�s com os dias da semana  em Portugu�s:
		
		Map<String,String> dias = new HashMap<String,String>();
		dias.put("Sun", "domingo");
		dias.put("Mon", "segunda-feira");
		dias.put("Tue", "ter�a-feira");
		dias.put("Wed", "quarta-feira");
		dias.put("Thu", "quinta-feira");
		dias.put("Fri", "sexta-feira");
		dias.put("Sat", "s�bado");
		
		// Agora vamos declarar outro mapa que vai relacionar os meses:
		
		Map <String,String> meses = new HashMap<String,String>();
		meses.put("Jan", "Janeiro");
		meses.put("Feb","Fevereiro");
		meses.put("Mar","Mar�o");
		meses.put("Apr","Abril");
		meses.put("May","Maio");
		meses.put("Jun","Junho");
		meses.put("Jul", "Julho");
		meses.put("Aug", "Agosto");
		meses.put("Sep", "Setembro");
		meses.put("Oct", "Outubro");
		meses.put("Nov", "Novembro");
		meses.put("Dec", "Dezembro");
		
		// Agora, vamos transformar a data passada como par�metro em um
		// array atrav�s do m�todo split:
		
		String d[] = dataDoItem.split("\\s");
		
		// split recebe uma express�o regular como par�metro, assim, \\s
		// representa o espa�o em branco. No entanto, precisamos de  re-
		// mover a v�rgula ap�s o primeiro campo, correspondente ao  dia
		// da semana:
		
		String diaDaSemana = d[0].substring(0, 3);
		
		// Traduzindo o dia da semana:
		
		if(dias.containsKey(diaDaSemana)){
			diaDaSemana = dias.get(diaDaSemana);
		} else {
			diaDaSemana = "";
		}
		
		// Traduzindo o m�s:
		
		String mes = d[2];
		
		if(meses.containsKey(mes)) {
			mes = meses.get(mes);
		} else {
			mes = "";
		}
		
		// Precisamos de diminuir tr�s horas da hora do post
		
		String hs[] = d[4].split("\\:");
		String h = hs[0];
		String m = hs[1];
		String s = hs[2];
		int novaHora = Integer.parseInt(h) - 3;
		
		String hora = Integer.toString(novaHora) + ":" +
		  m + ":" + s;
		
		
		// E finalmente formamos a data!
		
		String dataFinal = diaDaSemana + ", " + d[1] + " de " + mes + 
				" de " + d[3] + " �s " + hora;
		
		return dataFinal;
	}

}
