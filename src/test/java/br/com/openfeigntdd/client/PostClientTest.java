package br.com.openfeigntdd.client;


import br.com.openfeigntdd.dto.PostDTO;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.mock.HttpMethod;
import feign.mock.MockClient;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PostClientTest {

    private static String BASE_URL = "https://jsonplaceholder.typicode.com";
    private static String RESPONSE_POST = "[{\n" +
            "        \"userId\": 1,\n" +
            "        \"id\": 1,\n" +
            "        \"title\": \"sunt aut facere repellat provident occaecati excepturi optio reprehenderit\",\n" +
            "        \"body\": \"quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto\"\n" +
            "    }]";
    private PostClient postClient;

    @Test
    public void whenGetPostClientThenReturnOk(){
        this.builderFeignClient(new MockClient().ok(
                HttpMethod.GET,
                BASE_URL.concat("/posts"),
                RESPONSE_POST));

        List<PostDTO> postDTOList = postClient.getAllPosts();
        assertFalse(postDTOList.isEmpty());
        assertThat(postDTOList.get(0).getUserId(), equalTo(1));
    }

    private void builderFeignClient(MockClient mockClient){
        postClient = Feign.builder()
                .client(mockClient)
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .contract(new SpringMvcContract())
                .target(PostClient.class, BASE_URL);
    }


    @Test
    public void testUpdateById() {
        PostDTO mockPostDTO = new PostDTO();
        when(postClient.updateById(anyInt())).thenReturn((mockPostDTO));

        PostDTO response = postClient.updateById(1);

        verify(postClient).updateById(1);

        assertEquals(mockPostDTO, response.getBody());
    }

    @Test
    public void testSave() {
        PostDTO requestPostDTO = new PostDTO();
        PostDTO mockPostDTO = new PostDTO();
        when(postClient.save(requestPostDTO)).thenReturn((mockPostDTO));

        PostDTO response = postClient.save(requestPostDTO);

        verify(postClient).save(requestPostDTO);

        assertEquals(mockPostDTO, response.getBody());
    }


}
