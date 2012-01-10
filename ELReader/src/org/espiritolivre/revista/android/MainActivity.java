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
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // Aqui n�s declaramos todos os bot�es da interface que v�o ter
        // uma a��o associada a eles
        
        // Bot�o �ltimos artigos
        Button thebtnLatestArticles = (Button)findViewById(R.id.btnLatestArticles);
        thebtnLatestArticles.setOnClickListener(this);
        
        // Bot�o �ltimos coment�rios
        Button thebtnLatestComments = (Button)findViewById(R.id.btnLatestComments);
        thebtnLatestComments.setOnClickListener(this);
        
        // Bot�o Autores
        Button thebtnAuthors = (Button)findViewById(R.id.btnAuthors);
        thebtnAuthors.setOnClickListener(this);
        
        // Bot�o Sobre
        Button thebtnAbout = (Button)findViewById(R.id.btnAbout);
        thebtnAbout.setOnClickListener(this);
        
    }

    // E aqui declaramos as a��es num loop switch:
    
    // Aten��o se voc� quiser modificar: os dois primeiros cases abaixo
    // funcionam de forma muito similar: eles chamam a tela da activity
    // FeedReader e passam a ela dois par�metros: feedTitle e feedURL.
    // O primeiro � o nome do feed, por exemplo "�ltimos artigos" e o
    // segundo � a sua URL. Se quiser adaptar para os seu site, apenas
    // modifique essas informa��es com os dados corretos! N�o mexa no resto!
    // Se quiser adicionar mais bot�es, declare-os no main.xml, siga o
    // exemplo acima de declara��o de Button e adicione um novo case abaixo.

public void startTheActivity(String theTitle, String theURL) {
	Intent i = new Intent(MainActivity.this, FeedReaderActivity.class);
	i.putExtra("feedTitle", theTitle);
	i.putExtra("feedURL", theURL);
	startActivityForResult(i, REQUEST_CODE);
}
    
public void onClick(View v) {
	// TODO Auto-generated method stub
	   switch(v.getId()) {
	   case R.id.btnLatestArticles: // Bot�o �ltimos artigos
		   startTheActivity("�ltimos artigos", "http://espacoliberdade.blog.br/blog/feed/");
		   break; 
	   case R.id.btnLatestComments: // Bot�o �ltimos coment�rios
		   startTheActivity("�ltimos coment�rios", "http://espacoliberdade.blog.br/blog/comments/feed/");
		   break;
	   case R.id.btnAuthors: // Bot�o Autores
		   Intent iAuth = new Intent(MainActivity.this, AuthorsActivity.class);
		   startActivity(iAuth);
		   break;
	   case R.id.btnAbout: // Bot�o Sobre
		   Intent iAbout = new Intent(MainActivity.this, AboutActivity.class);
		   startActivity(iAbout);
		   break;
	   }
	
}

// Agora vamos gerenciar o c�digo/mensagem de erro do FeedReader quando ele retornar

@Override
protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
	super.onActivityResult(requestCode, resultCode, intent);
	Bundle extras = intent.getExtras();
    TextView theStatus = (TextView)findViewById(R.id.statusMsg);
	theStatus.setText(extras.getString("returnKey"));
}

}