package com.example.demo.account;

import com.example.demo.account.profile.Profile;
import com.example.demo.account.profile.ProfileRepository;
import com.example.demo.account.profile.ProfileRequest;
import com.example.demo.registration.token.ConfirmationToken;
import com.example.demo.registration.token.ConfirmationTokenService;
import com.example.demo.account.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AccountService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG =
            "User with email %s not found";
    private final AccountRepository accountRepository;
    private final ProfileRepository profileRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    public List<Account> grabAllAccounts(){
            return accountRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return accountRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUpAccount(Account account) {
        boolean userExists = accountRepository
                .findByEmail(account.getEmail())
                .isPresent();
      if (userExists) {
          throw new IllegalStateException("Email already taken");
      }
      String encodedPassword = bCryptPasswordEncoder.encode(account.getPassword());

      account.setPassword((encodedPassword));


      accountRepository.save(account);

      String  token = UUID.randomUUID().toString();
      // TODO: Send confirmation taken
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                account
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        //TODO: Send email
        return token;
    }

    public String profile(ProfileRequest request){
        addProfile(new Profile(
                request.getFirstName(),
                request.getLastName(),
                getCurrentAccount()
        ));

        return "Profile for " + request.getFirstName() + " " + request.getLastName() + " created on current account.";
    }
    public void addProfile(Profile profile){
        profileRepository.save(profile);
    }

    public int enableAccount(String email) {
        return accountRepository.enableAccount(email);
    }

    public Account getCurrentAccount(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Account account = accountRepository.findByEmail(userDetails.getUsername()).get();
        return account;
    }


}
