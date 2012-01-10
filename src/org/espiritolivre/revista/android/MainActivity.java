package org.espiritolivre.revista.android;

import org.espiritolivre.revista.android.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener; 
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener  {
	private static final int REQUEST_CODE =10;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // Aqui nos declaramos todos os botoes da interface que vao ter
        // uma acao associada a eles
        
        // Botao ultimos artigos
        Button thebtnLatestArticles = (Button)findViewById(R.id.btnLatestArticles);
        thebtnLatestArticles.setOnClickListener(this);
        
        // Botao ultimos comentarios
        Button thebtnLatestComments = (Button)findViewById(R.id.btnLatestComments);
        thebtnLatestComments.setOnClickListener(this);
        
        // Botao Autores
        Button thebtnAuthors = (Button)findViewById(R.id.btnAuthors);
        thebtnAuthors.setOnClickListener(this);
        
        // Botao Sobre
        Button thebtnAbout = (Button)findViewById(R.id.btnAbout);
        thebtnAbout.setOnClickListener(this);
        
    }

    // E aqui declaramos as acoes num loop switch:
    
    // Atencao se voce quiser modificar: os dois primeiros cases abaixo
    // funcionam de forma muito similar: eles chamam a tela da activity
    // FeedReader e passam a ela dois parametros: feedTitle e feedURL.
    // O primeiro e o nome do feed, por exemplo "ultimos artigos" e o
    // segundo e a sua URL. Se quiser adaptar para os seu site, apenas
    // modifique essas informacoes com os dados corretos! Nao mexa no resto!
    // Se quiser adicionar mais botoes, declare-os no main.xml, siga o
    // exemplo acima de declaracao de Button e adicione um novo case abaixo.

public void startTheActivity(String theTitle, String theURL) {
	Intent i = new Intent(MainActivity.this, FeedReaderActivity.class);
	i.putExtra("feedTitle", theTitle);
	i.putExtra("feedURL", theURL);
	startActivityForResult(i, REQUEST_CODE);
}
    
public void onClick(View v) {
	// TODO Auto-generated method stub
	   switch(v.getId()) {
	   case R.id.btnLatestArticles: // Botao ultimos artigos
		   startTheActivity("Últimos artigos", "http://espacoliberdade.blog.br/blog/feed/");
		   break; 
	   case R.id.btnLatestComments: // Botao ultimos comentarios
		   startTheActivity("Últimos comentários", "http://espacoliberdade.blog.br/blog/comments/feed/");
		   break;
	   case R.id.btnAuthors: // Botao Autores
		   Intent iAuth = new Intent(MainActivity.this, AuthorsActivity.class);
		   startActivity(iAuth);
		   break;
	   case R.id.btnAbout: // Botao Sobre
		   Intent iAbout = new Intent(MainActivity.this, AboutActivity.class);
		   startActivity(iAbout);
		   break;
	   }
	
}

// Agora vamos gerenciar o coigo/mensagem de erro do FeedReader quando ele retornar

@Override
protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
	super.onActivityResult(requestCode, resultCode, intent);
	Bundle extras = intent.getExtras();
    TextView theStatus = (TextView)findViewById(R.id.statusMsg);
	theStatus.setText(extras.getString("returnKey"));
}

}