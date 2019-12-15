package com.example.android_resapi.ui.apicall;

import android.app.Activity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.example.android_resapi.R;
import com.example.android_resapi.httpconnection.GetRequest;

public class GetThingShadow extends GetRequest {
    final static String TAG = "AndroidAPITest";
    String urlStr;
    public GetThingShadow(Activity activity, String urlStr) {
        super(activity);
        this.urlStr = urlStr;
    }

    @Override
    protected void onPreExecute() {
        try {
            Log.e(TAG, urlStr);
            url = new URL(urlStr);

        } catch (MalformedURLException e) {
            Toast.makeText(activity,"URL is invalid:"+urlStr, Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            activity.finish();
        }
    }

    @Override
    protected void onPostExecute(String jsonString) {
        if (jsonString == null)
            return;
        Map<String, String> state = getStateFromJSONString(jsonString);
        TextView reported_ledTV = activity.findViewById(R.id.reported_led);
        TextView reported_coTV = activity.findViewById(R.id.reported_co);
        TextView reported_motorTV = activity.findViewById(R.id.reported_motor);
        reported_coTV.setText(state.get("reported_CO"));
        reported_ledTV.setText(state.get("reported_LED"));
        reported_motorTV.setText(state.get("reported_MOTOR"));

        TextView desired_ledTV = activity.findViewById(R.id.desired_led);
        TextView desired_coTV = activity.findViewById(R.id.desired_co);
        TextView desired_motorTV = activity.findViewById(R.id.desired_motor);
        desired_coTV.setText(state.get("desired_CO"));
        desired_ledTV.setText(state.get("desired_LED"));
        desired_motorTV.setText(state.get("desired_MOTOR"));

    }

    protected Map<String, String> getStateFromJSONString(String jsonString) {
        Map<String, String> output = new HashMap<>();
        try {
            // 처음 double-quote와 마지막 double-quote 제거
            jsonString = jsonString.substring(1,jsonString.length()-1);
            // \\\" 를 \"로 치환
            jsonString = jsonString.replace("\\\"","\"");
            Log.i(TAG, "jsonString="+jsonString);
            JSONObject root = new JSONObject(jsonString);
            JSONObject state = root.getJSONObject("state");
            JSONObject reported = state.getJSONObject("reported");
            String coValue = reported.getString("CO");
            String ledValue = reported.getString("LED");
            String motorValue = reported.getString("MOTOR");
            output.put("reported_CO", coValue);
            output.put("reported_LED",ledValue);
            output.put("reported_MOTOR",motorValue);

            JSONObject desired = state.getJSONObject("desired");
            String desired_coValue = desired.getString("CO");
            String desired_ledValue = desired.getString("LED");
            String desired_motorValue = desired.getString("MOTOR");
            output.put("desired_CO", desired_coValue);
            output.put("desired_LED",desired_ledValue);
            output.put("desired_MOTOR",desired_motorValue);
        } catch (JSONException e) {
            Log.e(TAG, "Exception in processing JSONString.", e);
            e.printStackTrace();
        }
        return output;
    }
}
