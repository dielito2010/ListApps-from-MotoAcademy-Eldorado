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

    private RecyclerView appRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appRecyclerView = findViewById(R.id.appRecyclerView);

        // Configurar o layout manager do RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        appRecyclerView.setLayoutManager(layoutManager);

        // Obter o PackageManager
        PackageManager packageManager = getPackageManager();

        // Obter a lista de aplicativos instalados
        List<ApplicationInfo> installedApps = packageManager.getInstalledApplications(0);

        // Lista de nomes de aplicativos
        List<String> appNames = new ArrayList<>();

        for (ApplicationInfo appInfo : installedApps) {
            // Filtrar aplicativos do sistema, se desejar
            if ((appInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                String appName = appInfo.loadLabel(packageManager).toString();
                appNames.add(appName);
            }
        }

        // Configurar o adaptador do RecyclerView
        AppAdapter appAdapter = new AppAdapter(appNames);
        appRecyclerView.setAdapter(appAdapter);

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedPosition = appAdapter.getSelectedPosition();
                if (selectedPosition != RecyclerView.NO_POSITION) {
                    String selectedAppName = appNames.get(selectedPosition);
                    Toast.makeText(getApplicationContext(), selectedAppName, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
