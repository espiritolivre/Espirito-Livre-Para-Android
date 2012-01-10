package com.espacoliberdade.elreader;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener; 
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener  {
	private static final int REQUEST_CODE =10;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // Aqui nós declaramos todos os botões da interface que vão ter
        // uma ação associada a eles
        
        // Botão Últimos artigos
        Button thebtnLatestArticles = (Button)findViewById(R.id.btnLatestArticles);
        thebtnLatestArticles.setOnClickListener(this);
        
        // Botão Últimos comentários
        Button thebtnLatestComments = (Button)findViewById(R.id.btnLatestComments);
        thebtnLatestComments.setOnClickListener(this);
        
        // Botão Autores
        Button thebtnAuthors = (Button)findViewById(R.id.btnAuthors);
        thebtnAuthors.setOnClickListener(this);
        
        // Botão Sobre
        Button thebtnAbout = (Button)findViewById(R.id.btnAbout);
        thebtnAbout.setOnClickListener(this);
        
    }

    // E aqui declaramos as ações num loop switch:
    
    // Atenção se você quiser modificar: os dois primeiros cases abaixo
    // funcionam de forma muito similar: eles chamam a tela da activity
    // FeedReader e passam a ela dois parâmetros: feedTitle e feedURL.
    // O primeiro é o nome do feed, por exemplo "Últimos artigos" e o
    // segundo é a sua URL. Se quiser adaptar para os seu site, apenas
    // modifique essas informações com os dados corretos! Não mexa no resto!
    // Se quiser adicionar mais botões, declare-os no main.xml, siga o
    // exemplo acima de declaração de Button e adicione um novo case abaixo.

public void startTheActivity(String theTitle, String theURL) {
	Intent i = new Intent(MainActivity.this, FeedReaderActivity.class);
	i.putExtra("feedTitle", theTitle);
	i.putExtra("feedURL", theURL);
	startActivityForResult(i, REQUEST_CODE);
}
    
public void onClick(View v) {
	// TODO Auto-generated method stub
	   switch(v.getId()) {
	   case R.id.btnLatestArticles: // Botão últimos artigos
		   startTheActivity("Últimos artigos", "http://espacoliberdade.blog.br/blog/feed/");
		   break; 
	   case R.id.btnLatestComments: // Botão Últimos comentários
		   startTheActivity("Últimos comentários", "http://espacoliberdade.blog.br/blog/comments/feed/");
		   break;
	   case R.id.btnAuthors: // Botão Autores
		   Intent iAuth = new Intent(MainActivity.this, AuthorsActivity.class);
		   startActivity(iAuth);
		   break;
	   case R.id.btnAbout: // Botão Sobre
		   Intent iAbout = new Intent(MainActivity.this, AboutActivity.class);
		   startActivity(iAbout);
		   break;
	   }
	
}

// Agora vamos gerenciar o código/mensagem de erro do FeedReader quando ele retornar

@Override
protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
	super.onActivityResult(requestCode, resultCode, intent);
	Bundle extras = intent.getExtras();
    TextView theStatus = (TextView)findViewById(R.id.statusMsg);
	theStatus.setText(extras.getString("returnKey"));
}

}