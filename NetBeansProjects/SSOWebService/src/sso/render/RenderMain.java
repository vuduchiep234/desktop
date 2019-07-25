/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sso.render;

import java.io.IOException;
import java.util.HashMap;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;
import firo.Request;
import firo.Response;
import firo.utils.config.Config;
import java.util.Map;
import org.json.JSONObject;
import static sso.controller.UserController.mapsToken;
import static sso.controller.UserController.tokenCookieName;
import sso.transport.SSOAPIService;
import sso.utils.CookieUtil;
import sso.utils.ServletUtil;

/**
 *
 * @author anbq
 */
public class RenderMain extends RenderEngine {

    private static final Logger log = Log.getLogger(RenderMain.class);
    private static RenderMain _instance = new RenderMain();
    JSONObject userInfo = new JSONObject();

    public static RenderMain getInstance() {
        return _instance;
    }

    public String renderHome(Request request, Response response) throws IOException {
        String content = "";
        Map<String, Object> attributes = new HashMap<>();
        try {
            attributes.put("static_url", Config.getParamString("config", "static_url", ""));
            String contenHeader = getHeader(request, response);
            String contenMenu = RenderEngine.getInstance().render(attributes, "menu.ftl");
            String contenFooter = RenderEngine.getInstance().render(attributes, "footer.ftl");
            attributes.put("static_url", Config.getParamString("config", "static_url", ""));

            attributes.put("HEADER", contenHeader);
            attributes.put("MENU", contenMenu);
            attributes.put("FOOTER", contenFooter);
            content = RenderEngine.getInstance().render(attributes, "home.ftl");
        } catch (Exception ex) {
            ex.printStackTrace();
            log.warn("render renderHome", ex);
        }
        return content;
    }

    public String renderProfile(Request request, Response response) throws IOException {
        String content = "";
        String contenHeader = getHeader(request, response);
        Map<String, Object> attributes = new HashMap<>();
        try {
            String token = CookieUtil.getValue(request, tokenCookieName);
            System.out.println("token==================" + token + "======================");

            if (userInfo == null || userInfo.length() == 0) {
                response.redirect("/");
                return null;
            }
            attributes.put("static_url", Config.getParamString("config", "static_url", ""));

            String contenMenu = RenderEngine.getInstance().render(attributes, "menu.ftl");
            String contenFooter = RenderEngine.getInstance().render(attributes, "footer.ftl");
            attributes.put("static_url", Config.getParamString("config", "static_url", ""));
            attributes.put("HEADER", contenHeader);
            attributes.put("MENU", contenMenu);
            attributes.put("FOOTER", contenFooter);

            attributes.put("hoVaTen", getValueJSOBObject(userInfo, "hoVaTen", "Chưa có thông tin"));
            attributes.put("tenDangNhap", getValueJSOBObject(userInfo, "tenDangNhap", "Chưa có thông tin"));
            attributes.put("gioiTinh", getValueJSOBObject(userInfo, "gioiTinh", "Chưa có thông tin"));
            attributes.put("quocTich", getValueJSOBObject(userInfo, "quocTich", "Chưa có thông tin"));
            attributes.put("danToc", getValueJSOBObject(userInfo, "danToc", "Chưa có thông tin"));
            attributes.put("email", getValueJSOBObject(userInfo, "email", "Chưa có thông tin"));
            attributes.put("ngaySinh", getValueJSOBObject(userInfo, "ngaySinh", "Chưa có thông tin"));
            attributes.put("hoKhauThuongTru", getValueJSOBObject(userInfo, "hoKhauThuongTru", "Chưa có thông tin"));
            attributes.put("soGiayTo", getValueJSOBObject(userInfo, "soGiayTo", "Chưa có thông tin"));
            attributes.put("soDienThoai", getValueJSOBObject(userInfo, "soDienThoai", "Chưa có thông tin"));
            attributes.put("noiCapGiayTo", getValueJSOBObject(userInfo, "noiCapGiayTo", "Chưa có thông tin"));
            attributes.put("ngayCapGiayTo", getValueJSOBObject(userInfo, "ngayCapGiayTo", "Chưa có thông tin"));
            content = RenderEngine.getInstance().render(attributes, "profile.ftl");

        } catch (Exception ex) {
            ex.printStackTrace();
            log.warn("render renderHome", ex);
        }
        return content;
    }

