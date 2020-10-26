package br.com.idonate.iDonate.resource;

import br.com.idonate.iDonate.model.Quotation;
import br.com.idonate.iDonate.service.QuotationService;
import br.com.idonate.iDonate.service.exception.InvalidValueException;
import br.com.idonate.iDonate.service.exception.QuotationNotRegisteredException;
import br.com.idonate.iDonate.service.exception.RegisterNotFoundException;
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
    public ResponseEntity<Quotation> save(@Valid @RequestBody Quotation quotation) throws InvalidValueException, QuotationNotRegisteredException {
        Quotation savedQuotation = quotationService.save(quotation);
        return new ResponseEntity<>(savedQuotation, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Quotation> searchOpen() throws RegisterNotFoundException {
        Quotation quotation = quotationService.searchOpen();
        return new ResponseEntity<>(quotation, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quotation> searchById(@PathVariable Long id) {
        Optional<Quotation> quotation = quotationService.searchById(id);
        return (quotation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build()));
    }
}
