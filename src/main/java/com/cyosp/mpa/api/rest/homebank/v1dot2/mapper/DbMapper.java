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
    Integer addHomebank(HomeBank homeBank);
    @Insert({"INSERT INTO PROPERTIES(title, curr, autoSmode, autoWeekday) VALUES( #{title}, #{curr}, #{autoSmode}, #{autoWeekday} )"})
    Integer addProperties(Properties properties);
    @Insert({"INSERT INTO CURRENCY(key, iso, name, symb, syprf, dchar, gchar, frac, rate, mdate) VALUES( #{key}, #{iso}, #{name}, #{symb}, #{syprf}, #{dchar}, #{gchar}, #{frac}, #{rate}, #{mdate} )"})
    Integer addCurrency(Currency currency);
    @Insert({"INSERT INTO FAVORITE(key, amount, account, paymode, flags, payee, category, wording, nextdate, every, unit, limit) VALUES( #{key}, #{amount}, #{account}, #{paymode}, #{flags}, #{payee}, #{category}, #{wording}, #{nextdate}, #{every}, #{unit}, #{limit} )"})
    Integer addFavorite(Favorite favorite);
    @Insert({"INSERT INTO ACCOUNT(flags, pos, type, curr, name, initial, minimum, cheque1, cheque2) " +
            "VALUES( #{flags}, #{pos}, #{type}, #{curr}, #{name}, #{initial}, #{minimum}, #{cheque1}, #{cheque2} )"})
    @Options(useGeneratedKeys = true, keyProperty = "key")
    Integer addAccount(Account account);
    @Insert({"INSERT INTO CATEGORY(key, parent, flags, name) VALUES( #{key}, #{parent}, #{flags}, #{name} )"})
    Integer addCategoryWithKey(Category category);
    @Insert({"INSERT INTO PAYEE(key, name) VALUES( #{key}, #{name} )"})
    Integer addPayeeWithKey(Payee payee);

    @Options(useGeneratedKeys = true, keyProperty = "key")
    @Insert({"INSERT INTO PAYEE(name) VALUES(#{name})"})
    Integer addPayee(Payee payee);
    @Options(useGeneratedKeys = true, keyProperty = "key")
    @Insert({"INSERT INTO CATEGORY(name) VALUES(#{name})"})
    Integer addCategory(Category category);

    @Options(useGeneratedKeys = true, keyProperty = "key")
    @Insert({"INSERT INTO OPERATION(date, amount, account, paymode, flags, payee, wording, category) VALUES( #{date}, #{amount}, #{account}, #{paymode}, #{flags}, #{payee}, #{wording}, #{category} )"})
    Integer addOperation(Operation operation);

    @Insert("SET @BALANCE = (SELECT INITIAL FROM ACCOUNT WHERE KEY= #{accountId})")
    Integer setBalanceVariable(Integer accountId);

    List<Account> getAccounts();
    List<Operation> getOperations();
    List<Operation> getOperationsByAccount(Integer id);
    List<Category> getCategoriesByAccount(Integer id);
    List<Payee> getPayeesByAccount(Integer id);

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

    Integer addAccounts(List<Account> accounts);
    Integer addCategories(List<Category> categories);
    Integer addCurrencies(List<Currency> currencies);
    Integer addFavorites(List<Favorite> favorites);
    Integer addOperations(List<Operation> operations);
    Integer addPayees(List<Payee> payees);
    Integer addTags(List<Tag> tags);
}
