package br.dev.danielribeiro.listapps;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<ApplicationInfo> appList;
    private RecyclerView recyclerView;
    private AppAdapter appAdapter;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicialize a lista de ApplicationInfo
        appList = new ArrayList<>();

        // Obtenha a lista de aplicativos instalados
        PackageManager packageManager = getPackageManager();
        List<ApplicationInfo> installedApps = packageManager.getInstalledApplications(0);
        for (ApplicationInfo appInfo : installedApps) {
            if ((appInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                appList.add(appInfo);
            }
            // appList.add(appInfo);
        }

        // Configure o RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        appAdapter = new AppAdapter(this, appList);
        recyclerView.setAdapter(appAdapter);

        // Configure o botão de salvar
        saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedPosition = appAdapter.getSelectedPosition();
                if (selectedPosition != RecyclerView.NO_POSITION) {
                    ApplicationInfo selectedApp = appList.get(selectedPosition);


                    String appName = selectedApp.loadLabel(packageManager).toString();
                    String packageName = selectedApp.packageName;
                    try {
                        //SystemPropertiesHelper.write(packageName, appName);
                        SystemPropertiesHelper.write("listAppsProp", packageName);
                    } catch (Exception e) {

                    }
                    Toast.makeText(MainActivity.this, "Selecionado: " + appName, Toast.LENGTH_SHORT).show();
                    finishAffinity();
                } else {
                    Toast.makeText(MainActivity.this, "Nenhum app selecionado", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
