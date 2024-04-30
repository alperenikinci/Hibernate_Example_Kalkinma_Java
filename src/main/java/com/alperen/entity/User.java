package com.alperen.entity;

import com.alperen.utility.enums.EGender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    //ÖNEMLİ!!!!!!!!!!!!
    /*
        OneToMany, ManyToOne ilişkilendirmelerinde Liste tutan tarafa ihtiyacımız yok ( @OneToMany kısmına )
        Çünkü; Liste tutmak çapraz tablo oluşmasına sebep olacaktır. Bu oluşacak olan çapraz tablo bizi sadece
        User entitysi tarafında biraz rahatlatır. Bu rahatlama user.getInterests() metodu aracılığıyla "DOĞRUDAN" bir user ile
        ilişkilendirilmiş Interest'leri getirme imkanı sağlar.

        ANCAK!!!!!!
        Bu kolaylığa erişmek için User ve Interest oluştururkenki oluşturma hiyerarşimizde dikkatli olmamız gerekir.
        Doğru sıralamayla veri oluşturmazsak patlarız. Bununla birlikte çapraz tablo oluşturmamız neticesinde DB'e ekstra yük bindiririz.

        Verileri oluştururken hata yapma olasılığımız yükselir, transient-persistent state durumlarını çok iyi yönetmemiz gerekir.

        BUNUN YERINE NE YAPABİLİRİZ?
        Interest ve User arasındaki ilişkilendirmede tekli ID tutacak entity'de(Interest) ilişkilendirileceği entity(User) Id'sini tutarız.
        Spesifik bir user'ın ilgi alanlarını ihtiyaç halinde getirmek için de basit bir join query'si yazarız.

        BU SAYEDE!!!!!!!
        Veri tabanında ekstra yükten kurtuluruz. Bir User'ın Interest'lerine SADECE ihtiyaç duyduğumuzda erişebileceğimiz bir yapı oluştururuz.
        DB'e veri eklerken persistent ve transient durumları yönetmesi daha kolay bir hal alır. Önce cascade hiyerarşisinde üstte kalan (User) entity
        oluşturulur. Sonra altta kalan(Interest) entity içerisine üstte kalan(User) entity'i yerleştiririz.
        Bunun bir sonucu olarak User özgün haliyle varolduktan sonra. Interest oluşturulduğunda içeriye User'ı verince interestte user_id tanımlanmış olur.
        Bu şekilde de bu iki tablo arasında Dolaylı çift yönlü bağlantı (indirect bidirectional relationship) tek bir entity üzerinden kurulur.
     */

//    @ElementCollection //İçerisinde Wrapper class saklayalan collection yapılarını DB'de ayrı bir tablo olarak tutmak için kullanılır.
//    @OneToMany // one user may have many interests... -> bir user'ın birden çok ilgi alanı olabilir.
//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @Builder.Default
//    private List<Interest> interests = new ArrayList<>();


}
