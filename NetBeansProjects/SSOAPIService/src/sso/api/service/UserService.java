/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sso.api.service;

import com.google.gson.Gson;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.Random;
import org.bson.Document;
import org.json.JSONObject;
import sso.api.conector.mongodb.CountersCollection;
import sso.api.conector.mongodb.ExceptionsCollection;
import sso.api.conector.mongodb.ObjectMongoDBCollections;
import sso.api.conector.mongodb.UserMongoDBConllection;
import sso.api.conector.sql.ObjectSQLCollections;
import sso.api.conector.sql.UserSQLConllection;
import sso.api.configuration.Configuration;
import sso.api.model.DataResponse;
import sso.api.model.SessionUser;
import sso.api.model.User;
import static sso.api.util.ConfigUtil.convertJsonToObject;
import static sso.api.util.ConfigUtil.dbTypeSQL;
import sso.api.util.JwtUtil;

/**
 *
 * @author anbq
 */
public class UserService {

    private static UserService instance = null;
    private static String objectName = "User";
    private static String objectSessionUserName = "SessionUser";
    private static final String tokenCookieName = "SSO-TOKEN";
    private static final String signingKey = "signingKey";
    Random random = new Random();

    public synchronized static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public DataResponse signUp(String sData) {
        try {
            JSONObject data = new JSONObject(sData);
            String tenDangNhap = data.getString("tenDangNhap");
            String passwordHash = makePasswordHash(data.getString("matKhau"), Integer.toString(random.nextInt()));
            if (dbTypeSQL()) {
                User user = UserSQLConllection.getInstance().getUserByField("tenDangNhap", tenDangNhap);
                if (user != null) {
                    return new DataResponse("1003", "Tên đăng nhập đã tồn tại.");
                }
                User userByEmail = UserSQLConllection.getInstance().getUserByField("email", data.getString("email"));
                if (userByEmail != null) {
                    return new DataResponse("1005", "Email đã tồn tại.");
                }
                user = (User) convertJsonToObject(sData, User.class);
                user.setMatKhau(passwordHash);
                return ObjectSQLCollections.getInstance().create(objectName, user);
            } else {
                User user = UserMongoDBConllection.getInstance().getUserByField("tenDangNhap", tenDangNhap);
                if (user != null) {
                    return new DataResponse("1003", "Ten dang nhap da ton tai");
                }
                Document document = Document.parse(sData);
                Long id = CountersCollection.getInstance().getNextValue(objectName);
                if (id <= 0) {
                    return DataResponse.COUNTERS_ERROR;
                }
                document.append("matKhau", passwordHash);
                document.append("id", id);
                return ObjectMongoDBCollections.getInstance().create(objectName, document);
            }
        } catch (Exception ex) {
            ExceptionsCollection.getInstance().addException(
                    Configuration.SERVICE_NAME,
                    ex.getStackTrace()[0].toString(),
                    ex.toString());
            return new DataResponse("-1", ex.getMessage());
        }
    }

    public DataResponse logIn(String tenDangNhap, String matKhau) {
        String newToken = JwtUtil.generateToken(signingKey, tenDangNhap);
        SessionUser newSession = new SessionUser();
        newSession.setExpriceTime(new Date(System.currentTimeMillis() + 86400));
        newSession.setSessionKey(newToken);
        newSession.setRold("FULL");
        if (dbTypeSQL()) {
            User user = UserSQLConllection.getInstance().getUserLogin(tenDangNhap);
            if (user == null || user.getId() <= 0) {
                return DataResponse.FAILED;
            }
            String hashedAndSalted = user.getMatKhau();
            System.out.println("Passsworddd:" + hashedAndSalted);
            String salt = hashedAndSalted.split(",")[1];
            if (!hashedAndSalted.equals(makePasswordHash(matKhau, salt))) {
                return DataResponse.FAILED;
            }
            if (user.getTrangThai() == 0) {
                return DataResponse.NO_ACTIVE;
            }
            newSession.setNguoiDungId(user.getId());
            int result = UserSQLConllection.getInstance().addSession(newSession);
            if (result <= 0) {
                return DataResponse.FAILED;
            }
            return new DataResponse(new Document("sessionKey", newToken));
        } else {
            User user = UserMongoDBConllection.getInstance().getUserByField("tenDangNhap", tenDangNhap);
            if (user == null || user.getId() <= 0) {
                return DataResponse.FAILED;
            }
            String hashedAndSalted = user.getMatKhau();
            String salt = hashedAndSalted.split(",")[1];
            if (!hashedAndSalted.equals(makePasswordHash(matKhau, salt))) {
                return DataResponse.FAILED;
            }
            Long id = CountersCollection.getInstance().getNextValue(objectSessionUserName);
            newSession.setId(id.intValue());
            newSession.setNguoiDungId(user.getId());
            int result = UserMongoDBConllection.getInstance().addSession(newSession);
            if (result <= 0) {
                return DataResponse.FAILED;
            }
            return new DataResponse(new Document("sessionKey", newToken));
        }

    }

