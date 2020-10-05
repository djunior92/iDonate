package br.com.idonate.iDonate.apicielo.payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerPayload {
    private String Name;

    public static CustomerPayload of(String name) {
        return CustomerPayload.builder().Name(name).build();
    }
}
