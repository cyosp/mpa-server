package com.cyosp.mpa.api.rest.homebank.v1dot2.model;

import com.cyosp.mpa.api.rest.common.exception.DuplicatedNameException;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by CYOSP on 2017-07-23.
 */
@Getter
@Setter
@XStreamAlias("homebank")
public class HomeBank {

    @XStreamAlias("v")
    @XStreamAsAttribute
    private String v;

    @XStreamAlias("d")
    @XStreamAsAttribute
    private String d;

    @XStreamAlias("properties")
    private Properties properties;

    @XStreamImplicit(itemFieldName = "cur")
    private List<Currency> currencies = new ArrayList<>();

    @XStreamImplicit(itemFieldName = "account")
    private List<Account> accounts = new ArrayList<>();

    @XStreamImplicit(itemFieldName = "pay")
    private List<Payee> payees = new ArrayList<>();

    @XStreamImplicit(itemFieldName = "cat")
    private List<Category> categories = new ArrayList<>();

    @XStreamImplicit(itemFieldName = "tag")
    private List<Tag> tags = new ArrayList<>();

    @XStreamImplicit(itemFieldName = "fav")
    private List<Favorite> favorites = new ArrayList<>();

    @XStreamImplicit(itemFieldName = "ope")
    private List<Operation> operations = new ArrayList<>();

    //---------------------------------------------------------

    @XStreamOmitField
    private int nextAccountKey = -1;

    @XStreamOmitField
    private Map<Integer, Account> accountMap;

    @XStreamOmitField
    private int nextCurrencyKey = -1;

    @XStreamOmitField
    private Map<Integer, Currency> currencyMap;

    @XStreamOmitField
    private int nextPayeeKey = -1;

    @XStreamOmitField
    private Map<Integer, Payee> payeeMap;

    @XStreamOmitField
    private int nextCategoryKey = -1;

    @XStreamOmitField
    private Map<Integer, Category> categoryMap;

    public void addMissingValues() {

        // Init
        accountMap = new HashMap<>();
        currencyMap = new HashMap<>();
        payeeMap = new HashMap<>();
        categoryMap = new HashMap<>();

        for (Currency currency : getCurrencies()) {
            // Add to map
            getCurrencyMap().put(currency.getKey(), currency);
        }

        for (Account account : getAccounts()) {
            // Init
            account.setBalance(account.getInitial());

            Options options = new Options();
            options.setOptions(account.getFlags());
            account.setOptions(options);

            Currency currency = getCurrencyMap().get(account.getCurr());
            account.setCurrency(currency);

            // Add to map
            getAccountMap().put(account.getKey(), account);
            // Update next key
            if (account.getKey() > nextAccountKey) setNextAccountKey(account.getKey());
        }

        for (Payee payee : getPayees()) {
            // Init
            payee.setBalance(new BigDecimal(0));
            // Add to map
            getPayeeMap().put(payee.getKey(), payee);
            // Update next key
            if (payee.getKey() > nextPayeeKey) setNextPayeeKey(payee.getKey());
        }

        for (Category category : getCategories()) {
            // Init
            category.setBalance(new BigDecimal(0));
            // Add to map
            getCategoryMap().put(category.getKey(), category);
            // Update next key
            if (category.getKey() > nextCategoryKey) setNextCategoryKey(category.getKey());
        }

        for (Operation operation : getOperations()) {

            // Init
            operation.convertJulianToDate();

            // Update account balance
            int accountRef = operation.getAccount();
            if (accountRef > 0) {
                Account account = getAccountMap().get(accountRef);
                account.setBalance(account.getBalance().add(operation.getAmount()));
            }
            // Update payee balance
            int payeeRef = operation.getPayee();
            if (payeeRef > 0) {
                Payee payee = getPayeeMap().get(payeeRef);
                payee.setBalance(payee.getBalance().add(operation.getAmount()));
            }
            // Update category balance
            int categoryRef = operation.getCategory();
            if (categoryRef > 0) {
                Category category = getCategoryMap().get(categoryRef);
                category.setBalance(category.getBalance().add(operation.getAmount()));
            }
        }

        // DEBUG
        /*for (Account account : getAccounts()) {
            System.out.println("Account: " + account.getName() + " <=> " + account.getBalance());
        }*/
        /*for (Payee payee : getPayees()) {
            System.out.println("Payee: " + payee.getName() + " <=> " + payee.getBalance());
        }*/
        /*for (Category category : getCategories()) {
            System.out.println("Category: " + category.getName() + " <=> " + category.getBalance());
        }*/
        /*for (Operation operation : getOperations()) {
            System.out.println("Operation: " + operation.getDate());
        }*/
    }

    public int getNextAccountKey() {
        nextAccountKey++;
        return nextAccountKey;
    }

    public void addAccount(Account account) throws DuplicatedNameException {
        for (Account accountFromList : getAccounts()) {
            if (accountFromList.getName().equals(account.getName())) throw new DuplicatedNameException();
        }

        account.setKey(getNextAccountKey());

        getAccounts().add(account);
    }
}
