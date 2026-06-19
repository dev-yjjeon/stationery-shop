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
                    .name("Neon Gradient Masking Tape")
                    .price(4500)
                    .description("라임 그린과 퍼플 네온이 어우러진 감각적인 그라데이션 마스킹 테이프입니다. 다이어리 꾸미기에 포인트를 주기 좋습니다.")
                    .imageUrl("https://images.unsplash.com/photo-1595079676339-1534801ad6cf?w=500&auto=format&fit=crop&q=60")
                    .category(Category.STICKER)
                    .stock(150)
                    .build(),
                Product.builder()
                    .name("Creative Block Sketchbook")
                    .price(12000)
                    .description("120g 도톰한 미색 모조지로 드로잉, 스케치, 일러스트에 최적화된 A5 드로잉북입니다. 크리에이티브한 아이디어를 가득 채워보세요.")
                    .imageUrl("https://images.unsplash.com/photo-1544816155-12df9643f363?w=500&auto=format&fit=crop&q=60")
                    .category(Category.NOTE)
                    .stock(80)
                    .build(),
                Product.builder()
                    .name("Lofi Studio Poster A2")
                    .price(15000)
                    .description("나만의 방 벽면을 힙하고 감성적인 무드로 연출해 주는 A2 사이즈 Lofi 일러스트 포스터입니다.")
                    .imageUrl("https://images.unsplash.com/photo-1580136579312-94651dfd596d?w=500&auto=format&fit=crop&q=60")
                    .category(Category.POSTER)
                    .stock(50)
                    .build(),
                Product.builder()
                    .name("Midnight Muse Ballpoint Pen (0.5mm)")
                    .price(2500)
                    .description("자정의 영감을 담은 부드러운 필기감의 0.5mm 블랙 볼펜입니다. 미끄러지듯 매끄럽게 써지는 잉크 젤 타입입니다.")
                    .imageUrl("https://images.unsplash.com/photo-1583485088034-697b5bc54ccd?w=500&auto=format&fit=crop&q=60")
                    .category(Category.WRITING)
                    .stock(200)
                    .build(),
                Product.builder()
                    .name("Doodle Sticker Pack (30pcs)")
                    .price(6000)
                    .description("20대 일러스트레이터의 귀여운 손그림을 담은 리무버블 코팅 스티커 30개 세트입니다. 노트북이나 태블릿 캐리어에 붙이기 딱 좋습니다.")
                    .imageUrl("https://images.unsplash.com/photo-1572375995501-4b0894dbe0d7?w=500&auto=format&fit=crop&q=60")
                    .category(Category.STICKER)
                    .stock(300)
                    .build(),
                Product.builder()
                    .name("Chunky Acrylic Keychain")
                    .price(8500)
                    .description("귀엽고 도톰한 네온 옐로우 아크릴 키링으로 에어팟 케이스, 파우치, 가방에 키치한 포인트를 줍니다.")
                    .imageUrl("https://images.unsplash.com/photo-1584622650111-993a426fbf0a?w=500&auto=format&fit=crop&q=60")
                    .category(Category.LIFESTYLE)
                    .stock(100)
                    .build()
            ));
        }
    }
}
