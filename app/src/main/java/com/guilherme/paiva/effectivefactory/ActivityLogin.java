package com.guilherme.paiva.effectivefactory;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;

/**
 * Created by guilhermepaiva on 18/02/16.
 */
public class ActivityLogin extends Activity {
    private ListView listView;
    private Button buttonBackLogin;
    private Tag myTag;
    private NfcAdapter nfcAdapter;
    private PendingIntent pendingIntent;
    private Context context;
    private String contentTagEmployee;
    private String contentTagOperativePost;
    private boolean tagEmployeeReaded = false;
    private boolean tagOperativePostReaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        listView = (ListView) findViewById(R.id.listViewLogin);
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

        buttonBackLogin = (Button) findViewById(R.id.buttonBackLogin);
        buttonBackLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DashboardFactory.class);
                startActivity(intent);
            }
        });

        showListViewLogged();

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

        handleReadingTags(text);

        showListViewLogged();

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

    private void handleReadingTags(String textReceived){
        if (verifyTagOperativePost(textReceived)){
            Toast.makeText(ActivityLogin.this, "Posto Operativo lido: " + textReceived, Toast.LENGTH_LONG).show();
            contentTagOperativePost = textReceived;
            tagOperativePostReaded = true;
        } else {
            Toast.makeText(ActivityLogin.this, "Funcionario lido: " + textReceived, Toast.LENGTH_LONG).show();
            contentTagEmployee = textReceived;
            tagEmployeeReaded = true;
        }

        if (tagEmployeeReaded & tagOperativePostReaded){
            recordLogin(contentTagEmployee, contentTagOperativePost);
        }
    }

    private void recordLogin(String employeeName, String operativePost){
        LoginDatabaseController crud = new LoginDatabaseController(getBaseContext());
        String result = crud.insertLogin(employeeName, operativePost);
        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
        tagOperativePostReaded = false;
        tagEmployeeReaded = false;

    }

    private void showListViewLogged(){
        LoginDatabaseController crud = new LoginDatabaseController(getBaseContext());
        final Cursor cursor = crud.loadLoggedIn();
        if (cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(), "Nenhuma funcionario logado ainda...", Toast.LENGTH_LONG).show();
        }
        String[] fieldNames = new String[] {DatabaseHelper.EMPLOYEE_LOGIN, DatabaseHelper.OPERATIVE_POST_LOGIN};
        int[] idViews = new int[] {R.id.idEmployeeLogin, R.id.idOperativePostLogin};

        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(getBaseContext(),
                R.layout.layout_list_view_login, cursor, fieldNames, idViews, 0);
        listView = (ListView) findViewById(R.id.listViewLogin);
        listView.setAdapter(simpleCursorAdapter);

    }
}