    public DataResponse changePassword(int id, String password, String newPassword) {
        if (dbTypeSQL()) {
            User user = UserSQLConllection.getInstance().getUserByField("id", id);
            String hashedAndSalted = user.getMatKhau();

            String salt = hashedAndSalted.split(",")[1];
            String hashPassword = makePasswordHash(password, salt);
            System.out.println("Passsworddd:" + hashedAndSalted);
            System.out.println("hashPassword:" + hashPassword);

            if (!hashedAndSalted.equals(hashPassword)) {
                return DataResponse.FAILED;
            }
            String passwordHash = makePasswordHash(newPassword, Integer.toString(random.nextInt()));
            user.setMatKhau(passwordHash);
            System.out.println("Changpassword user:" + user.getMatKhau());
            return ObjectSQLCollections.getInstance().update(objectName, user);
        } else {
            User user = UserMongoDBConllection.getInstance().getUserByField("id", id);
            String hashedAndSalted = user.getMatKhau();
            String salt = hashedAndSalted.split(",")[1];
            if (!hashedAndSalted.equals(makePasswordHash(password, salt))) {
                return DataResponse.FAILED;
            }
            String passwordHash = makePasswordHash(newPassword, Integer.toString(random.nextInt()));
            user.setMatKhau(passwordHash);
            Gson gson = new Gson();
            String json = gson.toJson(user);
            Document document = Document.parse(json);
            return ObjectMongoDBCollections.getInstance().update(objectName, Long.valueOf(id), document);
        }
    }

    public DataResponse checkSyncAccount(String soGiayTo, String hoVaTen, String ngaySinh) {
        if (dbTypeSQL()) {
            User user = UserSQLConllection.getInstance().getUserSync(soGiayTo, hoVaTen, ngaySinh);
            if (user == null || user.getId() <= 0) {
                return DataResponse.FAILED;
            } else if (user.getTenDangNhap() != null && !"null".equals(user.getTenDangNhap())) {
                //send email
                return new DataResponse("2", "Tai khoan da duoc dong bo");
            } else if (user.getEmail() == null || "null".equals(user.getEmail())) {
                //send email
                return new DataResponse("3", "Tai khoan chua dang ky email");
            }
            return new DataResponse(new Document("email", user.getEmail()));
        } else {
            return DataResponse.SUCCESSFUL;
        }
    }

    public DataResponse syncAccount(String soGiayTo, String hoVaTen, String ngaySinh, String tenDangNhap, String email, String matKhau) {
        if (dbTypeSQL()) {
            User user = UserSQLConllection.getInstance().getUserSync(soGiayTo, hoVaTen, ngaySinh);
            if (user == null || user.getId() <= 0) {
                return DataResponse.FAILED;
            }
            if (user.getEmail() == null || "".equals(user.getEmail())) {
                User userByEmail = UserSQLConllection.getInstance().getUserByField("email", email);
                if (userByEmail != null) {
                    return new DataResponse("1005", "Email đã tồn tại.");
                }

                user.setEmail(email);
            }
            User userByTenDangNhap = UserSQLConllection.getInstance().getUserByField("tenDangNhap", tenDangNhap);
            if (userByTenDangNhap != null) {
                return new DataResponse("1003", "Tên đăng nhập đã tồn tại.");
            }

            user.setTenDangNhap(tenDangNhap);
            user.setMatKhau(makePasswordHash(matKhau, Integer.toString(random.nextInt())));
            ObjectSQLCollections.getInstance().update(objectName, user);
            return new DataResponse(new Document("email", user.getEmail()));
        } else {
            return DataResponse.SUCCESSFUL;
        }
    }

    public DataResponse validateSession(String sessionKey) {
        if (dbTypeSQL()) {
            SessionUser session = UserSQLConllection.getInstance().getSessionUserBySessionKey(sessionKey);
            if (session == null || session.getId() <= 0) {
                System.out.println("session:" +"is null");
                return DataResponse.FAILED;
            }
            User user = UserSQLConllection.getInstance().getUserByField("id", session.getNguoiDungId());
            if (user == null) {
                System.out.println("user:" +"is null");
                return DataResponse.FAILED;
            }
            user.setMatKhau("");
            return new DataResponse(user);
        } else {
            SessionUser session = UserMongoDBConllection.getInstance().getSessionUserBySessionKey(sessionKey);
            if (session == null || session.getId() <= 0) {
                return DataResponse.FAILED;
            }
            User user = UserMongoDBConllection.getInstance().getUserByField("id", session.getNguoiDungId());
            if (user == null) {
                return DataResponse.FAILED;
            }
            user.setMatKhau("");
            return new DataResponse(user);
        }

    }

    public DataResponse activeAccount(String email) {
        if (dbTypeSQL()) {
            User user = UserSQLConllection.getInstance().getUserByField("email", email);
            if (user == null) {
                return DataResponse.FAILED;
            }
            user.setTrangThai(1);
            return ObjectSQLCollections.getInstance().update(objectName, user);
        } else {
            return DataResponse.SUCCESSFUL;
        }

    }

    private String makePasswordHash(String password, String salt) {
        try {
            String saltedAndHashed = password + "," + salt;
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(saltedAndHashed.getBytes());
            byte hashedBytes[] = (new String(digest.digest(), "UTF-8")).getBytes();
            return Base64.getEncoder().encodeToString(hashedBytes) + "," + salt;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 is not available", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 unavailable?  Not a chance", e);
        }
    }
}
