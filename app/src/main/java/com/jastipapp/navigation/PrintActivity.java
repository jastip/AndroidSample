package com.jastipapp.navigation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import honeywell.connection.ConnectionBase;
import honeywell.connection.Connection_Bluetooth;
import honeywell.connection.Connection_TCP;
import honeywell.printer.DocumentDPL;
import honeywell.printer.DocumentDPL.*;
import honeywell.printer.DocumentEZ;
import honeywell.printer.DocumentLP;
import honeywell.printer.DocumentExPCL_LP;
import honeywell.printer.DocumentExPCL_PP;
import honeywell.printer.DocumentExPCL_PP.*;
import honeywell.printer.ParametersDPL;
import honeywell.printer.ParametersDPL.*;
import honeywell.printer.ParametersEZ;
import honeywell.printer.ParametersExPCL_LP;
import honeywell.printer.ParametersExPCL_LP.*;
import honeywell.printer.ParametersExPCL_PP;
import honeywell.printer.ParametersExPCL_PP.*;
import honeywell.printer.ParametersLP;
import honeywell.printer.UPSMessage;
import honeywell.printer.configuration.dpl.*;
import honeywell.printer.configuration.ez.*;
import honeywell.printer.configuration.expcl.*;


public class PrintActivity extends AppCompatActivity {

    private Button btnPrintText;
    private Button btnPrintBarcode;
    private ConnectionBase connection;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (connection != null && connection.getIsOpen()){
            connection.close();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print);

        String deviceAddress = getIntent().getStringExtra("DeviceAddress");

        try {
            connection = Connection_Bluetooth.createClient(deviceAddress);
            connection.open();
        } catch (Exception e) {
            e.printStackTrace();
        }

        btnPrintText = (Button) findViewById(R.id.btnPrintText);
        btnPrintText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    //====Apex Printers(Apex 2, Apex 3, etc..)========//
                    DocumentExPCL_LP docExPCL_LP = new DocumentExPCL_LP(3); //Line Print mode. “3” is the font index.
                    ParametersExPCL_LP paramExPCL_LP = new ParametersExPCL_LP();

                    DocumentExPCL_PP docExPCL_PP = new DocumentExPCL_PP(PaperWidth.PaperWidth_384); //Page print mode
                    ParametersExPCL_PP paramExPCL_PP = new ParametersExPCL_PP();

                    paramExPCL_LP.setFontIndex(1);
                    docExPCL_LP.writeText("  JastipApp Font 1", paramExPCL_LP);
                    paramExPCL_LP.setFontIndex(2);
                    docExPCL_LP.writeText("    JastipApp Font 2", paramExPCL_LP);
                    paramExPCL_LP.setFontIndex(3);
                    docExPCL_LP.writeText("      JastipApp Font 3", paramExPCL_LP);
                    paramExPCL_LP.setFontIndex(4);
                    docExPCL_LP.writeText("        JastipApp Font 4", paramExPCL_LP);
                    paramExPCL_LP.setFontIndex(5);
                    docExPCL_LP.writeText("          JastipApp Font 5", paramExPCL_LP);
                    connection.write(docExPCL_LP.getDocumentData());

                    //paramExPCL_PP.setFontIndex(1);
                    //docExPCL_PP.writeText("Hello World JastipApp", 1, 1, paramExPCL_PP);
                    //connection.write(docExPCL_PP.getDocumentData());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }
}
