package br.com.idonate.iDonate.apicielo.service.implementation;

import br.com.idonate.iDonate.apicielo.config.ApiCieloProperty;
import br.com.idonate.iDonate.apicielo.payload.CreatingTokenizedCardRequest;
import br.com.idonate.iDonate.apicielo.payload.SimpleTransactionRequest;
import br.com.idonate.iDonate.apicielo.service.ApiCieloService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;

@Service
public class ApiCieloServiceImpl implements ApiCieloService {

    @Autowired
    private ApiCieloProperty property;

    @Override
    public String createCreditCard(CreatingTokenizedCardRequest creatingTokenizedCardRequest) throws NoSuchFieldException {
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headers = createRequest();
        HttpEntity<CreatingTokenizedCardRequest> entity = new HttpEntity<>(creatingTokenizedCardRequest, headers);

        ResponseEntity<Object> response = restTemplate.postForEntity(property.getCielo().getApiUrl() + "/1/card", entity, Object.class);
        String cardToken;

        try {
            cardToken = (response.getStatusCode().equals(HttpStatus.CREATED) && ObjectUtils.isNotEmpty(response.getBody()) ?
                    ((LinkedHashMap) response.getBody()).get("CardToken").toString() : StringUtils.EMPTY);
        } catch (Exception e) {
            return null;
        }

        return (StringUtils.isNotEmpty(cardToken) ?
                cardToken : null);
    }

    @Override
    public String paymentRequest(SimpleTransactionRequest simpleTransactionRequest) {
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headers = createRequest();
        HttpEntity<SimpleTransactionRequest> entity = new HttpEntity<>(simpleTransactionRequest, headers);

        ResponseEntity<Object> response = restTemplate.postForEntity(property.getCielo().getApiUrl() + "/1/sales", entity, Object.class);
        String cardToken;

        try {
            cardToken = (response.getStatusCode().equals(HttpStatus.CREATED) && ObjectUtils.isNotEmpty(response.getBody()) ?
                    ((LinkedHashMap) response.getBody()).get("CardToken").toString() : StringUtils.EMPTY);
        } catch (Exception e) {
            return null;
        }

        return (StringUtils.isNotEmpty(cardToken) ?
                cardToken : null);
    }

    private MultiValueMap<String, String> createRequest() {
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("MerchantId", property.getCielo().getMerchantId());
        headers.add("Content-Type", "application/json");
        headers.add("MerchantKey", property.getCielo().getMerchantKey());

        return headers;
    }

}
