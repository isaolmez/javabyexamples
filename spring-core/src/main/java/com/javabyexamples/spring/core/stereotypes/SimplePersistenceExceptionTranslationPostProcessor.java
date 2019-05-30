package com.javabyexamples.spring.core.stereotypes;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class SimplePersistenceExceptionTranslationPostProcessor implements PersistenceExceptionTranslator {

    @Nullable
    @Override
    public DataAccessException translateExceptionIfPossible(RuntimeException ex) {
        return new TimeAwareDataAccessException(ex);
    }

    public static class TimeAwareDataAccessException extends DataAccessException {

        public TimeAwareDataAccessException(Throwable cause) {
            super("Error occurred at " + System.currentTimeMillis(), cause);
        }
    }
}
