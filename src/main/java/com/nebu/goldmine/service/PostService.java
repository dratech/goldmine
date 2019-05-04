package com.nebu.goldmine.service;

import com.nebu.goldmine.data.PostEntity;
import com.nebu.goldmine.data.PostRepository;
import com.nebu.goldmine.web.PagedResponse;
import com.nebu.goldmine.web.PostVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public PagedResponse newPosts(int page, int pageSize, String ip){
        ArrayList<PostVO> posts = PostMapper.toVO(postRepository.newPosts(PageRequest.of(page, pageSize)), ip);
        return  this.assembleResponse(posts, postRepository.count());
    }

    public PagedResponse newSearch(int page, int pageSize, String search, String ip){
        ArrayList<PostVO> posts = PostMapper.toVO(postRepository.newSearch(search, PageRequest.of(page, pageSize)), ip);
        return this.assembleResponse(posts, postRepository.searchCount(search));
    }

    public PagedResponse bestPosts(int page, int pageSize, String ip){
        ArrayList<PostVO> posts = PostMapper.toVO(postRepository.bestPosts(PageRequest.of(page, pageSize)), ip);
        return this.assembleResponse(posts, postRepository.count());
    }

    public PagedResponse bestSearch(int page, int pageSize, String search, String ip){
        ArrayList<PostVO> posts = PostMapper.toVO(postRepository.newSearch(search, PageRequest.of(page, pageSize)), ip);
        return this.assembleResponse(posts, postRepository.searchCount(search));
    }

    public int vote(Long id , String ip){
        PostEntity postEntity = this.changeUpvoteCount(id, ip);
        return postEntity.getUpvoteCount();
    }

    public PostVO post(String story, String protagonist, String context, MultipartFile data, String ip) throws IOException {
        PostEntity postEntity = new PostEntity();
        postEntity.setUpvoteCount(0);
        postEntity.setUpvoterIPs(new ArrayList<>());
        postEntity.setStory(story);
        postEntity.setProtagonist(protagonist);
        postEntity.setContext(context);
        if(data!=null) {
            postEntity.setData(data.getBytes());
        }
        return PostMapper.toVO(postRepository.save(postEntity), ip);
    }





    private PostEntity changeUpvoteCount(Long id, String ip){
        PostEntity postEntity = postRepository.findById(id).get();
        if (postEntity.getUpvoterIPs().contains(ip)){
            postEntity.setUpvoteCount(postEntity.getUpvoteCount()-1);
            postEntity.getUpvoterIPs().remove(ip);
        }else {
            postEntity.setUpvoteCount(postEntity.getUpvoteCount()+1);
            postEntity.getUpvoterIPs().add(ip);
        }
        postRepository.save(postEntity);
        return postEntity;
    }

    private PagedResponse assembleResponse(ArrayList<PostVO> posts, Long totalCount){
        PagedResponse pagedResponse = new PagedResponse();
        pagedResponse.setPosts(posts);
        pagedResponse.setTotalCount(totalCount);
        return pagedResponse;
    }

}
