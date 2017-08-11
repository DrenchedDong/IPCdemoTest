package dongting.beijing.com.ipcdemotest;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;

import dongting.beijing.com.ipcdemo.IMyAidlInterface;
import dongting.beijing.com.ipcdemo.User;

public class MainActivity extends Activity {

    private ServiceConnection serviceConnection;
    private IMyAidlInterface iMyAidlInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {

                iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
                try {
                    iMyAidlInterface.aidlMethod(1);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };


        findViewById(R.id.clicktest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(iMyAidlInterface !=null){

                    try {
                        iMyAidlInterface.user(new User("aa",12));
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    }else{

                    //使用隐式意图进行进程间访问
                    Intent intent = new Intent();
                    intent.setAction("dongting.beijing.com.ipcdemo.ACTION");
                    intent.setPackage("dongting.beijing.com.ipcdemo");

                    // startService(intent);
                    bindService(intent,serviceConnection, Service.BIND_AUTO_CREATE);
                }
            }
        });
    }
}
