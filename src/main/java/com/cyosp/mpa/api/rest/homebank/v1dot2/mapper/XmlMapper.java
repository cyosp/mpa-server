package com.cyosp.mpa.api.rest.homebank.v1dot2.mapper;

import com.cyosp.mpa.api.rest.common.exception.DataNotSavedException;
import com.cyosp.mpa.api.rest.common.exception.DuplicatedNameException;
import com.cyosp.mpa.api.rest.homebank.v1dot2.model.Account;
import com.cyosp.mpa.api.rest.homebank.v1dot2.model.HomeBank;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.mapper.MapperWrapper;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 * Created by CYOSP on 2017-07-14.
 */
@Configuration
@Getter
public class XmlMapper {

    @Value("${homebank.repository.v1dot2.file}")
    private File homebankFilePath;

    @Getter
    private static HomeBank homeBank = null;

    @Getter
    private static XStream xstream;

    static {
        // Allow to:
        // * ignore XML elements/attributes not mapped
        // * manage attributes with _ in it's name
        xstream = new XStream(new StaxDriver(new NoNameCoder())) {
            @Override
            protected MapperWrapper wrapMapper(MapperWrapper next) {
                return new MapperWrapper(next) {
                    @Override
                    public boolean shouldSerializeMember(Class definedIn,
                                                         String fieldName) {
                        if (definedIn == Object.class) {
                            return false;
                        }
                        return super.shouldSerializeMember(definedIn, fieldName);
                    }
                };
            }
        };
        // XStream must be using different class loader
        // Solution: set same class loader than current thread
        // It allows to recognize object in loading
        xstream.setClassLoader(Thread.currentThread().getContextClassLoader());

        xstream.processAnnotations(HomeBank.class);

    }

    @PostConstruct
    public void loadXmlFile() {
        homeBank = (HomeBank) getXstream().fromXML(getHomebankFilePath());
        getHomeBank().initKeys();

        // FOR DEBUG
        String xmlContent = getXstream().toXML(getHomeBank());
        xmlContent = xmlContent.replaceAll("></(properties|cur|account|pay|cat)>", "/>");
        String xmlContentIndent = xmlContent.replaceAll("><", ">\n<");
        System.out.println(xmlContentIndent);
    }

    public void saveXmlFile() throws DataNotSavedException {

        try {
            FileOutputStream outputStream = new FileOutputStream(getHomebankFilePath());
            getXstream().toXML(getHomeBank(), outputStream);
        } catch (Exception e1) {
            throw new DataNotSavedException();
        }

    }

    public List<Account> getAccounts() {
        return getHomeBank().getAccounts();
    }

    public int addAccount(Account account) throws DuplicatedNameException, DataNotSavedException {
        int ret = 0;

        getHomeBank().addAccount(account);
        saveXmlFile();

        ret++;

        return ret;
    }
}
