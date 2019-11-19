package cn.printf.springbootboilerplate.rest.request;

import cn.printf.springbootboilerplate.annotation.Query;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserCriteria {

    @Query(type = Query.Type.INNER_LIKE)
    private String username;

    @Query
    private Boolean enabled;

    @Query
    private Long pid;

    @Query(type = Query.Type.GREATER_THAN, propName = "createAt")
    private Timestamp startTime;

    @Query(type = Query.Type.LESS_THAN, propName = "createAt")
    private Timestamp endTime;
}
