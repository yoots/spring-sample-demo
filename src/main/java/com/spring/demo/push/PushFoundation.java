/*
 * Copyright (c) 2014 아시아나IDT
 * 
 * 이 프로그램은 저작권 보호법에 의해 보호됩니다.
 * 이 프로그램의 일부나 전부를 무단으로 복제하거나 배포하는 경우에는
 * 저작권의 침해가 되므로 주의하시기 바랍니다.
 */
package com.spring.demo.push;

import javapns.communication.KeystoreManager;
import javapns.notification.AppleNotificationServer;
import javapns.notification.AppleNotificationServerBasicImpl;

/**
 * 이 클래스는...
 *
 * @author user
 * Created on 2014. 8. 27.
 */

public class PushFoundation {

	protected static boolean verifyCorrectUsage(Class<?> testClass, String[] argsProvided, String... argsRequired) {
	    if (argsProvided == null)
	      return true;
	    int numberOfArgsRequired = countArgumentsRequired(argsRequired);
	    if (argsProvided.length < numberOfArgsRequired) {
	      String message = getUsageMessage(testClass, argsRequired);
	      System.out.println(message);
	      return false;
	    }
	    return true;
	  }

	/**
	 * 
	 *
	 * @param testClass
	 * @param argsRequired
	 * @return
	 */
	  private static String getUsageMessage(Class<?> testClass, String... argsRequired) {
	    StringBuilder message = new StringBuilder("Usage: ");
	    message.append("java -cp \"<required libraries>\" ");
	    message.append(testClass.getName());
	    for (String argRequired : argsRequired) {
	      boolean optional = argRequired.startsWith("[");
	      if (optional) {
	        message.append(" [");
	        message.append(argRequired.substring(1, argRequired.length() - 1));
	        message.append("]");
	      } else {
	        message.append(" <");
	        message.append(argRequired);
	        message.append(">");
	      }
	    }
	    return message.toString();
	  }

	  private static int countArgumentsRequired(String... argsRequired) {
	    int numberOfArgsRequired = 0;
	    for (String argRequired : argsRequired) {
	      if (argRequired.startsWith("["))
	        break;
	      numberOfArgsRequired++;
	    }
	    return numberOfArgsRequired;
	  }

	  /**
	   * Validate a keystore reference and print the results to the console.
	   * 
	   * @param keystoreReference a reference to or an actual keystore
	   * @param password password for the keystore
	   * @param production service to use
	   */
	  public static void verifyKeystore(Object keystoreReference, String password, boolean production) {
	    try {
	      System.out.print("Validating keystore reference: ");
	      KeystoreManager.validateKeystoreParameter(keystoreReference);
	      System.out.println("VALID  (keystore was found)");
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    if (password != null) {
	      try {
	        System.out.print("Verifying keystore content: ");
	        AppleNotificationServer server = new AppleNotificationServerBasicImpl(keystoreReference, password, production);
	        KeystoreManager.verifyKeystoreContent(server, keystoreReference);
	        System.out.println("VERIFIED  (no common mistakes detected)");
	      } catch (Exception e) {
	        e.printStackTrace();
	      }
	    }
	  }
}