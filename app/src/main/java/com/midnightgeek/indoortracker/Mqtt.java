package com.midnightgeek.indoortracker;

import android.content.Context;
import android.util.Log;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class Mqtt {
    private String broker = "tcp://192.168.1.48:1883";
    private MqttAsyncClient client;
    private static Mqtt ourInstance;
    MemoryPersistence persistence = new MemoryPersistence();
    private final String TAG="Mqtt";
    public static Mqtt getInstance(){
        if (ourInstance==null){
            ourInstance=new Mqtt();
        }
        return ourInstance;
    }

    public void init(String clientId, Context context){
        try{
            client = new MqttAsyncClient(broker, "MQTT_"+clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            client.connect(connOpts, context, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken iMqttToken) {
                    Log.e(TAG,"onSuccess ");
                    try {
                        PrefHandler pref=new PrefHandler(context);
                        pref.init();
                        String topic="/user/server/"+pref.getString(pref.getTAG_USER_ID(),"");
                        Log.e("TOPIC : ",topic);
                        client.subscribe(topic,2);
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(IMqttToken iMqttToken, Throwable throwable) {
                    Log.e(TAG,"onFailure "+throwable.toString());

                }
            });

            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable throwable) {
                    Log.e(TAG,"connectionLost "+throwable.toString());

                }

                @Override
                public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
                    Log.e(TAG,"messageArrived  "+mqttMessage.getPayload().toString());
                    NotificationHandler.getInstance().raiseNotification("ALARM",context);

                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

                }
            });
            //client.subscribe("test/123",2);

        }catch (Exception ex){
            Log.e(TAG,"ERROR "+ex.toString());
        }


    }


}
