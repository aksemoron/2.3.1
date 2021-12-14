package ru.project.util;

import ru.project.model.User;

import javax.persistence.TypedQuery;
import java.util.Optional;

public class HiberUtil {

    public static User getSingleResult(TypedQuery<User> query) {
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
