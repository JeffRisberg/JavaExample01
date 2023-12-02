package com.company;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

import com.company.config.SystemConfigUtil;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class SystemConfigUtilTest {

  @Test
  public void testGet() {

    try (MockedStatic mockStatic = mockStatic(SystemConfigUtil.class)) {

      SystemConfigUtil scu = new SystemConfigUtil();
      scu.set("alpha", 1234.56);
      scu.set("beta", "elephant");
      scu.set("gamma", 1);

      mockStatic.when(SystemConfigUtil::getInstance).thenReturn(scu);

      scu = SystemConfigUtil.getInstance();
      assertEquals(scu.get("alpha"), 1234.56);

      //Inside scope
      //assertEquals("bar", ClassWithStaticMethod.getVal());
      //mockStatic.verify(ClassWithStaticMethod::getVal);
    }
  }
}
