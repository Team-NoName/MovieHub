package info.noname.moviehub.appData;

import com.orm.query.Condition;
import com.orm.query.Select;

import info.noname.moviehub.models.User;

/**
 * Created by AKamenov on 10/12/2016.
 */

public class UserData {

    public UserData(){
    }

    public User getUser(String username, String password) {
        User user = Select.from(User.class)
                .where(Condition.prop("_username").like(username),
                        Condition.prop("_password").like(password)).first();

        return user;
    }
}
