package com.example.first_project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Entity //DB가 해당 객체를 인식 가능 (해당 클래스로 테이블을 만듦)
@NoArgsConstructor //디폴트 생성자 추가해주는 어노테이션
@Getter
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //db가 자동으로 id를 생성하는 어노테이션
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    public void patch(Article article) {
        if (article.title!=null){
            this.title=article.title;
        }
        if(article.content!=null){
            this.content=article.content;

        }
    }


    //public Article(Long id, String title, String content) {
        //this.id = id;
        //this.title = title;
        //this.content = content;
    //}

    //@Override
    //public String toString() {
        //return "Article{" +
                //"id=" + id +
                //", title='" + title + '\'' +
                //", content='" + content + '\'' +
                //'}';
    //}
}
