package com.danielcotter.officeprank.client.util;

import com.danielcotter.officeprank.client.config.Config;
import com.danielcotter.officeprank.client.json.ChangeRequest;
import com.danielcotter.officeprank.client.json.ChangeResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class HttpUtil {

    private HttpClient client = new DefaultHttpClient();
    private static final ObjectMapper mapper = new ObjectMapper();

    public Boolean changeState(Boolean state) throws NoSuchAlgorithmException,
            UnsupportedOperationException, ClientProtocolException, IOException {
        List<BasicNameValuePair> postData = new ArrayList<BasicNameValuePair>();
        ChangeRequest request = new ChangeRequest(state, String.valueOf(System
                .currentTimeMillis()), EncryptionUtil.shaHash(String
                .valueOf(System.currentTimeMillis()) + Config.getSecret()));

        postData.add(new BasicNameValuePair("json", mapper
                .writeValueAsString(request)));

        ChangeResponse response = (ChangeResponse) doRequest(ChangeResponse.class,
                Config.getUrl(), postData);

        return response.getSuccess();
    }

    private Object doRequest(Object mappingObject, String url,
                             List<BasicNameValuePair> postData)
            throws UnsupportedOperationException, ClientProtocolException,
            IOException {
        HttpPost post = new HttpPost(url);
        post.setEntity(new UrlEncodedFormEntity(postData));
        String data = getReply(client.execute(post));

        return mapper.readValue(data, (Class<?>) mappingObject);
    }

    private String getReply(HttpResponse response)
            throws UnsupportedOperationException, IOException {
        BufferedReader myReader = new BufferedReader(new InputStreamReader(
                response.getEntity().getContent()));
        String line;
        String data = "";

        while ((line = myReader.readLine()) != null)
            data += line;

        return data;
    }
}
