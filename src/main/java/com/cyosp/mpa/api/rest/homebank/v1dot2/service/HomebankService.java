package com.cyosp.mpa.api.rest.homebank.v1dot2.service;

import com.cyosp.mpa.api.rest.common.exception.DataNotSavedException;
import com.cyosp.mpa.api.rest.common.exception.DuplicatedNameException;
import com.cyosp.mpa.api.rest.common.exception.LineNotDeletedException;
import com.cyosp.mpa.api.rest.common.exception.LineNotUpdatedException;
import com.cyosp.mpa.api.rest.homebank.v1dot2.model.*;
import com.cyosp.mpa.api.rest.homebank.v1dot2.repository.HomebankRepository;
import com.cyosp.mpa.api.rest.homebank.v1dot2.request.AccountRequest;
import com.cyosp.mpa.api.rest.homebank.v1dot2.request.OperationRequest;
import com.cyosp.mpa.api.rest.homebank.v1dot2.response.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CYOSP on 2017-06-27.
 */
@Service
public class HomebankService {

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

    private final HomebankRepository homebankRepository;

    public HomebankService(HomebankRepository homebankRepository) {
        this.homebankRepository = homebankRepository;
    }

    public void reload() {
        homebankRepository.load();
    }

    public InfosResponse getInfos() {

        InfosResponse ret = new InfosResponse();

        HomeBank homeBank = homebankRepository.getInfos();
        ret.setV(homeBank.getV());
        ret.setD(homeBank.getD());

        return ret;
    }

    public PropertiesResponse getProperties() {
        PropertiesResponse ret = new PropertiesResponse();

        Properties properties = homebankRepository.getProperties();
        BeanUtils.copyProperties(properties, ret);

        return ret;
    }

    //
    // Accounts
    //

    public AccountResponse addAccount(AccountRequest accountRequest) throws DataNotSavedException, DuplicatedNameException {
        Account account = new Account();
        account.setName(accountRequest.getName());

        homebankRepository.addAccount(account);

        AccountResponse accountResponse = new AccountResponse();
        BeanUtils.copyProperties(account, accountResponse);

        return accountResponse;
    }

    private String formatAmount(BigDecimal amount, Currency currency) {
        StringBuilder pattern = new StringBuilder("#,##0.");
        for (int i = 0; i < currency.getFrac(); i++)
            pattern.append("0");
        DecimalFormat df = new DecimalFormat(pattern.toString());
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator(currency.getGchar());
        symbols.setDecimalSeparator(currency.getDchar());
        df.setDecimalFormatSymbols(symbols);
        // TODO : Change how symbol is defined and placed
        // https://stackoverflow.com/questions/29215163/currency-symbol-with-another-number-format
        return df.format(amount) + " " + currency.getSymb();

    }

    public List<AccountResponse> getAccounts() {

        List<AccountResponse> ret = new ArrayList<>();

        for (Account account : homebankRepository.getAccounts()) {
            Options options = new Options();
            if (account.getFlags() != null) options.setOptions(account.getFlags());

            AccountResponse accountResponse = new AccountResponse();
            BeanUtils.copyProperties(account, accountResponse);
            OptionsResponse optionsResponse = new OptionsResponse();
            BeanUtils.copyProperties(options, optionsResponse);
            accountResponse.setOptions(optionsResponse);
            CurrencyResponse currencyResponse = new CurrencyResponse();
            BeanUtils.copyProperties(account.getCurrency(), currencyResponse);
            accountResponse.setCurrency(currencyResponse);
            accountResponse.setBalance(formatAmount(account.getBalance(), account.getCurrency()));

            ret.add(accountResponse);
        }

        return ret;
    }

