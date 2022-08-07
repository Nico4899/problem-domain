package edu.kit.tm.cm.smartcampus.problem.infrastructure.database;

import java.io.Serializable;
import java.util.Properties;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;

/**
 * This class represents a custom prefix sequence generator for database primary key generation. It
 * generates a prefixed sequence from {@link SequenceStyleGenerator}. The prefix can now be
 * configured by the {@link GenericGenerator} annotation.
 * @author Jonathan Kramer, Johannes von Geisau
 */
public class PrefixSequenceGenerator extends SequenceStyleGenerator {

  public static final String VALUE_PREFIX_PARAMETER = "valuePrefix";
  public static final String VALUE_PREFIX_DEFAULT = "";
  private String valuePrefix;

  @Override
  public Serializable generate(SharedSessionContractImplementor session, Object object)
      throws HibernateException {

    return valuePrefix + super.generate(session, object);
  }

  @Override
  public void configure(Type type, Properties params, ServiceRegistry serviceRegistry)
      throws MappingException {

    super.configure(LongType.INSTANCE, params, serviceRegistry);
    valuePrefix =
        ConfigurationHelper.getString(VALUE_PREFIX_PARAMETER, params, VALUE_PREFIX_DEFAULT);
  }
}
