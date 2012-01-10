package org.espiritolivre.revista.android;

import org.espiritolivre.revista.android.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener; 
import android.content.Intent;
import android.widget.Button;

public class AuthorsActivity extends Activity implements OnClickListener {
	private static final int REQUEST_CODE =10;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authors);
     
        // Vamos criar um botao para cada autor do site
        
        Button btnKleberson = (Button)findViewById(R.id.btnAuthKleberson);
        btnKleberson.setOnClickListener(this);
        
        Button btnAndre = (Button)findViewById(R.id.btnAuthAndre);
        btnAndre.setOnClickListener(this);
        
        Button btnCleiton = (Button)findViewById(R.id.btnAuthCleiton);
        btnCleiton.setOnClickListener(this);
        
        Button btnF3N1X = (Button)findViewById(R.id.btnAuthF3N1X);
        btnF3N1X.setOnClickListener(this);
        
        Button btnChico = (Button)findViewById(R.id.btnAuthChico);
        btnChico.setOnClickListener(this);
        
    }

// Nao e elegante mas funciona...
public void startTheActivity2(String theTitle, String theURL) {
    	Intent i = new Intent(AuthorsActivity.this, FeedReaderActivity.class);
    	i.putExtra("feedTitle", theTitle);
    	i.putExtra("feedURL", theURL);
    	startActivityForResult(i, REQUEST_CODE);
    }

public void onClick(View v) {
	switch (v.getId()) {
	case R.id.btnAuthKleberson:
		startTheActivity2("Kleberson", "http://espacoliberdade.blog.br/blog/author/kleberson/feed");
		//Intent i1 = new Intent(AuthorsActivity.this, FeedReaderActivity.class);
		//i1.putExtra("feedTitle", "Kleberson");
		//i1.putExtra("feedURL", "http://espacoliberdade.blog.br/blog/author/kleberson/feed");
		//startActivityForResult(i1, REQUEST_CODE);
		break; 
	case R.id.btnAuthAndre:
		startTheActivity2("Andrá Machado", "http://espacoliberdade.blog.br/blog/author/andremachado/feed");
		//Intent i2 = new Intent(AuthorsActivity.this, FeedReaderActivity.class);
		//i2.putExtra("feedTitle", "André Machado");
		//i2.putExtra("feedURL", "http://espacoliberdade.blog.br/blog/author/andremachado/feed");
		//startActivityForResult(i2, REQUEST_CODE);
		break;
	case R.id.btnAuthCleiton:
		startTheActivity2("Cleiton Lima", "http://espacoliberdade.blog.br/blog/author/cleitonlima/feed");
		//Intent i3 = new Intent(AuthorsActivity.this, FeedReaderActivity.class);
		//i3.putExtra("feedTitle", "Cleiton Lima");
		//i3.putExtra("feedURL", "http://espacoliberdade.blog.br/blog/author/cleitonlima/feed");
		//startActivityForResult(i3, REQUEST_CODE);
		break;
	case R.id.btnAuthF3N1X:
		startTheActivity2("F3N1X", "http://espacoliberdade.blog.br/blog/author/F3N1X/feed");
		//Intent i4 = new Intent(AuthorsActivity.this, FeedReaderActivity.class);
		//i4.putExtra("feedTitle", "F3N1X");
		//i4.putExtra("feedURL", "http://espacoliberdade.blog.br/blog/author/F3N1X/feed");
		//startActivityForResult(i4, REQUEST_CODE);
		break;
	case R.id.btnAuthChico:
		startTheActivity2("Chico", "http://espacoliberdade.blog.br/blog/author/chiconumb/feed");
		//Intent i5 = new Intent(AuthorsActivity.this, FeedReaderActivity.class);
		//i5.putExtra("feedTitle", "Chico");
		//i5.putExtra("feedURL", "http://espacoliberdade.blog.br/blog/author/chiconumb/feed");
		//startActivityForResult(i5, REQUEST_CODE);
		break;
		}
}


}