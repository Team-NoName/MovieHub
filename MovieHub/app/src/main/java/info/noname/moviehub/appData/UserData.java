package info.noname.moviehub.appData;

import com.orm.query.Condition;
import com.orm.query.Select;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import info.noname.moviehub.models.User;

/**
 * Created by AKamenov on 10/12/2016.
 */

public class UserData {

    public UserData(){
    }

    public User getUser(String username, String password) {
        password = md5(password);
        if (!Objects.equals(password, "")) {

            User user = Select.from(User.class)
                    .where(Condition.prop("_username").like(username),
                            Condition.prop("_password").like(password)).first();
            return user;
        }

        return null;
    }

    public User addUser(String username, String password, String email, String imageUri) {

        password = md5(password);
        if (!Objects.equals(password, "")) {
            User user = new User(username, password, email, imageUri);

            user.save();

            return user;
        }

        return null;
    }

    private String md5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i=0; i<messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

}

