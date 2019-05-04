package com.nebu.goldmine.web;

import lombok.Data;

import java.util.ArrayList;

@Data
public class PagedResponse {
    private ArrayList<PostVO> posts;
    private Long totalCount;
}
