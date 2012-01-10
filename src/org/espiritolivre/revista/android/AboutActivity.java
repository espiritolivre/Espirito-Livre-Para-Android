package org.espiritolivre.revista.android;

import org.espiritolivre.revista.android.R;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.widget.TextView;

public class AboutActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        
        // Obtendo informacoes da versao do aplicativo a partir
        // do AndroidManifest.xml
        PackageInfo pinfo;
		try {
			pinfo = this.getPackageManager().getPackageInfo(getPackageName(), 0);
	        String VersionName = pinfo.versionName;
	        
	        // Agora vamos colocar isso no textview correspondente
	        TextView tvVersion = (TextView) findViewById(R.id.about_version);
	        tvVersion.setText("Versão " + VersionName + "\n");
		} catch (NameNotFoundException e) {
			// TODO Fazer um tratamento mais adequado.
			e.printStackTrace();
		}
    }
}
