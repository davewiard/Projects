package com.davewiard;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashSet;

public class ServiceSchedules {
    private JSONObject serviceSchedules;
    private String postData;
    private String zoomApiUrl;


    /**
     * Search for the clinic represented by the given clinicCode string. If the
     * clinic appears in multiple serviceSchedules entries it is assumed that the
     * hours will be the same in all the entries.
     *
     * @param clinicCode String representing the clinicCode being searched for.
     * @return JSONArray of all hours on all days for the clinic found
     */
    public JSONArray getClinicHours(String clinicCode) {
        JSONArray json = null;

        JSONArray serviceScheduleArray = this.serviceSchedules.getJSONArray("serviceSchedules");
        for (int i = 0; i < serviceScheduleArray.length(); i++) {
            JSONObject serviceSchedule = (JSONObject) serviceScheduleArray.get(i);
            if (serviceSchedule.isNull("scheduleDateString")) {
                // skip this schedule because it doesn't contain relevant data
                continue;
            }

            JSONArray clinics = serviceSchedule.getJSONArray("clinics");
            for (int j = 0; j < clinics.length(); j++) {
                if (clinicCode.equals(clinics.getJSONObject(j).getString("clinicCode"))) {
                    json = clinics.getJSONObject(j).getJSONArray("clinicHours");
                    break;
                }
            }
        }

        return json;
    }


    /**
     * Returns a complete, unique set of clinic codes. The result from the API may have
     * more than one entry in the serviceSchedules array. Loop through all returned
     * serviceSchedules entries to get the complete list of clinics.
     *
     * ASSUMPTION:
     * If the same clinic appears in more than one serviceSchedules entry it is assumed
     * that both entries will have the same set of hours.
     */
    public HashSet<String> getClinicCodes() {
        HashSet<String> clinicSet = new HashSet<>();

        JSONArray serviceScheduleArray = this.serviceSchedules.getJSONArray("serviceSchedules");
        for (int i = 0; i < serviceScheduleArray.length(); i++) {
            JSONObject serviceSchedule = (JSONObject) serviceScheduleArray.get(i);
            if (serviceSchedule.isNull("scheduleDateString")) {
                // skip this schedule because it doesn't contain relevant data
                continue;
            }

            // loop through all the clinics and display the number to enter and
            // the clinicCode
            JSONArray clinics = serviceSchedule.getJSONArray("clinics");
            for (int j = 0; j < clinics.length(); j++) {
                String clinicCode = clinics.getJSONObject(j).getString("clinicCode");
                clinicSet.add(clinicCode);
            }
        }

        return clinicSet;
    }


    /**
     *
     * @param postData
     */
    public void setPostData(String postData) {
        this.postData = postData;

        setScheduleDate();
    }


    /**
     * Sets the JSON POST data scheduleDate to today so the API can provide
     * relevant results.
     */
    private void setScheduleDate() {
        // do nothing if the POST data string is not set yet
        if (this.postData.length() <= 0) {
            return;
        }

        // update the POST data with current milliseconds since the epoch
        // this allows ZOOM+Care API to provide current information
        long epoch = System.currentTimeMillis();
        JSONObject postData = new JSONObject(this.postData);
        postData.put("scheduleDate", epoch);
        this.postData = postData.toString();
    }


    /**
     * Sets the URL for the ZOOM+Care Schedule API. Make this "dynamic" so
     * if a different URL needs to be used it can easily be swapped out
     * with the new one.
     *
     * @param zoomApiUrl complete URL to the ZOOM+Care Schedule API
     */
    public void setZoomApiUrl(String zoomApiUrl) {
        this.zoomApiUrl = zoomApiUrl;
    }


    /**
     * Execute the POST request and read the results. The results are returned
     * as a JSON formatted string. Convert the string to a JSONObject and store
     * the result in the serviceSchedules private class member.
     *
     * @return JSONObject representing the complete result of the POST request
     * @throws IOException
     * @throws JSONException
     */
    JSONObject updateServiceSchedulesFromAPI() throws IOException, JSONException {
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(this.zoomApiUrl);
        httpPost.setEntity(new StringEntity(this.postData));

        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        try (CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpPost);) {
            String responseContent = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
            this.serviceSchedules = new JSONObject(responseContent);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return this.serviceSchedules;
    }
}
