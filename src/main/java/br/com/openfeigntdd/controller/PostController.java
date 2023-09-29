package br.com.openfeigntdd.controller;

import br.com.openfeigntdd.client.PostClient;
import br.com.openfeigntdd.dto.PostDTO;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
