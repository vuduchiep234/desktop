/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sso.transport;

import firo.utils.config.Config;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author anbq
 */
public class SendEmailService {

    private static final Logger _logger = LoggerFactory.getLogger(SendEmailService.class);
    private static SendEmailService _instance;
    private static String _host = "";
    private static int _port = 0;

    static {
        _host = Config.getParamString("SendEmail", "host", "");
        _port = Config.getParamInt("SendEmail", "port", 2007);

    }

    public static SendEmailService getInstance() {
        if (_instance == null) {
            _instance = new SendEmailService();
        }

        return _instance;
    }

    public JSONObject getApi(String path) {
        JSONObject respon = new JSONObject();
        try {
            URL url = new URL("http://" + _host + ":" + _port + "/" + path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            respon.put("error_code", -1);
            respon.put("error_message", "Can not connect to backend!");
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        (conn.getInputStream())));
                String res = "";
                String output;
                while ((output = br.readLine()) != null) {
                    res += output;
                }
                respon = new org.json.JSONObject(res);
            } else {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respon;
    }

    public JSONObject postApi(String path, JSONObject data) {
        JSONObject respon = new JSONObject();
        try {
            URL url = new URL("http://" + _host + ":" + _port + "/" + path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            OutputStream os = conn.getOutputStream();
            respon.put("error_code", -1);
            respon.put("error_message", "Can not connect to backend!");
            os.write(data.toString().getBytes());
            os.flush();

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        (conn.getInputStream())));
                String res = "";
                String output;
                while ((output = br.readLine()) != null) {
                    res += output;
                }
                respon = new org.json.JSONObject(res);
            } else {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respon;
    }

    public JSONObject putApi(String path, JSONObject data) {
        JSONObject respon = new JSONObject();
        try {
            URL url = new URL("http://" + _host + ":" + _port + "/" + path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");
            OutputStream os = conn.getOutputStream();
            os.write(data.toString().getBytes());
            os.flush();
            respon.put("error_code", -1);
            respon.put("error_message", "Can not connect to backend!");
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        (conn.getInputStream())));
                String res = "";
                String output;
                while ((output = br.readLine()) != null) {
                    res += output;
                }
                respon = new org.json.JSONObject(res);
            } else {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException | RuntimeException e) {
            e.printStackTrace();
        }
        return respon;
    }

    public JSONObject deleteApi(String path) {
        JSONObject respon = new JSONObject();
        try {
            URL url = new URL("http://" + _host + ":" + _port + "/" + path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("DELETE");
            conn.setRequestProperty("Content-Type", "application/json");
            respon.put("error_code", -1);
            respon.put("error_message", "Can not connect to backend!");
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        (conn.getInputStream())));
                String res = "";
                String output;
                while ((output = br.readLine()) != null) {
                    res += output;
                }
                respon = new org.json.JSONObject(res);
            } else {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException | RuntimeException e) {
            e.printStackTrace();
        }
        return respon;
    }

    public JSONObject deleteByconditionApi(String path, JSONObject data) {
        JSONObject respon = new JSONObject();
        try {
            URL url = new URL("http://" + _host + ":" + _port + "/" + path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("DELETE");
            conn.setRequestProperty("Content-Type", "application/json");
            OutputStream os = conn.getOutputStream();
            os.write(data.toString().getBytes());
            os.flush();
            respon.put("error_code", -1);
            respon.put("error_message", "Can not connect to backend!");
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        (conn.getInputStream())));
                String res = "";
                String output;
                while ((output = br.readLine()) != null) {
                    res += output;
                }
                respon = new org.json.JSONObject(res);
            } else {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException | RuntimeException e) {
            e.printStackTrace();
        }
        return respon;
    }

    public String checkSession(String key) {
        JSONObject respon = new JSONObject();
        String username;
        try {
            JSONObject data = new JSONObject();
            data.put("sessionKey", key);
            URL url = new URL("http://" + _host + ":" + _port + "/User/action/getSession");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            OutputStream os = conn.getOutputStream();
            os.write(data.toString().getBytes());
            os.flush();

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        (conn.getInputStream())));
                String res = "";
                String output;
                while ((output = br.readLine()) != null) {
                    res += output;
                }
                org.json.JSONObject jsonObj = new org.json.JSONObject(res);
                if (jsonObj.isNull("data")) {
                    return null;
                }
                username = jsonObj.getJSONObject("data").getString("username");
            } else {
                respon.put("responseCode", conn.getResponseCode());
                respon.put("responseMessage", "Failed : HTTP error code!");
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            conn.disconnect();
            return username;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    
}
