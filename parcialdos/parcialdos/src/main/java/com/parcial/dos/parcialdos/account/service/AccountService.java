package com.parcial.dos.parcialdos.account.service;

import com.parcial.dos.parcialdos.account.dto.*;
import com.parcial.dos.parcialdos.account.entity.Account;
import com.parcial.dos.parcialdos.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService implements IAccountService{

    @Autowired
    private AccountRepository repository;



    @Override
    public AccountResponseDTO create(AccountRequestDTO request) {
        Account account = new Account();
        account.setAccountNumber(request.getNumeroCuenta());
        account.setOwnerName(request.getDueno());
        account.setBalance(request.getBalanceActual());
        account.setActive(true);

        Account saved = repository.save(account);
        return toResponseDTO(saved);
    }

    @Override
    public List<AccountResponseDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AccountResponseDTO getById(Long id) {
        return repository.findById(id)
                .map(this::toResponseDTO)
                .orElse(null);
    }

    @Override
    public String update(Long id, AccountRequestDTO request) {
        Optional<Account> opt = repository.findById(id);
        if (opt.isEmpty()) return "Cuenta no encontrada";

        Account account = opt.get();
        String mensaje = String.format(
                "La cuenta %s fue actualizada: balanceAnterior=%.2f, balanceActual=%.2f",
                account.getAccountNumber(),
                account.getBalance(),
                request.getBalanceActual()
        );
        account.setBalance(request.getBalanceActual());
        repository.save(account);
        return mensaje;
    }
    // En tu clase AccountService
    @Override
    public String updateByNumeroCuenta(String numeroCuenta, AccountRequestDTO request) {
        Optional<Account> opt = repository.findByAccountNumber(numeroCuenta);
        if (opt.isEmpty()) return "Cuenta no encontrada";

        Account account = opt.get();
        String mensaje = String.format(
                "La cuenta %s fue actualizada: balanceAnterior=%.2f, balanceActual=%.2f",
                account.getAccountNumber(),
                account.getBalance(),
                request.getBalanceActual()
        );
        account.setBalance(request.getBalanceActual());
        repository.save(account);
        return mensaje;
    }


    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public AccountOwnerBalanceDTO findByNumeroCuenta(String numeroCuenta) {
        return repository.findByAccountNumber(numeroCuenta)
                .map(acc -> new AccountOwnerBalanceDTO(acc.getOwnerName(), acc.getBalance()))
                .orElse(null);
    }

    private AccountResponseDTO toResponseDTO(Account a) {
        AccountResponseDTO dto = new AccountResponseDTO();
        dto.setId(a.getId());
        dto.setNumeroCuenta(a.getAccountNumber());
        dto.setDueno(a.getOwnerName());
        dto.setBalanceActual(a.getBalance());
        dto.setActive(a.getActive());
        return dto;
    }
    
}
