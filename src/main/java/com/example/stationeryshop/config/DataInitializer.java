package com.example.stationeryshop.config;

import com.example.stationeryshop.entity.Category;
import com.example.stationeryshop.entity.Product;
import com.example.stationeryshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        if (productRepository.count() == 0) {
            productRepository.saveAll(Arrays.asList(
                Product.builder()
                    .name("Fluffy Cat Jelly Sticker Pack")
                    .price(3800)
                    .description("말랑말랑 고양이 발바닥 젤리 스티커 팩. 다이어리에 붙이기 딱 좋은 귀여운 스티커입니다.")
                    .imageUrl("https://images.unsplash.com/photo-1514888286974-6c03e2ca1dba?w=500&auto=format&fit=crop&q=60")
                    .category(Category.STICKER)
                    .stock(150)
                    .build(),
                Product.builder()
                    .name("Lazy Teddy Bear Sketchbook")
                    .price(10500)
                    .description("포근한 곰돌이 드로잉 노트 A5. 도톰한 모조지로 귀여운 곰돌이와 함께 그림을 그려보세요.")
                    .imageUrl("https://images.unsplash.com/photo-1582063283689-a6a3722bef90?w=500&auto=format&fit=crop&q=60")
                    .category(Category.NOTE)
                    .stock(80)
                    .build(),
                Product.builder()
                    .name("Sweet Dessert Masking Tape")
                    .price(4200)
                    .description("달콤 바삭 디저트 일러스트 마스킹 테이프. 달콤한 디저트가 가득 그려진 사랑스러운 마테입니다.")
                    .imageUrl("https://images.unsplash.com/photo-1587314168485-3236d6710814?w=500&auto=format&fit=crop&q=60")
                    .category(Category.STICKER)
                    .stock(300)
                    .build(),
                Product.builder()
                    .name("Happy Bunny Soft Keyring")
                    .price(9800)
                    .description("토끼 솜뭉치 키링. 보들보들한 솜뭉치 토끼 인형 키링으로 가방에 귀여움을 더해보세요.")
                    .imageUrl("https://images.unsplash.com/photo-1583511655857-d19b40a7a54e?w=500&auto=format&fit=crop&q=60")
                    .category(Category.LIFESTYLE)
                    .stock(100)
                    .build(),
                Product.builder()
                    .name("Dreamy Rainbow Gel Pen")
                    .price(1800)
                    .description("몽글몽글 무지개 그라데이션 젤펜. 부드러운 필기감과 예쁜 그라데이션 컬러를 자랑합니다.")
                    .imageUrl("https://images.unsplash.com/photo-1513542789411-b6a5d4f31634?w=500&auto=format&fit=crop&q=60")
                    .category(Category.WRITING)
                    .stock(200)
                    .build(),
                Product.builder()
                    .name("Cozy Room Illustration Poster")
                    .price(12000)
                    .description("아기자기한 일러스트레이터의 방 A3 포스터. 방 안을 따스하고 귀엽게 꾸며줄 감성 일러스트 포스터입니다.")
                    .imageUrl("https://images.unsplash.com/photo-1500462908006-c224a4d9c7c2?w=500&auto=format&fit=crop&q=60")
                    .category(Category.POSTER)
                    .stock(50)
                    .build(),
                Product.builder()
                    .name("Clumsy Puppy Memo Pad")
                    .price(3500)
                    .description("어설픈 댕댕이 떡메모지 100매. 삐뚤빼뚤 낙서 같은 댕댕이 일러스트가 매력적인 메모패드입니다.")
                    .imageUrl("https://images.unsplash.com/photo-1543466835-00a7907e9de1?w=500&auto=format&fit=crop&q=60")
                    .category(Category.NOTE)
                    .stock(120)
                    .build()
            ));
        }
    }
}
