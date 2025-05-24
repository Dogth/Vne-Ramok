package com.handsdown.account.model;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

@Data
public class Item {

  @NotNull
  @Length(min = 1, max = 20)
  private String title;

  @NotNull private BigDecimal amount;

  @NotNull private float price;

  @NotNull private String icon;
}
