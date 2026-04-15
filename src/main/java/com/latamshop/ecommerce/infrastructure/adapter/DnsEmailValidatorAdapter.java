package com.latamshop.ecommerce.infrastructure.adapter;

import com.latamshop.ecommerce.domain.port.EmailValidatorPort;
import java.util.Hashtable;
import java.util.List;
import javax.naming.directory.InitialDirContext;
import org.springframework.stereotype.Component;

@Component
public class DnsEmailValidatorAdapter implements EmailValidatorPort {

  private static final List<String> BLACKLIST =
      List.of("yopmail.com", "tempmail.org", "10minutemail.com");

  @Override
  public boolean isReal(String email) {
    String domain = email.substring(email.lastIndexOf("@") + 1);

    if (BLACKLIST.contains(domain.toLowerCase())) return false;

    try {
      Hashtable<String, String> env = new Hashtable<>();
      env.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");
      var ctx = new InitialDirContext(env);
      var attrs = ctx.getAttributes(domain, new String[] {"MX"});
      return attrs.get("MX") != null;
    } catch (Exception e) {
      return false;
    }
  }
}
