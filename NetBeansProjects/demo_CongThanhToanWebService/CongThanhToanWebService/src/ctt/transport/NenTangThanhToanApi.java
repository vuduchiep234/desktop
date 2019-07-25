/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctt.transport;

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
public class NenTangThanhToanApi {

    private static final Logger _logger = LoggerFactory.getLogger(NenTangThanhToanApi.class);
    private static NenTangThanhToanApi _instance;
    private static String _host = "";
    private static int _port = 0;

    static {
        _host = Config.getParamString("NenTangThanhToanAPI", "host", "");
        _port = Config.getParamInt("NenTangThanhToanAPI", "port", 2004);

    }

    public static NenTangThanhToanApi getInstance() {
        if (_instance == null) {
            _instance = new NenTangThanhToanApi();
        }

        return _instance;
    }

    public JSONObject getApi(String path) {
        JSONObject jsonObj = new JSONObject();
        try {
            URL url = new URL("http://" + _host + ":" + _port + "/" + path);
//            System.out.println("path :"+"http://" + _host + ":" + _port + "/" + path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            jsonObj.put("error_code", -1);
            jsonObj.put("error_message", "Can not connect to backend!");
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        (conn.getInputStream())));
                String res = "";
                String output;
                while ((output = br.readLine()) != null) {
                    res += output;
                }
                jsonObj = new org.json.JSONObject(res);
                
//                System.out.println("output :"+res);
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
        return jsonObj;
    }

    public JSONObject postApi(String path, JSONObject data) {
        JSONObject jsonObj = new JSONObject();
        try {
            URL url = new URL("http://" + _host + ":" + _port + "/" + path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            OutputStream os = conn.getOutputStream();
            jsonObj.put("error_code", -1);
            jsonObj.put("error_message", "Can not connect to backend!");
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
                jsonObj = new org.json.JSONObject(res);
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
        return jsonObj;
    }

    public JSONObject putApi(String path, JSONObject data) {
        JSONObject jsonObj = new JSONObject();
        try {
            URL url = new URL("http://" + _host + ":" + _port + "/" + path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");
            OutputStream os = conn.getOutputStream();
            os.write(data.toString().getBytes());
            os.flush();
            jsonObj.put("error_code", -1);
            jsonObj.put("error_message", "Can not connect to backend!");
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        (conn.getInputStream())));
                String res = "";
                String output;
                while ((output = br.readLine()) != null) {
                    res += output;
                }
                jsonObj = new org.json.JSONObject(res);
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
        return jsonObj;
    }

    public JSONObject deleteApi(String path) {
        JSONObject jsonObj = new JSONObject();
        try {
            URL url = new URL("http://" + _host + ":" + _port + "/" + path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("DELETE");
            conn.setRequestProperty("Content-Type", "application/json");
            jsonObj.put("error_code", -1);
            jsonObj.put("error_message", "Can not connect to backend!");
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        (conn.getInputStream())));
                String res = "";
                String output;
                while ((output = br.readLine()) != null) {
                    res += output;
                }
                jsonObj = new org.json.JSONObject(res);
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
        return jsonObj;
    }

    public JSONObject deleteByconditionApi(String path, JSONObject data) {
        JSONObject jsonObj = new JSONObject();
        try {
            URL url = new URL("http://" + _host + ":" + _port + "/" + path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("DELETE");
            conn.setRequestProperty("Content-Type", "application/json");
            OutputStream os = conn.getOutputStream();
            os.write(data.toString().getBytes());
            os.flush();
            jsonObj.put("error_code", -1);
            jsonObj.put("error_message", "Can not connect to backend!");
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        (conn.getInputStream())));
                String res = "";
                String output;
                while ((output = br.readLine()) != null) {
                    res += output;
                }
                jsonObj = new org.json.JSONObject(res);
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
        return jsonObj;
    }

}
