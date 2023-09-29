package br.com.openfeigntdd.controller;

import br.com.openfeigntdd.client.PostClient;
import br.com.openfeigntdd.dto.PostDTO;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("posts")
public class PostController {

    private PostClient postClient;

    @GetMapping
    public List<PostDTO> getAllPosts(){
        return postClient.getAllPosts();
    }

    @GetMapping(value = "/id/{id}")
    public PostDTO getAllPosts(@PathVariable Integer id) {
        return postClient.getPostById(id);
    }

    @PutMapping(value = "/posts/{id}")
    public PostDTO updateById(@RequestParam(value = "id") Integer id){
        return postClient.updateById(id);
    }

    @PostMapping(value = "/posts")
    public PostDTO updateById(@RequestBody PostDTO postDTO){
        return postClient.save(postDTO);
    }

}
