package com.example.first_project.dto;

import com.example.first_project.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ArticleForm {

    private Long id;

    private String title;
    private String content;

    //public ArticleForm(String title, String content) {
        //this.title = title;
        //this.content = content;
    //}

    //@Override
    //public String toString() {
        //return "ArticleForm{" +
                //"title='" + title + '\'' +
                //", content='" + content + '\'' +
                //'}';
    //}

    public Article toEntity() {

        return new Article(id,title,content);
    }
}
