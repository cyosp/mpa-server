package com.cyosp.mpa.api.rest.homebank.v1dot2.service;

import com.cyosp.mpa.api.rest.common.exception.DataNotSavedException;
import com.cyosp.mpa.api.rest.common.exception.DuplicatedNameException;
import com.cyosp.mpa.api.rest.common.exception.LineNotDeletedException;
import com.cyosp.mpa.api.rest.common.exception.LineNotUpdatedException;
import com.cyosp.mpa.api.rest.homebank.v1dot2.mapper.XmlMapper;
import com.cyosp.mpa.api.rest.homebank.v1dot2.model.*;
import com.cyosp.mpa.api.rest.homebank.v1dot2.request.AccountRequest;
import com.cyosp.mpa.api.rest.homebank.v1dot2.response.*;
import lombok.Getter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
@Getter
public class HomebankService {

    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private XmlMapper xmlMapper;

    public void reload() {
        getXmlMapper().loadXmlFile();
    }

    public InfosResponse getInfos() {

        InfosResponse ret = new InfosResponse();

        HomeBank homeBank = getXmlMapper().getInfos();
        ret.setV(homeBank.getV());
        ret.setD(homeBank.getD());

        return ret;
    }

    public PropertiesResponse getProperties() {
        PropertiesResponse ret = new PropertiesResponse();

        Properties properties = getXmlMapper().getProperties();
        BeanUtils.copyProperties(properties, ret);

        return ret;
    }

    //
    // Accounts
    //

    public AccountResponse addAccount(AccountRequest accountRequest) throws DataNotSavedException, DuplicatedNameException {
        Account account = new Account();
        account.setName(accountRequest.getName());

        getXmlMapper().addAccount(account);

        AccountResponse accountResponse = new AccountResponse();
        BeanUtils.copyProperties(account, accountResponse);

        return accountResponse;
    }

    public String formatAmount(BigDecimal amount, Currency currency)
    {
        String pattern = "#,##0.";
        for (int i = 0; i < currency.getFrac(); i++)
            pattern += "0";
        DecimalFormat df = new DecimalFormat(pattern);
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

        for (Account account : getXmlMapper().getAccounts()) {
            Options options = new Options();
            options.setOptions(account.getFlags());

            AccountResponse accountResponse = new AccountResponse();
            BeanUtils.copyProperties(account, accountResponse);
            OptionsResponse optionsResponse = new OptionsResponse();
            BeanUtils.copyProperties(options, optionsResponse);
            accountResponse.setOptions(optionsResponse);
            CurrencyResponse currencyResponse = new CurrencyResponse();
            BeanUtils.copyProperties(account.getCurrency(), currencyResponse);
            accountResponse.setCurrency(currencyResponse);
            accountResponse.setBalance(formatAmount(account.getBalance(),account.getCurrency()));

            ret.add(accountResponse);
        }

        return ret;
    }

    public List<OperationResponse> getOperationsByAccount(int id) {

        List<OperationResponse> ret = new ArrayList<>();

        for (Operation operation : getXmlMapper().getOperationsByAccount(id)) {
            operation.convertJulianToDate();
            OperationResponse operationResponse = new OperationResponse();
            BeanUtils.copyProperties(operation, operationResponse);
            operationResponse.setDateFormatted(SIMPLE_DATE_FORMAT.format(operation.getJavaDate()));
            operationResponse.setAmount(formatAmount(operation.getAmount(),operation.getCurrency()));
            operationResponse.setBalance(formatAmount(operation.getBalance(),operation.getCurrency()));
            ret.add(operationResponse);
        }

        return ret;
    }

    public List<CategoryResponse> getCategoriesByAccount(int id) {

        List<CategoryResponse> ret = new ArrayList<>();

        for (Category category : getXmlMapper().getCategoriesByAccount(id)) {
            CategoryResponse categoryResponse = new CategoryResponse();
            BeanUtils.copyProperties(category, categoryResponse);
            categoryResponse.setBalance(formatAmount(category.getBalance(),category.getCurrency()));
            ret.add(categoryResponse);
        }

        return ret;
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
    // Categories
    //

    public List<CategoryResponse> getCategories() {
        List<CategoryResponse> ret = new ArrayList<>();

        for (Category category : getXmlMapper().getCategories()) {
            CategoryResponse categoryResponse = new CategoryResponse();
            BeanUtils.copyProperties(category, categoryResponse);
            ret.add(categoryResponse);
        }

        return ret;
    }

    //
    // Currencies
    //

    public List<CurrencyResponse> getCurrencies() {
        List<CurrencyResponse> ret = new ArrayList<>();

        for (Currency currency : getXmlMapper().getCurrencies()) {
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

        for (Favorite favorite : getXmlMapper().getFavorites()) {
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

        for (Operation operation : getXmlMapper().getOperations()) {
            operation.convertJulianToDate();
            OperationResponse operationResponse = new OperationResponse();
            BeanUtils.copyProperties(operation, operationResponse);
            operationResponse.setDateFormatted(SIMPLE_DATE_FORMAT.format(operation.getJavaDate()));
            operationResponse.setAmount(formatAmount(operation.getAmount(),operation.getCurrency()));
            ret.add(operationResponse);
        }

        return ret;
    }

    //
    // Payees
    //

    public List<PayeeResponse> getPayees() {
        List<PayeeResponse> ret = new ArrayList<>();

        for (Payee payee : getXmlMapper().getPayees()) {
            PayeeResponse payeeResponse = new PayeeResponse();
            BeanUtils.copyProperties(payee, payeeResponse);
            ret.add(payeeResponse);
        }

        return ret;
    }

    //
    // Tags
    //

    public List<TagResponse> getTags() {
        List<TagResponse> ret = new ArrayList<>();

        for (Tag tag : getXmlMapper().getTags()) {
            TagResponse tagResponse = new TagResponse();
            BeanUtils.copyProperties(tag, tagResponse);
            ret.add(tagResponse);
        }

        return ret;
    }
}
