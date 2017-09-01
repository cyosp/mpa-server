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
    int addCategoryWithKey(Category category);
    @Insert({"INSERT INTO PAYEE(key, name) VALUES( #{key}, #{name} )"})
    int addPayeeWithKey(Payee payee);

    @Options(useGeneratedKeys = true, keyProperty = "key")
    @Insert({"INSERT INTO PAYEE(name) VALUES(#{name})"})
    int addPayee(Payee payee);
    @Options(useGeneratedKeys = true, keyProperty = "key")
    @Insert({"INSERT INTO CATEGORY(name) VALUES(#{name})"})
    int addCategory(Category category);

    @Options(useGeneratedKeys = true, keyProperty = "key")
    @Insert({"INSERT INTO OPERATION(date, amount, account, paymode, flags, payee, wording, category) VALUES( #{date}, #{amount}, #{account}, #{paymode}, #{flags}, #{payee}, #{wording}, #{category} )"})
    int addOperation(Operation operation);

    @Insert("SET @BALANCE = (SELECT INITIAL FROM ACCOUNT WHERE KEY= #{accountId})")
    int setBalanceVariable(int accountId);

    List<Account> getAccounts();
    List<Operation> getOperations();
    List<Operation> getOperationsByAccount(int id);
    List<Category> getCategoriesByAccount(int id);
    List<Payee> getPayeesByAccount(int id);

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

    @Select("SELECT * FROM PAYEE WHERE NAME = #{name}")
    Payee getPayeeByName(String name);
    @Select("SELECT * FROM CATEGORY WHERE NAME = #{name}")
    Category getCategoryByName(String name);

    int addAccounts(List<Account> accounts);
    int addCategories(List<Category> categories);
    int addCurrencies(List<Currency> currencies);
    int addFavorites(List<Favorite> favorites);
    int addOperations(List<Operation> operations);
    int addPayees(List<Payee> payees);
    int addTags(List<Tag> tags);
}
