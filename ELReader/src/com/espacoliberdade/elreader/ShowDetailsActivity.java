package com.espacoliberdade.elreader;

import java.util.HashMap;
import java.util.Map;

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

	 // Obtendo os parâmetros
	 Bundle bundle = this.getIntent().getExtras();
	 
	 // Exibindo as informações
	 detailsTitle.setText(bundle.getString("keyTitle") + "\n");
	 detailsPubdate.setText("Publicado " + tratarData(bundle.getString("keyPubdate")) + "\n");
	 detailsDescription.setText(bundle.getString("keyDescription"));
	 detailsLink.setText("\nLeia mais em " + bundle.getString("keyLink"));
	    
	}
	
	String tratarData(String dataDoItem) {
		// Por padrão, o feed do WordPress retorna a data do post na forma
		// Tue, 20 Dec 2011 11:30:12 +0000 , o   que é evidentemente pouco
		// amigável. Nesta função, vamos tratar essa entrada e transformá-
		// la em algo como "Domingo, 20 de Dezembro de 2011 às 08:30". Não
		// podemos nos esquecer do fuso-horário que, em nosso caso, é -3h!
		
		// Primeiro, vamos declarar um mapa que vai relacionar os dias  da
		// semana em Inglês com os dias da semana  em Português:
		
		Map<String,String> dias = new HashMap<String,String>();
		dias.put("Sun", "domingo");
		dias.put("Mon", "segunda-feira");
		dias.put("Tue", "terça-feira");
		dias.put("Wed", "quarta-feira");
		dias.put("Thu", "quinta-feira");
		dias.put("Fri", "sexta-feira");
		dias.put("Sat", "sábado");
		
		// Agora vamos declarar outro mapa que vai relacionar os meses:
		
		Map <String,String> meses = new HashMap<String,String>();
		meses.put("Jan", "Janeiro");
		meses.put("Feb","Fevereiro");
		meses.put("Mar","Março");
		meses.put("Apr","Abril");
		meses.put("May","Maio");
		meses.put("Jun","Junho");
		meses.put("Jul", "Julho");
		meses.put("Aug", "Agosto");
		meses.put("Sep", "Setembro");
		meses.put("Oct", "Outubro");
		meses.put("Nov", "Novembro");
		meses.put("Dec", "Dezembro");
		
		// Agora, vamos transformar a data passada como parâmetro em um
		// array através do método split:
		
		String d[] = dataDoItem.split("\\s");
		
		// split recebe uma expressão regular como parâmetro, assim, \\s
		// representa o espaço em branco. No entanto, precisamos de  re-
		// mover a vírgula após o primeiro campo, correspondente ao  dia
		// da semana:
		
		String diaDaSemana = d[0].substring(0, 3);
		
		// Traduzindo o dia da semana:
		
		if(dias.containsKey(diaDaSemana)){
			diaDaSemana = dias.get(diaDaSemana);
		} else {
			diaDaSemana = "";
		}
		
		// Traduzindo o mês:
		
		String mes = d[2];
		
		if(meses.containsKey(mes)) {
			mes = meses.get(mes);
		} else {
			mes = "";
		}
		
		// Precisamos de diminuir três horas da hora do post
		
		String hs[] = d[4].split("\\:");
		String h = hs[0];
		String m = hs[1];
		String s = hs[2];
		int novaHora = Integer.parseInt(h) - 3;
		
		String hora = Integer.toString(novaHora) + ":" +
		  m + ":" + s;
		
		
		// E finalmente formamos a data!
		
		String dataFinal = diaDaSemana + ", " + d[1] + " de " + mes + 
				" de " + d[3] + " às " + hora;
		
		return dataFinal;
	}

}
