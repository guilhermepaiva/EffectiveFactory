package com.guilherme.paiva.effectivefactory;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
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

    Tag myTag;
    NfcAdapter nfcAdapter;
    PendingIntent pendingIntent;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        textViewReceivedMessages = (TextView) findViewById(R.id.textViewReceivedMessages);
        textViewMessageNoneEmployeeLogged = (TextView) findViewById(R.id.messageNoneEmployeeLogged);
        listView = (ListView) findViewById(R.id.listViewLogin);

        //updateTextViews();

        context = this;

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (nfcAdapter == null) {
            // Stop here, we definitely need NFC
            Toast.makeText(this, "Este smartphone nao suporta NFC.", Toast.LENGTH_LONG).show();
            finish();
        }
        readFromIntent(getIntent());

        pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        IntentFilter tagDetected = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
        tagDetected.addCategory(Intent.CATEGORY_DEFAULT);

    }


    /******************************************************************************
     **********************************Read From NFC Tag***************************
     ******************************************************************************/
    private void readFromIntent(Intent intent) {
        String action = intent.getAction();
        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)
                || NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)
                || NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {
            Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            NdefMessage[] msgs = null;
            if (rawMsgs != null) {
                msgs = new NdefMessage[rawMsgs.length];
                for (int i = 0; i < rawMsgs.length; i++) {
                    msgs[i] = (NdefMessage) rawMsgs[i];
                }
            }
            buildTagViews(msgs);
        }
    }
    private void buildTagViews(NdefMessage[] msgs) {
        if (msgs == null || msgs.length == 0) return;

        String text = "";
//        String tagId = new String(msgs[0].getRecords()[0].getType());
        byte[] payload = msgs[0].getRecords()[0].getPayload();
        String textEncoding = ((payload[0] & 128) == 0) ? "UTF-8" : "UTF-16"; // Get the Text Encoding
        int languageCodeLength = payload[0] & 0063; // Get the Language Code, e.g. "en"
        // String languageCode = new String(payload, 1, languageCodeLength, "US-ASCII");

        try {
            // Get the Text
            text = new String(payload, languageCodeLength + 1, payload.length - languageCodeLength - 1, textEncoding);
        } catch (UnsupportedEncodingException e) {
            Log.e("UnsupportedEncoding", e.toString());
        }


        if (verifyTagOperativePost(text)){
            Toast.makeText(ActivityLogin.this, "Leu tag de Posto Operativo", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(ActivityLogin.this, "Leu tag de Funcionario", Toast.LENGTH_LONG).show();
        }

        //Toast.makeText(ActivityLogin.this, "Conteudo da tag NFC: " + text, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        readFromIntent(intent);
        if(NfcAdapter.ACTION_TAG_DISCOVERED.equals(intent.getAction())){
            myTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        }
    }

    private boolean verifyTagOperativePost(String textReceived){
        if (textReceived.startsWith("Posto")){
            return true;
        }
        else{
            return false;
        }
    }

    /*private  void updateTextViews() {



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
    }*/

}
