package com.nebu.goldmine.data;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;

@Data
@Entity
public class PostEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition="NVARCHAR(MAX)")
    private String story;
    private String protagonist;
    private String context;
    private int upvoteCount;
    private ArrayList<String> upvoterIPs;
    @Lob
    private byte[] data;
}
