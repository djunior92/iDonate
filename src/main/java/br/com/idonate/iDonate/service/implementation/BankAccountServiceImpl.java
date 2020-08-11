package br.com.idonate.iDonate.service.implementation;


import br.com.idonate.iDonate.model.BankAccount;
import br.com.idonate.iDonate.model.Perfil;
import br.com.idonate.iDonate.repository.BankAccountRepository;
import br.com.idonate.iDonate.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    @Autowired
    BankAccountRepository bankAccountRepository;


    @Override
    public BankAccount save(BankAccount bankAccount){
        return bankAccountRepository.save(bankAccount);
    }


    @Override
    public BankAccount edit(Long id, BankAccount bankAccount) {
        Optional<BankAccount> optionalBankAccount = bankAccountRepository.findById(id);

        if (!optionalBankAccount.isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }
        BankAccount bankAccountSaved = optionalBankAccount.get();
        bankAccountSaved.setAccountType(bankAccount.getAccountType());
        bankAccountSaved.setBank(bankAccount.getBank());
        bankAccountSaved.setAgency(bankAccount.getAgency());
        bankAccountSaved.setDgAgency(bankAccount.getDgAgency());
        bankAccountSaved.setNumberAccount(bankAccount.getNumberAccount());
        bankAccountSaved.setDgAccount(bankAccount.getDgAccount());


        return bankAccountRepository.save(bankAccountSaved);
    }

    @Override
    public void delete(Long id){
        bankAccountRepository.delete(bankAccountRepository.findById(id).get());
    }


    @Override
    public Optional<BankAccount> searchById(Long id) {
        return bankAccountRepository.findById(id);
    }


    @Override
    public List<BankAccount> searchByPerfil(Perfil perfil) {
        return bankAccountRepository.findByPerfil(perfil);
    }

}