    public List<OperationResponse> getOperationsByAccount(int id) {

        List<OperationResponse> ret = new ArrayList<>();

        for (Operation operation : homebankRepository.getOperationsByAccount(id)) {
            operation.convertJulianToDate();
            OperationResponse operationResponse = new OperationResponse();
            BeanUtils.copyProperties(operation, operationResponse);
            operationResponse.setDateFormatted(SIMPLE_DATE_FORMAT.format(operation.getJavaDate()));

            if (operation.getPaymode() != null) {
                PaymentMode paymentMode =
                        PaymentMode.getPaymentModes().stream().filter(pm -> pm.getCode() == operation.getPaymode()).findFirst().get();
                operationResponse.setPaymodeName(paymentMode.getName());
            } else operationResponse.setPaymodeName("");

            operationResponse.setAmount(formatAmount(operation.getAmount(), operation.getCurrency()));
            operationResponse.setBalance(formatAmount(operation.getBalance(), operation.getCurrency()));
            ret.add(operationResponse);
        }

        return ret;
    }

    public List<CategoryResponse> getCategoriesByAccount(int id) {

        List<CategoryResponse> ret = new ArrayList<>();

        for (Category category : homebankRepository.getCategoriesByAccount(id)) {
            CategoryResponse categoryResponse = new CategoryResponse();
            BeanUtils.copyProperties(category, categoryResponse);
            categoryResponse.setBalance(formatAmount(category.getBalance(), category.getCurrency()));
            ret.add(categoryResponse);
        }

        return ret;
    }

    public List<PayeeResponse> getPayeesByAccount(int id) {

        List<PayeeResponse> ret = new ArrayList<>();

        for (Payee payee : homebankRepository.getPayeesByAccount(id)) {
            PayeeResponse payeeResponse = new PayeeResponse();
            BeanUtils.copyProperties(payee, payeeResponse);
            payeeResponse.setBalance(formatAmount(payee.getBalance(), payee.getCurrency()));
            ret.add(payeeResponse);
        }

        return ret;
    }

    public OperationResponse addOperationByAccount(int id, OperationRequest operationRequest) throws DataNotSavedException {
        return homebankRepository.addOperationByAccount(id, operationRequest);
    }

    public AccountResponse getAccountById(long id) {
        return null;
    }

    public AccountResponse updateAccount(long id, AccountRequest accountRequest) throws LineNotUpdatedException, DuplicatedNameException {
        return null;
    }

    public AccountResponse deleteAccount(long id) throws LineNotDeletedException {
        return null;
    }

    //
    // Currencies
    //

    public List<CurrencyResponse> getCurrencies() {
        List<CurrencyResponse> ret = new ArrayList<>();

        for (Currency currency : homebankRepository.getCurrencies()) {
            CurrencyResponse currencyResponse = new CurrencyResponse();
            BeanUtils.copyProperties(currency, currencyResponse);
            ret.add(currencyResponse);
        }

        return ret;
    }

    //
    // Favorites
    //

    public List<FavoriteResponse> getFavorites() {
        List<FavoriteResponse> ret = new ArrayList<>();

        for (Favorite favorite : homebankRepository.getFavorites()) {
            FavoriteResponse favoriteResponse = new FavoriteResponse();
            BeanUtils.copyProperties(favorite, favoriteResponse);
            ret.add(favoriteResponse);
        }

        return ret;
    }

    //
    // Operations
    //

    public List<OperationResponse> getOperations() {
        List<OperationResponse> ret = new ArrayList<>();

        for (Operation operation : homebankRepository.getOperations()) {
            operation.convertJulianToDate();
            OperationResponse operationResponse = new OperationResponse();
            BeanUtils.copyProperties(operation, operationResponse);
            operationResponse.setDateFormatted(SIMPLE_DATE_FORMAT.format(operation.getJavaDate()));
            operationResponse.setAmount(formatAmount(operation.getAmount(), operation.getCurrency()));
            ret.add(operationResponse);
        }

        return ret;
    }

    //
    // Tags
    //

    public List<TagResponse> getTags() {
        List<TagResponse> ret = new ArrayList<>();

        for (Tag tag : homebankRepository.getTags()) {
            TagResponse tagResponse = new TagResponse();
            BeanUtils.copyProperties(tag, tagResponse);
            ret.add(tagResponse);
        }

        return ret;
    }
}