    public String renderUpdateProfile(Request request, Response response) throws IOException {
        String content = "";
        String contenHeader = getHeader(request, response);
        Map<String, Object> attributes = new HashMap<>();
        try {

            if (userInfo == null || userInfo.length() == 0) {
                response.redirect("/");
                return null;
            }
            attributes.put("static_url", Config.getParamString("config", "static_url", ""));

            String contenMenu = RenderEngine.getInstance().render(attributes, "menu.ftl");
            String contenFooter = RenderEngine.getInstance().render(attributes, "footer.ftl");
            attributes.put("static_url", Config.getParamString("config", "static_url", ""));
            attributes.put("HEADER", contenHeader);
            attributes.put("MENU", contenMenu);
            attributes.put("FOOTER", contenFooter);
            String loaiGiayTo = "Chứng minh nhân dân";
            if (getValueJSOBObject(userInfo, "loaiGiayTo", "").equals("2")) {
                loaiGiayTo = "Hộ chiếu";
            }
            attributes.put("hoVaTen", getValueJSOBObject(userInfo, "hoVaTen", ""));
            attributes.put("tenDangNhap", getValueJSOBObject(userInfo, "tenDangNhap", ""));
            attributes.put("gioiTinh", getValueJSOBObject(userInfo, "gioiTinh", ""));
            attributes.put("quocTich", getValueJSOBObject(userInfo, "quocTich", ""));
            attributes.put("danToc", getValueJSOBObject(userInfo, "danToc", ""));
            attributes.put("email", getValueJSOBObject(userInfo, "email", ""));
            attributes.put("ngaySinh", getValueJSOBObject(userInfo, "ngaySinh", ""));
            attributes.put("hoKhauThuongTru", getValueJSOBObject(userInfo, "hoKhauThuongTru", ""));
            attributes.put("soGiayTo", getValueJSOBObject(userInfo, "soDienThoai", ""));
            attributes.put("soDienThoai", getValueJSOBObject(userInfo, "soGiayTo", ""));
            attributes.put("noiCapGiayTo", getValueJSOBObject(userInfo, "noiCapGiayTo", ""));
            attributes.put("ngayCapGiayTo", getValueJSOBObject(userInfo, "ngayCapGiayTo", ""));
            attributes.put("loaiGiayTo", loaiGiayTo);
            attributes.put("tenDuong", getValueJSOBObject(userInfo, "tenDuong", ""));
            attributes.put("tenPhuong", getValueJSOBObject(userInfo, "tenPhuong", ""));
            attributes.put("tenHuyen", getValueJSOBObject(userInfo, "tenHuyen", ""));
            attributes.put("soNha", getValueJSOBObject(userInfo, "soNha", ""));
            attributes.put("diaChiDangKy", getValueJSOBObject(userInfo, "diaChiDangKy", ""));

            content = RenderEngine.getInstance().render(attributes, "updateProfile.ftl");

        } catch (Exception ex) {
            ex.printStackTrace();
            log.warn("render renderHome", ex);
        }
        return content;
    }

    public String renderChangpassword(Request request, Response response) throws IOException {
        String contenHeader = getHeader(request, response);
        String content = "";
        Map<String, Object> attributes = new HashMap<>();
        try {

            if (userInfo == null || userInfo.length() == 0) {
                response.redirect("/");
                return null;
            }
            attributes.put("static_url", Config.getParamString("config", "static_url", ""));

            String contenMenu = RenderEngine.getInstance().render(attributes, "menu.ftl");
            String contenFooter = RenderEngine.getInstance().render(attributes, "footer.ftl");
            attributes.put("static_url", Config.getParamString("config", "static_url", ""));
            attributes.put("HEADER", contenHeader);
            attributes.put("MENU", contenMenu);
            attributes.put("FOOTER", contenFooter);

            attributes.put("hoVaTen", getValueJSOBObject(userInfo, "hoVaTen", ""));
            attributes.put("tenDangNhap", getValueJSOBObject(userInfo, "tenDangNhap", ""));

            content = RenderEngine.getInstance().render(attributes, "changepassword.ftl");

        } catch (Exception ex) {
            ex.printStackTrace();
            log.warn("render renderHome", ex);
        }
        return content;
    }

