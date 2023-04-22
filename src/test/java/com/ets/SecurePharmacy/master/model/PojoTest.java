package com.ets.SecurePharmacy.master.model;

import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.PojoField;
import com.openpojo.reflection.PojoMethod;
import com.openpojo.reflection.filters.FilterPackageInfo;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.reflection.impl.PojoClassImpl;
import com.openpojo.reflection.impl.PojoFieldFactory;
import com.openpojo.reflection.impl.PojoMethodFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class PojoTest {
  // The package to test
  private static final String POJO_PACKAGE = "com.ets.SecurePharmacy.master.model";

  @Test
  void testPojoStructureAndBehavior() {
    Validator validator = ValidatorBuilder.create()
                            .with(new SetterTester())
                            .with(new GetterTester())
                            .build();

    List<PojoClass> pojoClasses = PojoClassFactory.getPojoClasses(POJO_PACKAGE,
            new FilterPackageInfo());

    for (PojoClass pojoClass : pojoClasses) {
      final Class<?> classUnderTest = pojoClass.getClazz();
      final ArrayList<PojoField> pojoFields = new ArrayList<>(PojoFieldFactory.getPojoFields(classUnderTest));
      final List<PojoMethod> pojoMethods = PojoMethodFactory.getPojoMethods(classUnderTest);
      final PojoClassImpl pojoClassImpl = new PojoClassImpl(classUnderTest, pojoFields, pojoMethods);
      validator.validate(pojoClassImpl);
    }
  }
}