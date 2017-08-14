package com.cyosp.mpa.api.rest.homebank.v1dot2.mapper;

import com.cyosp.mpa.api.rest.homebank.v1dot2.model.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by CYOSP on 2017-08-03.
 */
@UseHomebankDatasource
public interface DbMapper {

    void init();

    @Select("SELECT * FROM HOMEBANK LIMIT 1")
    HomeBank getHomebank();
    @Select("SELECT * FROM PROPERTIES LIMIT 1")
    Properties getProperties();

    @Insert({"INSERT INTO HOMEBANK(v, d) VALUES( #{v}, #{d} )"})
    int addHomebank(HomeBank homeBank);
    @Insert({"INSERT INTO PROPERTIES(title, curr, autoSmode, autoWeekday) VALUES( #{title}, #{curr}, #{autoSmode}, #{autoWeekday} )"})
    int addProperties(Properties properties);
    @Insert({"INSERT INTO CURRENCY(key, iso, name, symb, syprf, dchar, gchar, frac, rate, mdate) VALUES( #{key}, #{iso}, #{name}, #{symb}, #{syprf}, #{dchar}, #{gchar}, #{frac}, #{rate}, #{mdate} )"})
    int addCurrency(Currency currency);
    @Insert({"INSERT INTO FAVORITE(key, amount, account, paymode, flags, payee, category, wording, nextdate, every, unit, limit) VALUES( #{key}, #{amount}, #{account}, #{paymode}, #{flags}, #{payee}, #{category}, #{wording}, #{nextdate}, #{every}, #{unit}, #{limit} )"})
    int addFavorite(Favorite favorite);
    @Insert({"INSERT INTO ACCOUNT(flags, pos, type, curr, name, initial, minimum, cheque1, cheque2) " +
            "VALUES( #{flags}, #{pos}, #{type}, #{curr}, #{name}, #{initial}, #{minimum}, #{cheque1}, #{cheque2} )"})
    @Options(useGeneratedKeys = true, keyProperty = "key")
    int addAccount(Account account);
    @Insert({"INSERT INTO CATEGORY(key, parent, flags, name) VALUES( #{key}, #{parent}, #{flags}, #{name} )"})
    int addCategory(Category category);
    @Insert({"INSERT INTO PAYEE(key, name) VALUES( #{key}, #{name} )"})
    int addPayee(Payee payee);

   @Insert("SET @BALANCE = (SELECT INITIAL FROM ACCOUNT WHERE KEY= #{accountId})")
   int setBalanceVariable(int accountId);

    List<Account> getAccounts();
    List<Operation> getOperations();
    List<Operation> getOperationsByAccount(int id);

    @Select("SELECT * FROM CATEGORY")
    List<Category> getCategories();
    @Select("SELECT * FROM CURRENCY")
    List<Currency> getCurrencies();
    @Select("SELECT * FROM PAYEE")
    List<Payee> getPayees();
    @Select("SELECT * FROM TAG")
    List<Tag> getTags();
    @Select("SELECT * FROM FAVORITE")
    List<Favorite> getFavorites();

    int addAccounts(List<Account> accounts);
    int addCategories(List<Category> categories);
    int addCurrencies(List<Currency> currencies);
    int addFavorites(List<Favorite> favorites);
    int addOperations(List<Operation> operations);
    int addPayees(List<Payee> payees);
    int addTags(List<Tag> tags);
}
