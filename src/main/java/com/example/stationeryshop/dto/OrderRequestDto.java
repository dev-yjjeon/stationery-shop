package com.example.stationeryshop.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequestDto {

    @NotBlank(message = "주문자 이름은 필수입니다.")
    private String customerName;

    @NotBlank(message = "연락처는 필수입니다.")
    private String customerPhone;

    @NotBlank(message = "배송지 주소는 필수입니다.")
    private String shippingAddress;
}
