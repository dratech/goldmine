package com.nebu.goldmine.web;

import lombok.Data;

@Data
public class PostVO {
    private Long id;
    private String story;
    private String protagonist;
    private String context;
    private int upvoteCount;
    private Boolean voted;
    private byte[] data;
}
