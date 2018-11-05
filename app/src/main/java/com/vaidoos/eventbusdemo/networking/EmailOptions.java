package com.vaidoos.eventbusdemo.networking;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.os.StatFs;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.vaidoos.eventbusdemo.R;
import com.vaidoos.eventbusdemo.interfaces.DataReceiveandSet;
import com.vaidoos.eventbusdemo.networking.interfaces.LocationResponse;
import com.vaidoos.eventbusdemo.networking.interfaces.VolleyCallback;
import com.vaidoos.eventbusdemo.networking.utils.MySingleton;
import com.vaidoos.eventbusdemo.networking.utils.RptTypeField;

import org.json.JSONObject;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;
import static android.text.TextUtils.substring;

public class EmailOptions {

    private static final String TAG = "EmailOption";

    public static Double DEVICE_LAT = 23.7785768;
    public static Double DEVICE_LANG = 90.3929036;


    public static String newtworkState = null;

    public static String ITEM_ID = "";
    public static String ITEM_NAME = "";

    public static int dd;
    public static int mm;
    public static int yyyy;

    public String latestdate;

    public static String netState = "";

    public static boolean networkAvailable = false;

    public static String COMPANY_ID_DEVICE = "01";

    public static String DEVICE_IMEI1 = "";
    public static String DEVICE_IMEI2 = "";
    public static String DEVICE_SERIAL_NUMBER = "";

    Context context;
    VolleyCallback volleyCallback;
    LocationResponse locationResponse;
    public DataReceiveandSet dataReceiveandSet;

    double lat, lng;

    private FusedLocationProviderClient client;

    public EmailOptions(Context context) {
        this.context = context;
    }

    public void setEmailOptionsListener(VolleyCallback volleyCallback) {
        this.volleyCallback = volleyCallback;
    }

    public void setLocationResponseListener(LocationResponse locationResponse) {
        this.locationResponse = locationResponse;
    }

    public void setDataReceiveandSetListener(DataReceiveandSet dataReceiveandSet){
        this.dataReceiveandSet = dataReceiveandSet;
    }

    public void apiLocation(String rptType,
                            String fillParam1,
                            String fillParam2,
                            String fillParam3,
                            String fillParam4,
                            String fillParam5,
                            String fillParam6,
                            String fillParam7,
                            String fillParam8,
                            String fillParam9,
                            String fillParam0,
                            String companyID,
                            String userID,
                            String lat,
                            String lang) {
        Map<String, String> map = new HashMap<>();
        map.put(RptTypeField.SP_WEB_FILL_RPT, "spWebFillRpt");
        map.put(RptTypeField.RPT_TYPE, rptType);
        map.put(RptTypeField.FILL_PARAM_1, fillParam1);
        map.put(RptTypeField.FILL_PARAM_2, fillParam2);
        map.put(RptTypeField.FILL_PARAM_3, fillParam3);
        map.put(RptTypeField.FILL_PARAM_4, fillParam4);
        map.put(RptTypeField.FILL_PARAM_5, fillParam5);
        map.put(RptTypeField.FILL_PARAM_6, fillParam6);
        map.put(RptTypeField.FILL_PARAM_7, fillParam7);
        map.put(RptTypeField.FILL_PARAM_8, fillParam8);
        map.put(RptTypeField.FILL_PARAM_9, fillParam9);
        map.put(RptTypeField.FILL_PARAM_0, fillParam0);
        map.put(RptTypeField.COMPANY_ID, COMPANY_ID_DEVICE);
        map.put(RptTypeField.USER_ID, userID);
        map.put(RptTypeField.USER_LATITUDE, lat);
        map.put(RptTypeField.USER_LONGITUDE, lang);
        map.put(RptTypeField.CR_USER_NAME, "cct");
        map.put(RptTypeField.CR_USER_PASS, "1234");
        map.put(RptTypeField.IMEI1, DEVICE_IMEI1);
        map.put(RptTypeField.IMEI2, DEVICE_IMEI2);
        map.put(RptTypeField.AndUID, DEVICE_SERIAL_NUMBER);


        String json = new JSONObject(map).toString();
        requestToServer(json);

    }