    public String renderSyncAccount(Request request, Response response) throws IOException {
        String content = "";
        String contenHeader = getHeader(request, response);
        Map<String, Object> attributes = new HashMap<>();
        try {
            attributes.put("static_url", Config.getParamString("config", "static_url", ""));

            String contenMenu = RenderEngine.getInstance().render(attributes, "menu.ftl");
            String contenFooter = RenderEngine.getInstance().render(attributes, "footer.ftl");
            attributes.put("static_url", Config.getParamString("config", "static_url", ""));
            attributes.put("HEADER", contenHeader);
            attributes.put("MENU", contenMenu);
            attributes.put("FOOTER", contenFooter);

            content = RenderEngine.getInstance().render(attributes, "syncAccount.ftl");

        } catch (Exception ex) {
            ex.printStackTrace();
            log.warn("render renderHome", ex);
        }
        return content;
    }

    public String renderUpdateAccount(Request request, Response response) throws IOException {
        String content = "";
        String contenHeader = getHeader(request, response);
        Map<String, Object> attributes = new HashMap<>();
        try {
            attributes.put("static_url", Config.getParamString("config", "static_url", ""));

            String contenMenu = RenderEngine.getInstance().render(attributes, "menu.ftl");
            String contenFooter = RenderEngine.getInstance().render(attributes, "footer.ftl");
            attributes.put("static_url", Config.getParamString("config", "static_url", ""));
            attributes.put("HEADER", contenHeader);
            attributes.put("MENU", contenMenu);
            attributes.put("FOOTER", contenFooter);

            attributes.put("hoVaTen", " bui quang an");
            attributes.put("soGiayTo", "988239525235");
            attributes.put("ngaySinh", "28-09-1992");
            content = RenderEngine.getInstance().render(attributes, "updateAccount.ftl");

        } catch (Exception ex) {
            ex.printStackTrace();
            log.warn("render renderHome", ex);
        }
        return content;
    }

    public String getHeader(Request request, Response response) {
        String content = "";
        try {
            Map<String, Object> attributes = new HashMap<>();
            String configUrl = Config.getParamString("config", "static_url", "");
            attributes.put("static_url", configUrl);
            String htmlUserInfo = "<a class=\"p-2 text-dark cd-signin\" href=\"#\">Đăng nhập</a>";

            String token = CookieUtil.getValue(request, tokenCookieName);
            userInfo = new JSONObject();
            System.out.println("token==================" + token + "======================");

            if (token != null && !token.equals("null")) {
                JSONObject data = new JSONObject();
                data.put("sessionKey", token);
                JSONObject responeAPI = SSOAPIService.getInstance().postApi("user/validateSession", data);
                if (responeAPI.getString("error_code").equals("SUCCESSFUL")) {
                    userInfo = responeAPI.getJSONObject("data");
                }
            }
            if (userInfo != null && userInfo.length() > 0) {
                htmlUserInfo = "<div class=\"user-login\">\n"
                        + "                                            <div style=\"float:right;margin-bottom: -13px;\" class=\"dropdown show\">\n"
                        + "                                            <img class=\"avata-login\" src=\"" + configUrl + "assets/img/avata.png\">\n"
                        + "                                            <a class=\"btn btn-secondary dropdown-toggle\" href=\"profile?id=" + userInfo.getInt("id") + "\" role=\"button\" id=\"dropdownMenuLink\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\n"
                        + "                                              " + userInfo.getString("tenDangNhap") + "\n"
                        + "                                              <i class=\"fa fa-angle-down\"></i>\n"
                        + "                                            </a>\n"
                        + "                                            \n"
                        + "                                            <div class=\"dropdown-menu\" aria-labelledby=\"dropdownMenuLink\">\n"
                        + "                                              <a class=\"dropdown-item\" href=\"/profile\">Hồ sơ của tôi</a>\n"
                        + "                                              </br>\n"
                        + "                                              <a class=\"dropdown-item\" href=\"/user/logout\">Thoát</a>\n"
                        + "                                            </div>\n"
                        + "                                          </div>\n"
                        + "                                        </div>";

            }

            attributes.put("USERINFO", htmlUserInfo);
            content = RenderEngine.getInstance().render(attributes, "header.ftl");
        } catch (Exception ex) {
            ex.printStackTrace();
            log.warn("render renderHeader", ex);
        }
        return content;
    }

