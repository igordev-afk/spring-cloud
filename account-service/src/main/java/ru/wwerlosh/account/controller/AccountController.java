package ru.wwerlosh.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.wwerlosh.account.controller.dto.AccountRequest;
import ru.wwerlosh.account.controller.dto.AccountResponse;
import ru.wwerlosh.account.entity.Account;
import ru.wwerlosh.account.service.AccountService;

@RestController
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{accountId}")
    public AccountResponse getAccount(@PathVariable Long accountId) {
        return new AccountResponse(accountService.getAccountById(accountId));
    }

    @PostMapping("/")
    public Long createAccount(@RequestBody AccountRequest accountRequest) {
        return accountService.createAccount(
                accountRequest.getName(),
                accountRequest.getEmail(),
                accountRequest.getPhone(),
                accountRequest.getBills());
    }

    @PutMapping("/{accountId}")
    public AccountResponse updateAccount(@PathVariable Long accountId,
                                         @RequestBody AccountRequest accountRequest) {
        return new AccountResponse(accountService.updateAccount(
                accountId,
                accountRequest.getName(),
                accountRequest.getEmail(),
                accountRequest.getPhone(),
                accountRequest.getBills()));
    }

    @DeleteMapping("/{accountId}")
    public AccountResponse deleteAccount(@PathVariable Long accountId) {
        return new AccountResponse(accountService.deleteAccount(accountId));
    }

}
