package br.com.openfeigntdd.client;

import br.com.openfeigntdd.dto.PostDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "post", url = "${api.post.url}")
public interface PostClient {

    @GetMapping(value = "/posts")
    List<PostDTO> getAllPosts();

    @GetMapping(value = "/posts/{id}")
    PostDTO getPostById(@RequestParam(value = "id") Integer id);

    @PutMapping(value = "/posts/{id}")
    PostDTO updateById(@RequestParam(value = "id") Integer id);

    @PostMapping(value = "/posts")
    PostDTO save(@RequestBody PostDTO postDTO);
}
