package com.nebu.goldmine.service;

import com.nebu.goldmine.data.PostEntity;
import com.nebu.goldmine.web.PostVO;

import java.util.ArrayList;
import java.util.List;

public class PostMapper {
    public static PostVO toVO(PostEntity entity, String ip) {
        PostVO postVO = new PostVO();
        postVO.setId(entity.getId());
        postVO.setStory(entity.getStory());
        postVO.setProtagonist(entity.getProtagonist());
        postVO.setContext(entity.getContext());
        postVO.setUpvoteCount(entity.getUpvoteCount());
        postVO.setVoted(entity.getUpvoterIPs().contains(ip));
        if(entity.getData()!=null)
        postVO.setData(entity.getData());
        return postVO;
    }

    public static PostEntity toEntity(PostVO postVO) {
        PostEntity postEntity = new PostEntity();
        postEntity.setId(postVO.getId());
        postEntity.setStory(postVO.getStory());
        postEntity.setProtagonist(postVO.getProtagonist());
        postEntity.setContext(postVO.getContext());
        postEntity.setUpvoteCount(postVO.getUpvoteCount());
        postEntity.setData(postVO.getData());
        return postEntity;
    }

    public static ArrayList<PostVO> toVO(List<PostEntity> postEntities, String ip) {
        ArrayList<PostVO> postVOs = new ArrayList<>();
        for (PostEntity postEntity : postEntities) {
            postVOs.add(toVO(postEntity, ip));
        }
        return postVOs;
    }

}
