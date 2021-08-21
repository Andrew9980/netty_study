package org.andrew;

import org.andrew.convert.UserConvert;
import org.andrew.obj.Person;
import org.andrew.obj.User;

/**
 * @author siqing.xu
 * @version 1.0
 * @desc
 * @date 2021/8/8 20:24
 * @since 1.0
 */
public class Test {

    public static void main(String[] args) {
        User user = User.builder().age(10).name("Andrew").id(1).build();
        Person person = UserConvert.CONVERT.convert2Person(user);
        System.out.println(person);
    }

}
