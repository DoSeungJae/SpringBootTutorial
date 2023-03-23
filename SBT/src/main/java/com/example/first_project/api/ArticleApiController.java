package com.example.first_project.api;

import com.example.first_project.dto.ArticleForm;
import com.example.first_project.entity.Article;
import com.example.first_project.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController //controller for RestAPI! return JSON data
public class ArticleApiController {

    @Autowired
    private ArticleRepository articleRepository;
    //GET
    @GetMapping("/api/articles")
    public List<Article> index(){
        return articleRepository.findAll();
    }

    @GetMapping("/api/articles/{id}")
    public Article index(@PathVariable Long id){
        return articleRepository.findById(id).orElse(null);
    }
    //POST
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm dto){
        Article article =dto.toEntity();
        return articleRepository.save(article);
    }


    //FETCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto){

        //1: 수정용 엔티티 생성
        Article article =dto.toEntity();
        log.info("id : {}, article : {}",id,article.toString());

        //2: 대상 엔티티 조회
        Article target = articleRepository.findById(id).orElse(null);

        //3: 잘못된 요청 처리(대상이 없거나, id가 다른 경우)
        if(target==null || id!=article.getId()){
            log.info("wrong request ! id : {}, article : {}",id,article.toString());
            //400, 잘못된 요청 응답
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);


        }

        //4: 정상적인 경우 업데이트 및 정답 응답(200)
        //target.patch(article);
        Article updated = articleRepository.save(article);
        return ResponseEntity.status(HttpStatus.OK).body(updated);




    }
    //DELETE
    @DeleteMapping("/api/articles/{id}")
        public ResponseEntity<Article> delete(@PathVariable Long id){
            Article target = articleRepository.findById(id).orElse(null);

            if(target==null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

            articleRepository.delete(target);
            return ResponseEntity.status(HttpStatus.OK).build();
        }

}
