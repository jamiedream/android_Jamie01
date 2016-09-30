package chunying.mybluetooth;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_ENABLE_BT = 1;
    private BluetoothAdapter mBA;
    private boolean isSupport=true;
    private boolean isBTInitEnable=false;
    private boolean isBTEnable=false;
    private ListView listdevices;
    private SimpleAdapter adapter;
    private String[] from = {"name","addr","type"};
    private int[] to = {R.id.name,R.id.addr,R.id.type};
    private LinkedList<HashMap<String,Object>> data;
    private MyBTR receiver;
    private UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupbt();


        if(Build.VERSION.SDK_INT>=23){
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        10);
            }
        }

        listdevices = (ListView) findViewById(R.id.listdevices);
        InitBTListview();
        receiver = new MyBTR();
    }

    private void InitBTListview(){
        data = new LinkedList<>();
        adapter = new SimpleAdapter(this,data,R.layout.layout_item,from,to);
        listdevices.setAdapter(adapter);
        listdevices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BluetoothDevice remoteDevice = (BluetoothDevice) data.get(position).get("device");
            }
        });


    }

    public void setupbt() {
        mBA = BluetoothAdapter.getDefaultAdapter();
        if (mBA == null) {
            //device doesn't support bt
            isSupport = false;
        } else {
            if (!mBA.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            } else {
                isBTInitEnable = true;
                isBTEnable = true;
            }

        }
    }
    public void edi(View v){
        Intent discoverableIntent = new
                Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
        startActivity(discoverableIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_ENABLE_BT){
            if(requestCode == RESULT_OK){
                isBTEnable = true;
            }
        }
    }

    @Override
    public void finish() {
        if(isSupport && !isBTInitEnable){
            mBA.disable();
        }
        super.finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Register the BroadcastReceiver
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(receiver, filter); // Don't forget to unregister during onDestroy
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(isSupport&&mBA.isDiscovering()){
            mBA.cancelDiscovery();
        }
        unregisterReceiver(receiver);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void scan(View v){
        data.clear();
        //query paired
        Set<BluetoothDevice> paired = mBA.getBondedDevices();
        for(BluetoothDevice device:paired){
            HashMap<String,Object> item = new HashMap<>();
            item.put(from[0],device.getName());
            item.put(from[1],device.getAddress());
            item.put(from[2],"paired");
            item.put("device",device);
            data.add(item);
            adapter.notifyDataSetChanged();
        }
        // Discovering devices
        mBA.startDiscovery();

    }
    private class MyBTR extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            HashMap<String,Object> item = new HashMap<>();
            if (!isDevice(device.getAddress())) {
                item.put(from[0], device.getName());
                item.put(from[1], device.getAddress());
                item.put(from[2], "scan");
                item.put("device", device);
                data.add(item);
                adapter.notifyDataSetChanged();
            }
        }
    }
    private boolean isDevice(String addr){
        boolean isExist=false;
        for (HashMap<String,Object>  device:data){
            if(((String)device.get(from[1])).equals(addr)) {
                isExist = true;
                break;
            }
        }
        return isExist;
    }
    public void asserver(View v){
        AcceptThread serverthread = new AcceptThread();
        serverthread.start();
    }
    private class AcceptThread extends Thread {
        private final BluetoothServerSocket mmServerSocket;

        public AcceptThread() {
            // Use a temporary object that is later assigned to mmServerSocket,
            // because mmServerSocket is final
            BluetoothServerSocket tmp = null;
            try {
                // MY_UUID is the app's UUID string, also used by the client code
                tmp = mBA.listenUsingRfcommWithServiceRecord("jamie", MY_UUID);
            } catch (IOException e) { }
            mmServerSocket = tmp;
        }

        public void run() {
            BluetoothSocket socket = null;
            // Keep listening until exception occurs or a socket is returned
            while (true) {
                try {
                    socket = mmServerSocket.accept();
                    Log.d("jamie","accept");
                } catch (IOException e) {
                    break;
                }

            }
        }

        /** Will cancel the listening socket, and cause the thread to finish */
        public void cancel() {
            try {
                mmServerSocket.close();
            } catch (IOException e) { }
        }
    }

}
