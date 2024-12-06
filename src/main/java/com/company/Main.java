package com.company;

import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

  public static void main(String[] args) {

    System.out.println("Welcome");
    String enabledAppsList = "1, 2,3 , 4";
    List<String> x = Arrays.asList(enabledAppsList.split(",", -1));
    for (int i = 0; i < x.size(); i++) {
      x.set(i, x.get(i).trim());
    }

    for (String str : x) {
      System.out.println("|"+str+"|");
      System.out.println("|"+str.trim()+"|");
    }

    log.info("Welcome to Example 1");
  }
}
