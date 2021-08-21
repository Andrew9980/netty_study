package org.andrew.obj;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * @author siqing.xu
 * @version 1.0
 * @desc
 * @date 2021/8/10 19:07
 * @since 1.0
 */
@Data
@ToString
@Builder
public class User {

    private Integer id;

    private String name;

    private Integer age;

}
