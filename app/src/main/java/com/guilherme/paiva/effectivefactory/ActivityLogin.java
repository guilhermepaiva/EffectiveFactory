package com.guilherme.paiva.effectivefactory;

import android.app.Activity;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by guilhermepaiva on 18/02/16.
 */
public class ActivityLogin extends Activity {

    private ArrayList<String> messagesReceivedArray = new ArrayList<>();
    private TextView textViewReceivedMessages;
    private TextView textViewMessageNoneEmployeeLogged;
    private String employeeReadTag, ptoReadTag, lteReadTag;
    private ListView listView;
    private ArrayList<String> valuesAdapter = new ArrayList<>();
    private ArrayAdapter<String> arrayAdapter;
    private boolean employeeReaded = false;
    private boolean ptoReaded = false;
    private StringBuffer employeeToBeInserted = new StringBuffer();
    private StringBuffer ptoToBeInserted = new StringBuffer();
    private String toBeInserted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        textViewReceivedMessages = (TextView) findViewById(R.id.textViewReceivedMessages);
        textViewMessageNoneEmployeeLogged = (TextView) findViewById(R.id.messageNoneEmployeeLogged);
        listView = (ListView) findViewById(R.id.listViewLogin);

        updateTextViews();

    }

    private  void updateTextViews() {



        //Populate our list of messages we have received
        if (messagesReceivedArray.size() > 0) {
            String typeTag = messagesReceivedArray.get(0).substring(0,6);
            typeTag = typeTag.trim();

            if (typeTag.equalsIgnoreCase("enpto")){
                ptoReadTag = messagesReceivedArray.get(0).substring(6);
                textViewReceivedMessages.setText("Posto Operativo: " + messagesReceivedArray.get(0).substring(6) + " lido com sucesso!");

                ptoToBeInserted.append(ptoReadTag);
                toBeInserted = ptoToBeInserted.toString() + employeeToBeInserted.toString();
                ptoReaded = true;
            } else if (typeTag.equalsIgnoreCase("enlte")){
                textViewReceivedMessages.setText("Lote: " + messagesReceivedArray.get(0).substring(6) + " lido...\n\nVocê deve ler uma Tag de Posto Operativo...");
            } else {
                textViewReceivedMessages.setText("Operador: " + messagesReceivedArray.get(0).substring(3) + "\n\nPor favor, realize a leitura da Tag de indentificação do" +
                        " Posto de Trabalho!");
                employeeToBeInserted.append("Operador " + messagesReceivedArray.get(0).substring(3) + " logado no ");
                employeeReaded = true;
            }

            if (employeeReaded){

                if (ptoReaded){
                    valuesAdapter.add(toBeInserted.toString());
                    ptoReaded = false;
                    employeeReaded = false;
                    employeeToBeInserted.delete(0, employeeToBeInserted.length());
                    ptoToBeInserted.delete(0, ptoToBeInserted.length());
                    toBeInserted = "";
                }
            }


        } else {
            textViewReceivedMessages.setText("Aproxime o smartphone junto com a Tag do funcionário...");
        }

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, valuesAdapter);
        listView.setAdapter(arrayAdapter);
    }

    private void handleNfcIntent(Intent NfcIntent) {
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(NfcIntent.getAction())) {
            Parcelable[] receivedArray =
                    NfcIntent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);

            if(receivedArray != null) {
                messagesReceivedArray.clear();
                NdefMessage receivedMessage = (NdefMessage) receivedArray[0];
                NdefRecord[] attachedRecords = receivedMessage.getRecords();

                for (NdefRecord record:attachedRecords) {
                    String string = new String(record.getPayload());
                    //Make sure we don't pass along our AAR (Android Application Record)
                    if (string.equals(getPackageName())) {
                        continue; }
                    messagesReceivedArray.add(string);
                }
                Toast.makeText(this, "TAG: " + messagesReceivedArray +
                        " lida", Toast.LENGTH_LONG).show();
                updateTextViews();
            }
            else {
                Toast.makeText(this, "Received Blank Parcel", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onNewIntent(Intent intent) {
        handleNfcIntent(intent);
    }


    @Override
    public void onResume() {
        super.onResume();
        updateTextViews();
        handleNfcIntent(getIntent());
    }

}
