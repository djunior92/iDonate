package br.com.idonate.iDonate.resource;

import br.com.idonate.iDonate.model.Quotation;
import br.com.idonate.iDonate.service.QuotationService;
import br.com.idonate.iDonate.service.exception.InvalidValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/v1/quotation")
public class QuotationResource {

    @Autowired
    QuotationService quotationService;

    @PostMapping
    public ResponseEntity<Quotation> save(@Valid @RequestBody Quotation quotation) throws Exception, InvalidValueException{
        Quotation savedQuotation = quotationService.save(quotation);
        return new ResponseEntity<>(savedQuotation, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Quotation> searchOpen() {
        Optional<Quotation> quotation = quotationService.searchOpen();
        return (quotation.isPresent() ? ResponseEntity.ok(quotation.get()) : ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quotation> searchById(@PathVariable Long id) {
        Optional<Quotation> quotation = quotationService.searchById(id);
        return (quotation.isPresent() ? ResponseEntity.ok(quotation.get()) : ResponseEntity.notFound().build());
    }
}
