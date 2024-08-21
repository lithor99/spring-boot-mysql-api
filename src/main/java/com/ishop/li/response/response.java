package com.ishop.li.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class response {
    private String timestamp;
    private String message;
    private boolean success;
    private Integer code;
    private Integer page;
    private Integer limit;
    private Integer count;
    private Object data;
}
