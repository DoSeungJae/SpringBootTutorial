package com.example.first_project.controller;


import com.example.first_project.dto.ArticleForm;
import com.example.first_project.entity.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.first_project.repository.ArticleRepository;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j //로깅을 위한 어노테이션
public class ArticleController {

    @Autowired //스프링 부트가 미리 생성해놓은 객체를 가져다가 자동 연결!
    private ArticleRepository articleRepository;


    @GetMapping("/articles/new")
    public String newArticle(){
        return "articles/new";
    }

    @PostMapping("articles/create")
    public String createArticle(ArticleForm form){
        log.info(form.toString());
        //System.out.println(form.toString()); -> 로깅으로 대체

        //1. DTO로 변환 ! 엔티티
        Article article=form.toEntity();
        log.info(article.toString());
        //System.out.println(article.toString());
        //2. 리파지토리에게 엔티티를 디비안에 저장하게함
        Article saved=articleRepository.save(article);
        log.info(saved.toString());
        //System.out.println(saved.toString());


        return "redirect:/articles/"+saved.getId();

    }

    @GetMapping("articles/{id}")
    public String show(@PathVariable Long id , Model model){
        log.info("ID ="+id);
        //1 :id로 데이터를 가져옴. (Repository)
        Article articleEntity=articleRepository.findById(id).orElse(null);


        //2: 가져온 데이터를 모델에 등록
        model.addAttribute("article",articleEntity);


        //3: 보여줄 페이지를 설정
        return "articles/show";

    }
    @GetMapping("/articles")
    public String index(Model model){

        // 1 : 모든 article을 가져옴.
        List<Article> articleEntityList =(List<Article>)articleRepository.findAll(); //1.1
        //1.2 Iterable<Article> articleEntityList =(List<Article>)articleRepository.findAll();

        // 2 : 가져온 article 묶음을 뷰료 전달!
        model.addAttribute("articleList",articleEntityList);

        // 3 : 뷰 페이지를 설정
        return "articles/index"; //articles/index.mustache
    }

    @GetMapping("articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        // 1 : 수정할 데이터 (article) 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 2 : 모델에 데이터 등록
        model.addAttribute("article",articleEntity);


        //3 :뷰 페이지 설정

        return "articles/edit";

    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form){
        log.info(form.toString());

        //1: DTO를 엔티티로 변환
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());


        //2: 엔티티를 DB로 저장한다.

        //2-1: DB에 기존 데이터를 가져온다
        Article target=articleRepository.findById(articleEntity.getId()).orElse(null);

        //2-2: 기존 데이터에 값을 갱신
        if (target!=null) {
            articleRepository.save(articleEntity); //엔티티가 DB로 갱신
        }

        //3: 수정 결과를 페이지로 리다이렉트


        return "redirect:/articles/"+articleEntity.getId();
    }


    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("delete request received");

        //1: 삭제 대상 가져오기
        Article target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());


        //2 : 대상 삭제
        if(target != null){
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg","delete complited");
        }

        //3 : 결과 페이지로 리다이렉트
        return "redirect:/articles";
    }
}
