package org.andrew.convert;

import org.andrew.obj.Person;
import org.andrew.obj.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author siqing.xu
 * @version 1.0
 * @desc
 * @date 2021/8/10 19:07
 * @since 1.0
 */
@Mapper
public interface UserConvert {

    UserConvert CONVERT = Mappers.getMapper(UserConvert.class);

    Person convert2Person(User user);

}
