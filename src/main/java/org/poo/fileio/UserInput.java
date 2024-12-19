package org.poo.fileio;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.poo.Account;

import java.util.List;

@Data
@NoArgsConstructor
public final class UserInput {
    private String firstName;
    private String lastName;
    private String email;
    private List<Account> accounts;

}
