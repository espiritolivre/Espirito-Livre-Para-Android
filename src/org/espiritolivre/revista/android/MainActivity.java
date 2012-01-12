package org.espiritolivre.revista.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
	
    private static final int REQUEST_CODE = 10;

    /**
     * Retorna as categorias usadas no menu. O mais correto seria ter uma constante, mas o ciclo de vida da Activity não
     * possibilita recuperar strings do R no momento da inicialização da classe.
     *
     * @return Categorias usadas no menu.
     */
    private String[] getCategorias() {
        return new String[] {
                super.getString(R.string.edicoes),
                super.getString(R.string.noticias),
                super.getString(R.string.artigos)
        };
    }

    private static final String[] FEEDS_CATEGORIAS = new String[] {
            "http://www.revista.espiritolivre.org/category/lancamentos/feed",
            "http://www.revista.espiritolivre.org/category/noticias/feed",
            "http://www.revista.espiritolivre.org/category/artigos/feed"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getCategorias());
        
        ListView categorias = (ListView) findViewById(R.id.categorias);
        categorias.setAdapter(adapter);
        categorias.setClickable(true);
        categorias.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			public void onItemClick(AdapterView<?> adapter, View view, int posicao, long id) {
				exibirFeed(getCategorias()[posicao], FEEDS_CATEGORIAS[posicao]);
			}
		});
    }

    public void exibirFeed(String theTitle, String theURL) {
        Intent i = new Intent(MainActivity.this, FeedReaderActivity.class);
        i.putExtra("feedTitle", theTitle);
        i.putExtra("feedURL", theURL);
        startActivityForResult(i, REQUEST_CODE);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	
    	MenuItem sobre = menu.add(R.string.about);
    	sobre.setIcon(android.R.drawable.ic_menu_info_details);
    	sobre.setIntent(new Intent(MainActivity.this, AboutActivity.class));
    	
    	return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	startActivity(item.getIntent());
    	return super.onOptionsItemSelected(item);
    }
}