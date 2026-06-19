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
                    .name("산호의 Y2K 소녀 스티커 팩")
                    .price(4500)
                    .description("Y2K Retro 감성을 가득 담아 일러스트레이터 산호가 그린 30가지 조각 스티커 팩입니다. 노트북과 다이어리에 붙이기 좋아요.")
                    .imageUrl("https://images.unsplash.com/photo-1595079676339-1534801ad6cf?w=500&auto=format&fit=crop&q=60")
                    .category(Category.STICKER)
                    .stock(150)
                    .build(),
                Product.builder()
                    .name("비 오는 날의 세일러 소녀 포스터 (A3)")
                    .price(12000)
                    .description("비 내리는 날의 몽환적이고 차분한 분위기를 세일러복 소녀 일러스트로 표현한 A3 포스터입니다.")
                    .imageUrl("https://images.unsplash.com/photo-1579783902614-a3fb3927b6a5?w=500&auto=format&fit=crop&q=60")
                    .category(Category.POSTER)
                    .stock(50)
                    .build(),
                Product.builder()
                    .name("초록 네잎클로버 엽서 세트")
                    .price(3500)
                    .description("행운을 가져다줄 네잎클로버와 귀여운 삼색 고양이 일러스트가 그려진 엽서 3종 세트입니다.")
                    .imageUrl("https://images.unsplash.com/photo-1572375995501-4b0894dbe0d7?w=500&auto=format&fit=crop&q=60")
                    .category(Category.STICKER)
                    .stock(300)
                    .build(),
                Product.builder()
                    .name("코타츠와 귤 떡메모지")
                    .price(3800)
                    .description("추운 겨울 코타츠 안에서 따뜻하게 귤을 나누어 먹는 포근한 드로잉 떡메모지 100매입니다.")
                    .imageUrl("https://images.unsplash.com/photo-1559251606-c623743a6d76?w=500&auto=format&fit=crop&q=60")
                    .category(Category.NOTE)
                    .stock(100)
                    .build(),
                Product.builder()
                    .name("가을 도토리 소녀 드로잉 북")
                    .price(10500)
                    .description("깊어가는 가을의 무드를 머금은 스케치북입니다. 120g 모조지로 연필 and 색연필 드로잉에 최적화되어 있습니다.")
                    .imageUrl("https://images.unsplash.com/photo-1544816155-12df9643f363?w=500&auto=format&fit=crop&q=60")
                    .category(Category.NOTE)
                    .stock(80)
                    .build(),
                Product.builder()
                    .name("매운 라면 소녀 아크릴 키링")
                    .price(8500)
                    .description("키치한 매운 라면 봉지와 요리사 소녀가 함께 달린 도톰하고 귀여운 아크릴 키링입니다. 에어팟이나 파우치에 매달기 좋습니다.")
                    .imageUrl("https://images.unsplash.com/photo-1584622650111-993a426fbf0a?w=500&auto=format&fit=crop&q=60")
                    .category(Category.LIFESTYLE)
                    .stock(100)
                    .build(),
                Product.builder()
                    .name("마이크를 든 소녀 마스킹 테이프")
                    .price(4200)
                    .description("노래 부르는 힙한 소녀 일러스트가 연속 패턴으로 그려진 감각적인 마스킹 테이프입니다.")
                    .imageUrl("https://images.unsplash.com/photo-1583485088034-697b5bc54ccd?w=500&auto=format&fit=crop&q=60")
                    .category(Category.STICKER)
                    .stock(200)
                    .build()
            ));
        }
    }
}
