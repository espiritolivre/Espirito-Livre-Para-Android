package org.espiritolivre.revista.android;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * Tela "Sobre"
 */
public class AboutActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

        String versionName = getVersionName();

        TextView tvVersion = (TextView) findViewById(R.id.about_version);
        String versionLabel = getString(R.string.version);
        tvVersion.setText(versionLabel + " " + versionName);
    }

    /**
     * Obtém nome da versão do aplicativo a partir do AndroidManifest.xml
     *
     * @return Nome da versão do aplicativo
     */
    private String getVersionName() {
        try {
            PackageInfo pinfo = this.getPackageManager().getPackageInfo(getPackageName(), 0);
            return pinfo.versionName;
        } catch (NameNotFoundException e) {
            Log.e("EspiritoLivre:AboutActivity", e.getMessage());
            return null;
        }
    }
}
