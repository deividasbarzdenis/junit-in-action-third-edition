package org.example.account;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.junit5.JUnit5Mockery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import static org.junit.jupiter.api.Assertions.*;

class TestAccountServiceJMock {
    @RegisterExtension
    Mockery context = new JUnit5Mockery();

    private AccountManager mockAccountManager;

    @BeforeEach
    void setUp(){
        mockAccountManager = context.mock(AccountManager.class);
    }

    @Test
    void testTransferOk() {
        Account senderAccount = new Account( "1", 200 );
        Account beneficiaryAccount = new Account( "2", 100 );
        context.checking( new Expectations(){
            {
             oneOf(mockAccountManager).findAccountForUser("1");
             will(returnValue(senderAccount));
             oneOf(mockAccountManager).findAccountForUser("2");
             will(returnValue(beneficiaryAccount));

             oneOf(mockAccountManager).updateAccount(senderAccount);
             oneOf(mockAccountManager).updateAccount(beneficiaryAccount);
            }
        });
        AccountService accountService = new AccountService();
        accountService.setAccountManager(mockAccountManager);
        accountService.transfer("1", "2", 50);

        assertEquals(150, senderAccount.getBalance());
        assertEquals(150, beneficiaryAccount.getBalance());
    }
}
