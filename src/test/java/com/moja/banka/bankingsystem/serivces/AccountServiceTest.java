package com.moja.banka.bankingsystem.serivces;

import com.moja.banka.bankingsystem.dto.CreateAccountDTO;
import com.moja.banka.bankingsystem.entities.AccountEntity;
import com.moja.banka.bankingsystem.entities.UserEntity;
import com.moja.banka.bankingsystem.enums.AccountType;
import com.moja.banka.bankingsystem.enums.CurrencyType;
import com.moja.banka.bankingsystem.repositories.AccountRepository;
import com.moja.banka.bankingsystem.services.AccountService;
import com.moja.banka.bankingsystem.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private AccountService accountService;

    private UserEntity testUser;
    private CreateAccountDTO createAccountDTO;

    @BeforeEach
    public void setUp() {
        testUser = new UserEntity();
        testUser.setId(1L);
        testUser.setOib("12345678901");
        testUser.setEmail("test@test.com");
        testUser.setFirstName("Test");
        testUser.setLastName("User");
        testUser.setUsername("testuser");

        createAccountDTO = new CreateAccountDTO();
        createAccountDTO.setOib("12345678901");
        createAccountDTO.setAccountType(AccountType.CHECKING);
        createAccountDTO.setCurrency(CurrencyType.EUR);
    }

    @Test
    void testCreateAccountForUser(){
        when(userService.findUserByOib(createAccountDTO.getOib())).thenReturn(testUser);
        when(accountRepository.save(any(AccountEntity.class))).thenReturn(new AccountEntity());

        accountService.createAccountForUser(createAccountDTO);

        verify(userService, times(1)).findUserByOib(createAccountDTO.getOib());
        verify(accountRepository, times(1)).save(any(AccountEntity.class));

        ArgumentCaptor<AccountEntity> accountCaptor = ArgumentCaptor.forClass(AccountEntity.class);
        verify(accountRepository).save(accountCaptor.capture());
        AccountEntity savedAccount = accountCaptor.getValue();

        assertEquals(createAccountDTO.getOib(), savedAccount.getUser().getOib());
        assertEquals(createAccountDTO.getAccountType(), savedAccount.getAccountType());
        assertEquals(createAccountDTO.getCurrency(), savedAccount.getCurrency());
    }

    // TODO: Dodati jos scearija za CreateAccountForUser kada je
    //  accountRepository.existsByUserEntityAndAccountType(user, accountType) je true i adaptirati postojeci
}
