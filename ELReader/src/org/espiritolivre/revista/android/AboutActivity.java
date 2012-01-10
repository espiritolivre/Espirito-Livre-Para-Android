package org.espiritolivre.revista.android;

import org.espiritolivre.revista.android.R;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.widget.TextView;

public class AboutActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        
        // Obtendo informa��es da vers�o do aplicativo a partir
        // do AndroidManifest.xml
        
        PackageInfo pinfo;
		try {
			pinfo = this.getPackageManager().getPackageInfo(getPackageName(), 0);
	        String VersionName = pinfo.versionName;
	        
	        // Agora vamos colocar isso no textview correspondente
	        TextView tvVersion = (TextView) findViewById(R.id.about_version);
	        tvVersion.setText("Vers�o " + VersionName + "\n");
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
