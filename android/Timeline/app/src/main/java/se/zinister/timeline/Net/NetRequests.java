package se.zinister.timeline.Net;

import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import se.zinister.timeline.MainActivity;


/**
 * Created by josef on 2015-04-06.
 */
public class NetRequests {
    String host;
    HttpURLConnection urlConnection;
    URL url;
    CookieManager cookieManager;



    public NetRequests(String h) {
        host = h;
        cookieManager = new CookieManager( null, CookiePolicy.ACCEPT_ALL );
        CookieHandler.setDefault(cookieManager);
    }
    public NetRequests(String h, boolean b) {
        host=h;
    }

    public String login() {
        try {

            url = new URL("https://www.google.com/accounts/ServiceLogin?service=ah&passive=true&continue=https://appengine.google.com/_ah/conflogin%3Fcontinue%3Dhttp://www.calcium-firefly-93808.appspot.com/&ltmpl=gm&shdf=ChQLEgZhaG5hbWUaCHRpbWVsaW5lDBICYWgiFEe8n7wQtuUtbgPCn7jK2fn05U8MKAEyFDngw08K4pxz3An0VB-Sd9Gfl-fw");
            String cookies = android.webkit.CookieManager.getInstance().getCookie(url.toString());
            Log.d("COKIES", "All the cookies in a string from url "+url.toString()+ ": " + cookies);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            BufferedReader r = new BufferedReader(new InputStreamReader(in));
            StringBuilder total = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                total.append(line);
            }
            return total.toString();

        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            if (urlConnection != null)
                urlConnection.disconnect();
        }
        return "error";
    }

    public String joinGame() {

        try {
            url = new URL("https://" + host + "/joinGame");
            String cookies = android.webkit.CookieManager.getInstance().getCookie(url.toString());
            Log.d("COKIES", "All the cookies in a string from url "+url.toString()+ ": " + cookies);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            BufferedReader r = new BufferedReader(new InputStreamReader(in));
            StringBuilder total = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                total.append(line);
            }
            return total.toString();

        } catch (IOException e) {
            e.printStackTrace();
        } finally{
                if (urlConnection != null)
                urlConnection.disconnect();
            }
       return "error";
        }

    public String getQuestions(String gameID) {
        URL url;
        try {
            url = new URL("https://"+ host +"/match?action=getQuestions&game_id=" + gameID);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader r = new BufferedReader(new InputStreamReader(in));
            StringBuilder total = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                total.append(line);
            }
            return total.toString();

        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            if (urlConnection != null)
                urlConnection.disconnect();
        }
        return "error";
    }


    public String answerQuestions(int gameID, String a1,String a2, String a3, String a4, String a5){
        URL url;
        try {
            url = new URL( "https://" + host +"/match?action=answerQuestions&game_id=" + gameID + "&a1=" + a1 +
                    "&a2=" + a2 + "&a3=" + a3 + "&a4=" + a4 + "&a5=" + a5);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Cookie",
                    TextUtils.join(",", cookieManager.getCookieStore().getCookies()));
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader r = new BufferedReader(new InputStreamReader(in));
            StringBuilder total = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                total.append(line);
            }
            return total.toString();

        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            if (urlConnection != null)
                urlConnection.disconnect();
        }
        return "error";
    }


    public String registerUSer() {
            if (urlConnection != null) urlConnection.disconnect();
        try {

            url = new URL("https://" +host +"/registerNewUser");
            String cookies = android.webkit.CookieManager.getInstance().getCookie(url.toString());
            Log.d("COKIES", "cookies from url "+url.toString()+ ": " + cookies);

            Map <String, List<String>> f = urlConnection.getHeaderFields();
            for(Map.Entry<String,List<String>> x: f.entrySet()){
                String key = x.getKey();
                for (String list:x.getValue()) {
                    Log.d("MINsss", key + " " + list);
                }
            }

            urlConnection.setDoOutput(true);

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader r = new BufferedReader(new InputStreamReader(in));
            StringBuilder total = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                total.append(line);
            }
            return total.toString();

        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            if (urlConnection != null)
                urlConnection.disconnect();
        }
        return "error";

    }

    public String startPage() {

        try {
            url = new URL("https://" +host +"/startMess");
            String cookies = android.webkit.CookieManager.getInstance().getCookie(url.toString());
            Log.d("COKIES", "All the cookies in a string from url "+url.toString()+ ": " + cookies);
            urlConnection = (HttpURLConnection) url.openConnection();
            Map <String, List<String>> f = urlConnection.getHeaderFields();
            for(Map.Entry<String,List<String>> x: f.entrySet()){
                String key = x.getKey();
                for (String list:x.getValue()) {
                    Log.d("MINsss", key + " " + list);
                }

            }
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader r = new BufferedReader(new InputStreamReader(in));
            StringBuilder total = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                total.append(line);
            }
            return total.toString();

        } catch (IOException e) {
            Log.d("hehe","ssss");
            e.printStackTrace();
        } finally{
            if (urlConnection != null)
                urlConnection.disconnect();
        }
        Log.d("hehe","slut");
        return "error";

    }

    public String friendList() {

        try {
            url = new URL("https://" +host +"/friendlist");
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader r = new BufferedReader(new InputStreamReader(in));
            StringBuilder total = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                total.append(line);
            }
            return total.toString();

        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            if (urlConnection != null)
                urlConnection.disconnect();
        }
        return "error";

    }
    public String search(String action, String parameter) {

        try {
            url = new URL("https://" +host +"/search?type=" + action+"&search=" + parameter);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader r = new BufferedReader(new InputStreamReader(in));
            StringBuilder total = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                total.append(line);
            }
            return total.toString();

        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            if (urlConnection != null)
                urlConnection.disconnect();
        }
        return "error";

    }


    public String friend(String action, String id) {

        try {
            url = new URL("https://" +host +"/friend?action=" + action+"&friend_id=" + id);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader r = new BufferedReader(new InputStreamReader(in));
            StringBuilder total = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                total.append(line);
            }
            return total.toString();

        } catch (IOException e) {
            e.printStackTrace();
        } finally{
             if (urlConnection != null)
                 urlConnection.disconnect();
        }
        return "error";

    }

    public String game(String id) {

        try {
            url = new URL("https://" +host +"/getGameInfo?game_id=" + id);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader r = new BufferedReader(new InputStreamReader(in));
            StringBuilder total = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                total.append(line);
            }
            return total.toString();

        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            if (urlConnection != null)
                urlConnection.disconnect();
        }
        return "error";

    }

 }