    public void requestToServer(final String json) {

        Log.d(TAG, "requestToServer: json :"+json);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, context.getString(R.string.api),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        /*
                        String data = response.replaceFirst("(\")","");
                        String responseData = data.substring(0,data.length()-1).replaceAll("\\\\","");
                        */


                        Log.d("json_data", response);
                        //Toast.makeText(context, ""+response, Toast.LENGTH_SHORT).show();
                        volleyCallback.onSuccessResponse(response);

                        //Log.d("TAG",responseData);
                /*ModelClass[] modelClasses =  new Gson().fromJson(responseData,ModelClass[].class);
                etUserId.setText(modelClasses[0].getCustomerID());*/

                        /*
                         * This portion of code
                         * may be needed when the json has multiple objects
                         * */
                /*for (int i=0; i<modelClasses.length; i++){
                    Toast.makeText(CreateAccount.this, ""+modelClasses[0].getcRegDt(), Toast.LENGTH_LONG).show();
                }*/

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("testJson", "" + error.getMessage());
            }
        }) {

            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return json == null ? null : json.getBytes("utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        };

        MySingleton.getInstance(context.getApplicationContext()).addToRequestQueue(stringRequest);
    }

    /*
     * This method is used to get geocode data
     * But this method can be used to fetch data
     * from any url using only GET method
     * for this need to send the three parameters ( requestType, url, header in HashMap params)*/
    public void requestToServerUsingGetMethod(final String requestType, String url, final HashMap<String, String> params) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // here do your operation
                // according to your request type

                Log.d("json_data", response);
                if (requestType == "locationRequest") {
                    locationResponse.getLocationLatLngAddress(String.valueOf(lat), String.valueOf(lng), response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Log.d("json_data",""+error);
                //Toast.makeText(context, "Server Error! Try Later: "+error.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onErrorResponse: " + "Server Error! Try Later: " + error.getMessage());

                locationResponse.getLocationLatLngAddress("", "", "");

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return params;
            }
        };

        MySingleton.getInstance(context.getApplicationContext()).addToRequestQueue(stringRequest);

    }

    /*
     *  send url parameter data "null"
     *  if only need latitude and longitude
     *  if you need latitude, longitude and address of that lat lng
     *  then you have to pass a geocode api url without lat lng and api_key
     */
    public void requestLocation(final String url) {

        client = LocationServices.getFusedLocationProviderClient(context);

        //final String [] latLng = new String[4];

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {

            return;
        } else {

            netState = checkNetworkState(context);

            if(netState.isEmpty()){
                //Toast.makeText(context, "No Internet", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "requestLocation: No Internet. Order will be saved locally");
                locationResponse.getLocationLatLngAddress("", "", "");
            }else {
                
                try {

                    client.getLastLocation().addOnSuccessListener((Activity) context, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            //Toast.makeText(context, "working addOnSuccessListener", Toast.LENGTH_SHORT).show();
                            //Toast.makeText(context, "location: "+location, Toast.LENGTH_SHORT).show();

                            Log.d(TAG, "onSuccess: location" + location);

                            try {


                                if (location != null) {
                                    lat = location.getLatitude();
                                    lng = location.getLongitude();

                                    if (url != null) {

                                        try {
                                            Log.d(TAG, "onSuccess: now");
                                            requestAddress(String.format(url + "json?latlng=%.4f,%.4f&key=" + context.getResources().getString(R.string.geocode_api_key), lat, lng));
                                        } catch (Exception e) {
                                            Toast.makeText(context, "Location Problem: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }

                                    } else {
                                        // here do operations to send the lat and lng
                                        Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
                                    }

                                } else {
                                    Toast.makeText(context, "Location addressing problem", Toast.LENGTH_SHORT).show();
                                    locationResponse.getLocationLatLngAddress("", "", "");
                                }


                            } catch (Exception e) {
                                Toast.makeText(context, "Exception: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                Log.d(TAG, "onSuccess: Exception: " + e.getMessage());
                            }


                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG, "onFailure: " + e.getMessage());
                        }
                    });

                } catch (Exception e) {
                    Log.d(TAG, "requestLocation: fused Location error: " + e.getMessage());
                }
            }
            

            /*client.getLastLocation().addOnFailureListener((Activity) context, new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });*/


        }

    }

    /*
     * This is used to get geocode address
     * If you need geocode data using this method
     * then you need to pass a url to this method containing
     * latitude,longitude and api_key*/
    public void requestAddress(String url) {
        HashMap<String, String> params = new HashMap<>();
        params.put("Content-Type", "application/x-www-form-urlencoded");

        requestToServerUsingGetMethod("locationRequest", url, params);
    }

    @NonNull
    public static String formatdateyyyymmddtoddmmyyyy(String sDODt) {

        String yyyy = substring(sDODt, 0, 4);
        String mm = substring(sDODt, 5, 7);
        String dd = substring(sDODt, 8, 10);

        return dd + "/" + mm + "/" + yyyy;
    }

    public static String deleteLastTwoChar(String data) {

        String s = substring(data, 0, data.length() - 2);

        return s;
    }

    public String DateDialog(Context context) {
        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                latestdate = dayOfMonth + "/" + monthOfYear + "/" + year;
            }
        };
        DatePickerDialog dpDialog = new DatePickerDialog(context, listener, yyyy, mm, dd);
        dpDialog.show();

        return latestdate;
    }

    public static void VolleyError(Context context, VolleyError volleyError) {
        String message = null;
        if (volleyError instanceof NetworkError) {
            message = "Cannot connect to Internet...Please check your connection!";
        } else if (volleyError instanceof ServerError) {
            message = "The server could not be found. Please try again after some time!!";
        } else if (volleyError instanceof AuthFailureError) {
            message = "Cannot connect to Internet...Please check your connection!";
        } else if (volleyError instanceof ParseError) {
            message = "Parsing error! Please try again after some time!!";
        } else if (volleyError instanceof NoConnectionError) {
            message = "Cannot connect to Internet...Please check your connection!";
        } else if (volleyError instanceof TimeoutError) {
            message = "Connection TimeOut! Please check your internet connection.";
        }
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /*
     * ===================== Store Data (Username) In Shared Preferance  ==================
     * */

    /*
     * ============ PREFERENCE_FILE_KEY =============
     * */

    public static void SharedPrefStore(Context context, String valueKey, String value) {

        SharedPreferences.Editor editor = context.getSharedPreferences(context.getString(R.string.preference_file_key), MODE_PRIVATE).edit();
        editor.putString(valueKey, value);
        //editor.apply();
        boolean savedOrNot = editor.commit();

        if (savedOrNot) {
            // Successfully saved

            Log.d(TAG, "SharedPrefStore: Save Successful");

        } else {
            // Not saved
            Log.d(TAG, "SharedPrefStore: Did not saved");
        }

    }

    public static String SharedPrefRetrive(Context context, String valueKey) {
        SharedPreferences prefs = context.getSharedPreferences(context.getString(R.string.preference_file_key), MODE_PRIVATE);
        String restoredText = prefs.getString(valueKey, null);
        if (restoredText != null) {
            Log.d("SharedPrefKey: " + valueKey, "Value: " + restoredText);
            return restoredText;
        } else {
            //Toast.makeText(context, "No data record", Toast.LENGTH_SHORT).show();
            return "No data record";
        }
    }

    public static void SharedPrefClear(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
    }

    /*
     * ============ PREFERENCE_FILE_KEY =============
     * */


    /*
     * ===================== Store Data (Username) In Shared Preferance  ==================
     * */

    public static String getTwoDigits(Double aDouble) {

        String total = String.format("%.2f", aDouble);

        return total;
    }





    /*
     * ================= Check Network State ====================
     * */

    public static String checkNetworkState(Context context) {


        String networkState = "";

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) { // connected to the internet
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                // connected to wifi
                networkState = activeNetwork.getTypeName();
                netState = activeNetwork.getTypeName();
                Log.d(TAG, "onCreate: networkState: " + networkState);
                //Toast.makeText(context, activeNetwork.getTypeName(), Toast.LENGTH_SHORT).show();

                networkAvailable = true;

            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                // connected to the mobile provider's data plan
                //Toast.makeText(context, activeNetwork.getTypeName(), Toast.LENGTH_SHORT).show();
                networkState = activeNetwork.getTypeName();
                netState = activeNetwork.getTypeName();
                Log.d(TAG, "onCreate: networkState: " + networkState);

                networkAvailable = true;

            }
        } else {
            // not connected to the internet
            //Toast.makeText(context, "No newtwork access", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "onCreate: networkState: " + networkState);
            netState = "";

            networkAvailable = false;
        }

        return networkState;
    }

    /*
     * ================= Check Network State ====================
     * */

    /*
     * ==================== Internal Storage ============================
     * */

    public static long getAvailableInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return availableBlocks * blockSize;
    }

    public static long getTotalInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        return totalBlocks * blockSize;
    }


    /*
     * ==================== Internal Storage ============================
     * */


}