    public String validateToken(Request request, Response response) throws IOException {
        String content = "";
        String contenHeader = getHeader(request, response);
        Map<String, Object> attributes = new HashMap<>();

        String email = request.raw().getParameter("email");
        String token = request.raw().getParameter("token");
        String messages = "<p>Tài khoản đã được kích hoạt hoặc token không chính xác. Bạn vui lòng kiểm tra lại</p>";

        try {
            if (token.equals(mapsToken.get(email))) {
                JSONObject resActive = SSOAPIService.getInstance().getApi("user/active/" + email);
                if (resActive.getString("error_code").equals("SUCCESSFUL")) {
                    messages = "<p>Kích hoạt tài khoản thành công.</p>";
                    mapsToken.remove(email);
                }

            }
            attributes.put("static_url", Config.getParamString("config", "static_url", ""));

            String contenMenu = RenderEngine.getInstance().render(attributes, "menu.ftl");
            String contenFooter = RenderEngine.getInstance().render(attributes, "footer.ftl");
            attributes.put("static_url", Config.getParamString("config", "static_url", ""));
            attributes.put("HEADER", contenHeader);
            attributes.put("MENU", contenMenu);
            attributes.put("FOOTER", contenFooter);

            attributes.put("messages", messages);
            content = RenderEngine.getInstance().render(attributes, "activeAccount.ftl");

        } catch (Exception ex) {
            ex.printStackTrace();
            log.warn("render renderHome", ex);
        }
        return content;
    }

    public String getValueJSOBObject(JSONObject object, String key, String defaultValue) {
        String value = "";
        try {
            if (object.has(key) && object.getString(key) != null && !object.getString(key).equals("null")) {
                value = object.get(key).toString();
            } else {
                value = defaultValue;
            }
        } catch (Exception e) {
            System.out.println("Exeption:" + e.toString());
        }
        return value;
    }

    public static Map<String, String> getQueryMap(String query) {
        String[] params = query.split("&");
        Map<String, String> map = new HashMap<String, String>();
        for (String param : params) {
            String name = param.split("=")[0];
            String value = param.split("=")[1];
            map.put(name, value);
        }
        return map;
    }

    public String renderLogin(Request request, Response response) throws IOException {
        String content = "";
        Map<String, Object> attributes = new HashMap<>();
        try {
            attributes.put("static_url", Config.getParamString("config", "static_url", ""));

            String contenFooter = RenderEngine.getInstance().render(attributes, "footer.ftl");
            attributes.put("static_url", Config.getParamString("config", "static_url", ""));
            attributes.put("FOOTER", contenFooter);

            content = RenderEngine.getInstance().render(attributes, "demo_login.ftl");

        } catch (Exception ex) {
            ex.printStackTrace();
            log.warn("render renderHome", ex);
        }
        return content;
    }

    public String renderRegister(Request request, Response response) throws IOException {
        String content = "";
        Map<String, Object> attributes = new HashMap<>();
        try {
            attributes.put("static_url", Config.getParamString("config", "static_url", ""));
            String contentFooter = RenderEngine.getInstance().render(attributes, "footer.ftl");
            attributes.put("static_url", Config.getParamString("config", "static_url", ""));
            attributes.put("FOOTER", contentFooter);

            content = RenderEngine.getInstance().render(attributes, "demo_register.ftl");
        } catch (Exception ex) {
            ex.printStackTrace();
            log.warn("render renderHome", ex);
        }
        return content;
    }
}
