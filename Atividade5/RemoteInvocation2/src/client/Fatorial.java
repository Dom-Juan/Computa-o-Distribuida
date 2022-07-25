package client;

import compute.Task2;
import java.io.Serializable;
import java.math.BigDecimal;

public class Fatorial implements Task2<Integer>, Serializable {
  Integer num;

  public Fatorial(int num) {
    this.num = num;
  }

  public Integer execute2() {
    return computeFat();
  }

  public Integer computeFat() {
    Integer fat = this.num;
    int i = (int)(this.num);
    while(i > 1) {
      fat = fat * (i-1);
      i--;
    }
    return fat;
  }
}
