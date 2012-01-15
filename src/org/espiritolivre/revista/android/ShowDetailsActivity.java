package org.espiritolivre.revista.android;

import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

public class ShowDetailsActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);

        // Declarando os objetos da interface
        TextView detailsTitle = (TextView) findViewById(R.id.detailstitle);
        TextView detailsPubdate = (TextView) findViewById(R.id.detailspubdate);
        TextView detailsDescription = (TextView) findViewById(R.id.detailsdescription);
        TextView detailsLink = (TextView) findViewById(R.id.detailslink);

        // Obtendo os parametros
        Bundle bundle = this.getIntent().getExtras();

        // Exibindo as informacoes
        detailsTitle.setText(bundle.getString("keyTitle") + "\n");
        detailsPubdate.setText("Publicado " + tratarData(bundle.getString("keyPubdate")) + "\n");
        detailsDescription.setText(bundle.getString("keyDescription"));
        detailsLink.setText("\nLeia mais em " + bundle.getString("keyLink"));

    }

    String tratarData(String dataDoItem) {
        // Por padrao, o feed do WordPress retorna a data do post na forma
        // Tue, 20 Dec 2011 11:30:12 +0000 , o   que e evidentemente pouco
        // amigavel. Nesta funcao, vamos tratar essa entrada e transforma-
        // la em algo como "Domingo, 20 de Dezembro de 2011 as 08:30". Nao
        // podemos nos esquecer do fuso-horario que, em nosso caso, e -3h!

        // Primeiro, vamos declarar um mapa que vai relacionar os dias  da
        // semana em Ingles com os dias da semana  em Portugues:

        Map<String, String> dias = new HashMap<String, String>();
        dias.put("Sun", "domingo");
        dias.put("Mon", "segunda-feira");
        dias.put("Tue", "terça-feira");
        dias.put("Wed", "quarta-feira");
        dias.put("Thu", "quinta-feira");
        dias.put("Fri", "sexta-feira");
        dias.put("Sat", "sábado");

        // Agora vamos declarar outro mapa que vai relacionar os meses:

        Map<String, String> meses = new HashMap<String, String>();
        meses.put("Jan", "Janeiro");
        meses.put("Feb", "Fevereiro");
        meses.put("Mar", "Março");
        meses.put("Apr", "Abril");
        meses.put("May", "Maio");
        meses.put("Jun", "Junho");
        meses.put("Jul", "Julho");
        meses.put("Aug", "Agosto");
        meses.put("Sep", "Setembro");
        meses.put("Oct", "Outubro");
        meses.put("Nov", "Novembro");
        meses.put("Dec", "Dezembro");

        // Agora, vamos transformar a data passada como parametro em um
        // array atraves do metodo split:

        String d[] = dataDoItem.split("\\s");

        // split recebe uma expressao regular como paremetro, assim, \\s
        // representa o espaco em branco. No entanto, precisamos de  re-
        // mover a virgula apos o primeiro campo, correspondente ao  dia
        // da semana:

        String diaDaSemana = d[0].substring(0, 3);

        // Traduzindo o dia da semana:

        if (dias.containsKey(diaDaSemana)) {
            diaDaSemana = dias.get(diaDaSemana);
        } else {
            diaDaSemana = "";
        }

        // Traduzindo o mes:

        String mes = d[2];

        if (meses.containsKey(mes)) {
            mes = meses.get(mes);
        } else {
            mes = "";
        }

        // Precisamos de diminuir tres horas da hora do post

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
