package com.example.reactnativepoc;

import android.util.Log;
import android.widget.Toast;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class BridgingModule extends ReactContextBaseJavaModule {
    private static Boolean isOn = false;
    private static ReactApplicationContext reactApplicationContext;

    public BridgingModule(ReactApplicationContext reactContext) {
        super(reactContext);
        reactApplicationContext= reactContext;
    }

    @ReactMethod
    public void getStatus(
            com.facebook.react.bridge.Callback successCallback) {
        successCallback.invoke(null, isOn);

    }

    @ReactMethod
    public void turnOn() {
        isOn = true;
        System.out.println("T logo displayed");
        Toast.makeText(getCurrentActivity(), "T logo displayed", Toast.LENGTH_SHORT ).show();
    }
    @ReactMethod
    public void turnOff() {
        isOn = false;
        System.out.println("T logo removed");
        Toast.makeText(getCurrentActivity(), "T logo removed", Toast.LENGTH_SHORT ).show();
    }

    @ReactMethod
    public void sendAPIRequest(){
      JSONPlaceholderAPI mJsonPlaceholderAPI = getRetrofitClient();

        Call<List<Comment>> call = mJsonPlaceholderAPI.getComments();

        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if(!response.isSuccessful()){
                    Log.i("code", String.valueOf(response.code()));
                }

                List<Comment> comments = response.body();

                for(Comment comment : comments){
                    String result= "";
                    result+= "PostId: " + comment.getPostId() + "\n";
                    result+= "UserId: " + comment.getUserId() + "\n";
                    result+= "UserName: " + comment.getUserName() + "\n";
                    result+= "EmailAddress: " + comment.getEmailAddress() + "\n";
                    result+= "EmailBody: " + comment.getEmailBody() + "\n\n";
                    MyReactActivity.displayData(result);
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                Log.i("API", "FAILED");
            }
        });
    }

    @Override
    public String getName() {
        return "BridgingModule";
    }

    public static void getComments(){


        WritableMap params = Arguments.createMap(); // add here the data you want to send
        params.putString("eventParam", "call from java");


                reactApplicationContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit("onClick", params);

    }


    private JSONPlaceholderAPI getRetrofitClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(JSONPlaceholderAPI.class);

    }

}


