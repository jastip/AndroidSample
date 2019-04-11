package com.jastipapp.navigation;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jastipapp.navigation.com.jastipapp.navigation.model.DbContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class SaveDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_data);

        Button btnSaveDataSharedPref = (Button) findViewById(R.id.btnSaveSharePref);

        btnSaveDataSharedPref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = SaveDataActivity.this.getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("Test", 1000);
                editor.commit();
            }
        });

        Button btnLoadDataSharedPref = (Button) findViewById(R.id.btnLoadSharedPref);

        btnLoadDataSharedPref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = SaveDataActivity.this.getPreferences(Context.MODE_PRIVATE);
                int value = sharedPreferences.getInt("Test", 0);
                Toast toast = Toast.makeText(getApplicationContext(), "The test value is " + String.valueOf(value), Toast.LENGTH_LONG);
                toast.show();
            }
        });

        Button btnSaveDataIntStorage = (Button) findViewById(R.id.btnSaveIntStorage);

        btnSaveDataIntStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    FileOutputStream fos = openFileOutput("Test.txt", Context.MODE_PRIVATE);
                    fos.write("HelloWorld".getBytes());
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });

        Button btnLoadDataIntStorage = (Button) findViewById(R.id.btnLoadIntStorage);

        btnLoadDataIntStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileInputStream fi = openFileInput("Test.txt");
                    Scanner scanner = new Scanner(fi);
                    scanner.useDelimiter("\\Z");
                    String content = scanner.next();
                    scanner.close();

                    Toast toast = Toast.makeText(getApplicationContext(), "The test content is " + content, Toast.LENGTH_LONG);
                    toast.show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        });

        final DbContext db = new DbContext(this);


        Button btnSaveDatabase = (Button) findViewById(R.id.btnSaveDatabase);

        btnSaveDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.saveToDB();
            }
        });

        Button btnLoadDatabase = (Button) findViewById(R.id.btnLoadDatabase);

        btnLoadDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = db.readFromDB();
                Toast toast = Toast.makeText(getApplicationContext(), "The test value is " + String.valueOf(value), Toast.LENGTH_LONG);
                toast.show();
            }
        });

    }
}
