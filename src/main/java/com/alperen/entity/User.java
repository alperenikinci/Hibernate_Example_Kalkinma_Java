package com.alperen.entity;

import com.alperen.utility.enums.EGender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tbl_user") //Zorunlu olmamakla birlikte, best practice her tabloya tbl_ şablonunu kullanarak isim vermekte fayda var.
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Name name;
    /*
    unique = true -> özgün değer, Tabloda bu değerden sadece 1 tane olabilir.
    nullable = false -> Bu alan boş bırakılamaz. Oluşturulma ve
    DB'e gönderilmeden önce mutl6"789aka ama mutlaka içerisine bir değer yerleştirilmeli.
     */
    @Column(unique = true,nullable = false)
    private String username;

    @Column(nullable = false,length = 32)
    private String password;

    /*
    @Enumerated anotasyonu enum'ların DB tablosunda nasıl görüntüleneceğini belirlemek için kullanılır.
    2 farklı parametre alabilir;
        1- EnumType.ORDINAL -> Enum'ın ordinal değerini(indexini) baz alarak kayıt işlemi gerçekleştirir. (0,1,2,3,4,5...)
        2- EnumType.String -> Enum'ın String değerini baz alarak kayıt işlemi gerçekleştirir. (MALE, FEMALE)
     Eğer @Enumerated anotasyonu ile işaretleme yapmazsak, default değer olarak ORDINAL'i alır.
     */
    @Enumerated(EnumType.STRING)
    private EGender gender;
//
//    @ElementCollection //İçerisinde Wrapper class saklayalan collection yapılarını DB'de ayrı bir tablo olarak tutmak için kullanılır.
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Builder.Default
    private List<Interest> interests = new ArrayList<>();


}
